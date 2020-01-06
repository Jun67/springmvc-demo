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
    <form class="content-center" action="${pageContext.request.contextPath}/user/login" method="post">
        <label>ID: <input name="id" value=""></label><br>
        <label>Name: <input name="name" value=""></label><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
