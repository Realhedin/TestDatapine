<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/5/15
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edited user</title>
</head>
<body>

<h1>Users</h1>
<p>You have edited an user with id: ${id} at <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/users/" />
<p>Return to <a href="${mainUrl}">Main List</a></p>

</body>
</html>
