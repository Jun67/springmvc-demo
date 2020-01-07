<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/css/main.css">
<style>
form {
    margin: 0 auto;
    width: 300px;
    line-height: 40px;
}
</style>
</head>
<body>
<a href="${pageContext.request.contextPath}/user/register">注册</a>
<form class="content-center" action="${pageContext.request.contextPath}/user/login" method="post">
    <label>用户名: <input name="name" value=""></label><br>
    <label>密 码: <input type="password" name="password" value=""></label><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
