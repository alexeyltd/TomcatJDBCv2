<%--
  Created by IntelliJ IDEA.
  User: alexeypavlenko
  Date: 22/04/2017
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>


<html>
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>Users</title>
</head>
<body>
<sql:setDataSource
        var="myDB"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/users"
        user="root" password="111"
/>

<sql:query var="listUsers" dataSource="${myDB}">
    SELECT * FROM data;
</sql:query>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>LOGIN</th>
            <th>PASSWORD</th>
        </tr>
        <c:forEach var="user" items="${listUsers.rows}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.login}" /></td>
                <td><c:out value="${user.password}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

<br>

<div align="center">
    <a href="/create.jsp" target="blank">
        <button>CREATE</button>
    </a>
    <a href="/update.jsp" target="blank">
        <button>UPDATE</button>
    </a>
    <a href="/delete.jsp" target="blank">
        <button>DELETE</button>
    </a>
</div>
</body>
</html>
