package com.bailiban.socket.niohttptest2;


import com.bailiban.socket.niohttptest2.annotation.MyRequestMapping;
import com.bailiban.socket.niohttptest2.annotation.MyRestController;
import com.bailiban.socket.niohttptest2.model.MethodInfo;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerTest {
    // IOC 容器
    public static Map<String, Object> beanMap = new HashMap<>();
    // URL映射，RequestMapping
    public static Map<String, MethodInfo> methodMap = new HashMap<>();

    private static String pkg = "com.bailiban.socket.niohttptest2";

    public static void main(String[] args) throws Exception {

        refreshBeanFactory(pkg);

//        建立服务器端socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(80));
        serverSocketChannel.configureBlocking(false);
//        建立selector
        Selector selector = Selector.open();
//        将服务器端socket注册到selector中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(3000) <= 0) {
                continue;
            }
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                handler(selectionKey);
                keyIterator.remove();
            }
        }
    }

    // 1. 初始化 beanMap
    // 2. 初始化 methodMap
    private static void refreshBeanFactory(String pkg) throws Exception {
        // 将包名中的点转换为斜杠
        String path = pkg.replace(".", "/");
        // 从class path中获取上述资源
        URL url = ServerTest.class.getClassLoader().getResource(path);
        //  获取包名对应的文件夹
        // 通过解码可以解决：1）中文目录；2）目录中包含空格；3）路径为反斜杠\\
        File rootPkgDir = new File(URLDecoder.decode(url.getPath(), "utf-8"));
        // 遍历文件夹，解析class
        beanParse(rootPkgDir);
    }

    private static void beanParse(File dir) {
        // 非目录直接返回
        if (!dir.isDirectory()) {
            return;
        }
        // 过滤并获取目录下的所有文件
        File[] files = dir.listFiles(pathname -> {
            // 如果是目录，则递归调用 beanParse，并且返回false
            if (pathname.isDirectory()) {
                beanParse(pathname);
                return false;
            }
            // 对应文件，则判断是否为class文件 ，只处理class文件
            return pathname.getName().endsWith(".class");
        });
        for (File f: files) {
            // 获取绝对路径
            String filePath = f.getAbsolutePath();
            // 获取全类名：包名 + 类名
            String className = filePath.split("classes\\\\")[1].replace("\\", ".").split("\\.class")[0];
            try {
                Class<?> cls = Class.forName(className);
                MyRestController myRestController = cls.getAnnotation(MyRestController.class);
                // 处理 MyRestController注解的类
                if (myRestController != null) {
                    controllerParse(cls);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // 处理 MyRestController注解的类
    private static void controllerParse(Class<?> cls) {
        try {
            // IOC容器注入
            beanMap.put(cls.getSimpleName(), cls.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // 解析  MyRequestMapping注解的方法，初始化  methodMap
        Method[] methods = cls.getDeclaredMethods();
        for (Method m: methods) {
            MyRequestMapping myRequestMapping = m.getDeclaredAnnotation(MyRequestMapping.class);
            if (myRequestMapping == null)
                continue;
            String url = myRequestMapping.value();
            methodMap.put(url, new MethodInfo(m, cls.getSimpleName()));
        }
    }

    private static void handler(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            acceptHandler(key);
        } else if (key.isReadable()) {
            requestHandler(key);
        }
    }

    private static void requestHandler(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
//        clear：设置为写模式
        byteBuffer.clear();
//        从socket中读取内容到byteBuffer
        if (socketChannel.read(byteBuffer) == -1) {
            socketChannel.close();
            return;
        }
//        设置读模式
        byteBuffer.flip();
        String requestHeader = new String(byteBuffer.array());
        String url = requestHeader.split("\r\n")[0].split(" ")[1];
        String paramBody = requestHeader.split("\r\n\r\n")[1].trim();
        System.out.println("paramBody: " + paramBody);

        List<String> urlParams = new ArrayList<>();
        urlParamsParse(url, urlParams);

        System.out.println(url);

        url = url.split("\\?")[0];
        String content = null;
        try {
            content = methodInvoke(url, urlParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (content == null)
            content = "404";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 200 OK\r\n");
        stringBuilder.append("Content-Type:text/html;charset=utf-8\r\n\r\n");
//        stringBuilder.append("<html><head><title>HttpTest</title></head><body>\r\n");
        stringBuilder.append(content);
//        stringBuilder.append("</body></html>");
        socketChannel.write(ByteBuffer.wrap(stringBuilder.toString().getBytes()));
        socketChannel.close();
    }

    // 调用url所映射的方法
    private static String methodInvoke(String url, List<String> urlParams) throws Exception {
        MethodInfo methodInfo = methodMap.get(url);
        if (methodInfo == null) {
            return "404";
        }
        String className = methodInfo.getClassName();
        Method method = methodInfo.getMethod();
        Object beanObj = beanMap.get(className);
        Parameter[] parameters = method.getParameters();
        Map model = null;
        List<Object> params = new ArrayList<>();
        // 按照方法中参数的属性，来进行参数转换和填充
        boolean mapSetFlag = false;
        for (Parameter p: parameters) {
            String type = p.getType().getSimpleName();
            String pName = p.getName();
            boolean flag = false;
            for(String p2: urlParams) {
                String pp[] = p2.split("=");
                if (pName.equals(pp[0].trim())) {
                    // 根据类型进行参数转换
                    Object pValue = paramTranslate(type, pp[1]);
                    params.add(pValue);
                    flag = true;
                    continue;
                }
            }
            if (!flag) {
                if (p.getType().getSimpleName().equals("Map") && !mapSetFlag) {
                    model = new HashMap<>();
                    params.add(model);
                    mapSetFlag = true;
                    continue;
                }
                return "参数不匹配";
            }
        }
        String content =  (String) method.invoke(beanObj, params.toArray());
        if (content.endsWith(".html")) {
            content = parseHtml(content, model);
        }
        return content;
    }

    private static String parseHtml(String page, Map model) throws Exception {
        String path = pkg + ".pages/";
        path = path.replace(".", "/") + page;
        URL url = ServerTest.class.getClassLoader().getResource(path);
        //  获取包名对应的文件夹
        // 通过解码可以解决：1）中文目录；2）目录中包含空格；3）路径为反斜杠\\
        File file = new File(URLDecoder.decode(url.getPath(), "utf-8"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        while ((line = bufferedReader.readLine()) != null) {
            Matcher m = pattern.matcher(line);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                System.out.println(m.group(1));
                String[] objAttr = m.group(1).split("\\.");
                if (objAttr.length == 1) {
                    Object o = model.get(objAttr[0]);
                    m.appendReplacement(sb, o != null ? String.valueOf(o) : "");
                    continue;
                }
                Object obj = model.get(objAttr[0]);
                objAttr[1] = (char)(objAttr[1].charAt(0) - 32) +objAttr[1].substring(1);
                Method method = obj.getClass().getDeclaredMethod("get" + objAttr[1]);
                m.appendReplacement(sb, String.valueOf(method.invoke(obj)));
            }
            m.appendTail(sb);
            stringBuilder.append(sb);
        }
        return stringBuilder.toString();
    }

    private static Object paramTranslate(String type, String s) {
        switch (type) {
            case "int":
                return Integer.valueOf(s);
            case "double":
                return Double.valueOf(s);
            case "float":
                return Float.valueOf(s);
            default:
                return s;
        }
    }

    // 解析url参数
    private static void urlParamsParse(String url, List<String> urlParams) {
        if (!url.contains("?")) {
            return;
        }
//        /hello?id=1&name=dd&ssss => id=1&name=dd&ssss => [id=1, name=dd, ssss]
        String[] ps = url.replaceFirst(".*?\\?", "").split("&");
        for (String p: ps) {
            // 过滤ssss
            if (!p.contains("="))
                continue;
            urlParams.add(p);
        }
    }

    //  服务器端处理连接请求，将客户端socketChannel注册到selector中
    private static void acceptHandler(SelectionKey key) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

}
