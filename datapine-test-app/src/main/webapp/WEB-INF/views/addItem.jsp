<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/8/15
  Time: 10:13 PM
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
    <title>Add user page</title>
</head>
<body>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>Welcome : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h2>
</c:if>

<h1>Create New Item</h1>
<c:url var="saveUrl" value="/items/add" />
<form:form modelAttribute="item" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="product">Product:</form:label></td>
            <td><form:input path="product"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>
