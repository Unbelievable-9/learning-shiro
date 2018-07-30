<%--
  Created by IntelliJ IDEA.
  User: unbelievable9
  Date: 2018/7/27
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shiro Web</title>
    <style>.error {
        color: red;
    }</style>
</head>
<body>

<div class="error">${error}</div>

<form action="${pageContext.request.contextPath}/formFilterLogin" method="post">
    <label>
        Form Filter Login
    </label>
    <br/>
    <label>
        Username:
        <input type="text" name="username">
    </label>
    <br/>
    <label>
        Password:
        <input type="password" name="password">
    </label>
    <br/>
    <input type="submit" value="Login">
</form>

</body>
</html>
