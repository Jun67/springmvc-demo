<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<%@ include file="header.jsp"%>

<c:if test="${!empty user}">
    <form class="content-center" action="${pageContext.request.contextPath}/user/update" method="post">
        <div><label>ID：<input name="id" value="${user.id}"></label></div>
        <div><label>Name：<input name="name" value="${user.name}"></label></div>
        <div><input type="submit" value="保存"></div>
    </form>
</c:if>

</body>
</html>
