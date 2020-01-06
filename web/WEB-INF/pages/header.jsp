<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/main.css">
<div class="head">
    <span>欢迎${user.name}!</span>
    <a href="${pageContext.request.contextPath}/user/logout">退出</a>
    <a href="${pageContext.request.contextPath}/user/update">更新</a>
</div>
