<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/8/15
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of items</title>
</head>
<body>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>Welcome : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h2>
</c:if>

<c:url var="changePass" value="/items/userEdit" />
<h3> <a href="${changePass}">Edit password</a></h3>



<h1>Items</h1>
<c:url var="addUrl" value="/items/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#ff00f9">
    <tr>
        <th>Product</th>
        <th colspan="4">Links</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${itemsList}" var="item">
        <c:url var="deleteUrl" value="/items/delete?id=${item.id}" />
        <tr>
            <td><c:out value="${item.product}" /></td>
            <td><a href="${deleteUrl}">Delete</a></td>
            <td><a href="${addUrl}">Add</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty itemsList}">
    There are currently no items in the list for this user. <a href="${addUrl}">Add</a> an item..
</c:if>


<h1>Users</h1>
<c:url var="mainUrl" value="/users/" />
<p>Go to <a href="${mainUrl}">Main List</a></p>

</body>
</html>
