<%--
  Created by IntelliJ IDEA.
  User: alexeypavlenko
  Date: 22/04/2017
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>


<form action="/delete" method="POST">
    ID: <input type="text" name="id"/>
    <input type="submit" value="Submit">
</form>

</body>
</html>
