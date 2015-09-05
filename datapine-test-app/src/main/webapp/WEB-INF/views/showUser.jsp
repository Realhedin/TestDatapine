<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/5/15
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show user page</title>
</head>
<body>

<h1>Show user</h1>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#25ff29">
    <tr>
        <th>Email</th>
        <th>Password</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><c:out value="${user.email}" /></td>
        <td><c:out value="${user.password}" /></td>
    </tr>
</tbody>
</table>

<c:url var="mainUrl" value="/users/" />
<p>Return to <a href="${mainUrl}">Main List</a></p>

</body>
</html>
