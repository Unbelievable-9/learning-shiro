<%--
  Created by IntelliJ IDEA.
  User: unbelievable9
  Date: 2018/7/30
  Time: 下午2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shiro Web</title>
</head>
<body>
${subject.principal} has 'create user' permission.
<br/>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
