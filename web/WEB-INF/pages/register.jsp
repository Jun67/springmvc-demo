<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>注册</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<a href="${pageContext.request.contextPath}/user/login">登录</a>

<form class="content-center" action="${pageContext.request.contextPath}/user/register" method="post">
    <div><label>姓名：<input name="name"></label></div>
    <div><label>密码：<input type="password" name="password"></label></div>
    <div><label>性别：<input name="sex"></label></div>
    <div><label>手机：<input name="phone"></label></div>
    <div><label>邮箱：<input name="email"></label></div>
    <div><label>生日：<input name="birthday"></label></div>
    <div><input type="submit" value="注册"></div>
</form>

</body>
</html>
