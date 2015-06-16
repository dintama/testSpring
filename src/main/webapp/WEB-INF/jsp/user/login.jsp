<%--
  Created by IntelliJ IDEA.
  User: Dintama
  Date: 2015/6/13
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="user/login" METHOD="POST">
  Username:<input type="text" name="username"/><br/>
  Password:<input type="password" name="password"/><br/>
  <input type="submit" value="登录"/>
</form>

</body>
</html>
