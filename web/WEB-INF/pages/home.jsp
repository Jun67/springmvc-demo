<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
<style>
div#user {
    margin: 0 auto;
    width: 300px;
    line-height: 40px;
}
</style>
</head>
<body>
<div id="user">
    <label>ID: ${user.id}</label><br>
    <label>Name: ${user.name}</label><br>
    <a href="${pageContext.request.contextPath}/user/logout">Logout</a>
</div>
</body>
</html>
