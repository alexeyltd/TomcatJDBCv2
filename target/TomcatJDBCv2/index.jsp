<%--
  Created by IntelliJ IDEA.
  User: alexeypavlenko
  Date: 26/04/2017
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
</head>
<body>
<div align="center">
    <form action="/LoginFilter" method="POST">
        Login: <input type="text" name="login"/>
        Password: <input type="password" name="password"/>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
