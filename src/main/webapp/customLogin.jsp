<%--
  Created by IntelliJ IDEA.
  User: unbelievable9
  Date: 2018/7/30
  Time: 下午5:40
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

<form action="" method="post">
    <label>
        Custom Login
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
