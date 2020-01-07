<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>更新</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<%@ include file="header.jsp"%>

<c:if test="${!empty user}">
    <form class="content-center" action="${pageContext.request.contextPath}/user/update" method="post">
        <input name="id" hidden value="${user.id}">
        <div><label>姓名：<input name="name" value="${user.name}"></label></div>
        <div><label>密码：<input type="password" name="password" value="${user.password}"></label></div>
        <div><label>性别：<input name="sex" value="${user.sex}"></label></div>
        <div><label>手机：<input name="phone" value="${user.phone}"></label></div>
        <div><label>邮箱：<input name="email" value="${user.email}"></label></div>
        <div><label>生日：<input name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"></label></div>
        <div><input type="submit" value="保存"></div>
    </form>
</c:if>

</body>
</html>
