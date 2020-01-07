<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="content-center">
    <label>ID: ${user.id}</label><br>
    <label>姓名: ${user.name}</label><br>
    <label>性别: ${user.sex}</label><br>
    <label>手机: ${user.phone}</label><br>
    <label>邮箱: ${user.email}</label><br>
    <label>生日: <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/></label><br>
</div>
</body>
</html>
