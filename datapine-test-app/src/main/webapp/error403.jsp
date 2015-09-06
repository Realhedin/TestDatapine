<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 9/6/15
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

You don't have authorities to do this action!

<br>
<c:url var="mainUrl" value="/users/" />
<p>Return to <a href="${mainUrl}">Main List</a></p>

<br>
<br><h3>Login with another user:</h3>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>

</body>
</html>
