<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/5/15
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>Welcome : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h2>
</c:if>


<h1>Users</h1>

<c:url var="addUrl" value="/users/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#ff00f9">
    <tr>
        <th>Email</th>
        <th colspan="4">Links</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersList}" var="user">
        <c:url var="editUrl" value="/users/edit?id=${user.id}" />
        <c:url var="deleteUrl" value="/users/delete?id=${user.id}" />
        <c:url var="showUrl" value="/users/show?id=${user.id}"/>
        <tr>
            <td><c:out value="${user.email}" /></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
            <td><a href="${addUrl}">Add</a></td>
            <td><a href="${showUrl}">Show</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty usersList}">
    There are currently no users in the list. <a href="${addUrl}">Add</a> an user.
</c:if>

</body>
</html>