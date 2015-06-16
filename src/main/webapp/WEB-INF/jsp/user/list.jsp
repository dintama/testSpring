<%--
  Created by IntelliJ IDEA.
  User: Dintama
  Date: 2015/6/12
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 引入jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" type="text/css"/>
<body>
  <a href="add">填加</a>---->${loginUser.username}
  <br/>

  <c:forEach items="${users}" var="user">
    <a href="${user.value.username}">${user.value.username}</a> ----${user.value.password}----${user.value.email}
    <a href="${user.value.username}/update">编辑</a>
    <a href="${user.value.username}/delete">删除</a>
    <br/>
  </c:forEach>

</body>
</html>
