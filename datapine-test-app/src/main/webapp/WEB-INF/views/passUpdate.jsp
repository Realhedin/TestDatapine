<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/9/15
  Time: 9:45 PM
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
    <title>Change user password page</title>
</head>
<body>

<h1>Change user password</h1>
<c:url var="saveUrl" value="/items/userEdit" />
<form:form modelAttribute="user" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="id">Id:</form:label></td>
            <td><form:input path="id" readonly="true"/></td>
        </tr>

        <tr>
            <td><form:label path="email">Email:</form:label></td>
            <td><form:input path="email" readonly="true"/></td>
        </tr>

        <tr>
            <td><form:label path="oldPassword">Old password:</form:label></td>
            <td><form:input path="oldPassword" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="newPassword">New password:</form:label></td>
            <td><form:password path="newPassword"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>
