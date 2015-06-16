<%--
  Created by IntelliJ IDEA.
  User: Dintama
  Date: 2015/6/12
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>

<form:form method="post" modelAttribute="user">
  Username:<form:input path="username"/><form:errors path="username" /><br/>
  Password:<form:password path="password"/><form:errors path="password"/> <br/>
  Email:<form:input path="email"/><form:errors path="email"/> <br/>
  <input type="submit" value="确定"/>
</form:form>


</body>
</html>
