<%--
  Created by IntelliJ IDEA.
  User: alexeypavlenko
  Date: 22/04/2017
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Users</title>
</head>
<body>

<form action="/update" method="POST">
    ID: <input type="text" name="id" value="1"/>
    Name: <input type="text" name="name" value="max"/>
    Login: <input type="text" name="login" value="maxGen"/>
    Password: <input type="password" name="password" value="123"/>
    <input type="submit" value="Submit">
</form>


</body>
</html>
