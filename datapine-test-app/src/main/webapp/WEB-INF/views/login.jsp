<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ page session="true" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datapine Login</title>
</head>
<body>

<h2>Login:</h2>
<p style="background:rgba(145, 16, 255, 0.20)">Spring security login:
<br>Current users: admin/admin and user/user</p>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
    <table>
        <tr>
            <td align="right"><spring:message code="label.login" /></td>
            <td><label>Email:</label>
                <input type="text" name="j_username" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="label.password" /></td>
            <td><label>Password:</label>
                <input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="label.remember" /></td>
            <td><label>Remember me</label>
                <input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Login" />
                <input type="reset" value="Reset" /></td>
        </tr>
    </table>
</form>

<br><br>or<br>
<h2>Register new user: </h2>
<form method="post" action="register">
    Email: <input name="email" type="text" />
    <br />
    Password: <input name="password" type="password" />
    <br />
    <input type="submit" value="register"/>
</form>

</body>
</html>