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

<br>Basic login without security, but with login/password from DB
<br>Current users: admin/admin and user/user<br>
<form method="POST" action="tryLogin">
	Email: <input name="email" type="text" />
	<br />
	Password: <input name="password" type="password" />
	<br />
	<input type="submit" value="login" />
</form>

<br><br>
<p style="background:rgba(145, 16, 255, 0.20)">Spring security login:
<br>Current users: admin1/111 and user2/222</p>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
    <table>
        <tr>
            <td align="right"><spring:message code="label.login" /></td>
            <td><input type="text" name="j_username" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="label.password" /></td>
            <td><input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="label.remember" /></td>
            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Login" />
                <input type="reset" value="Reset" /></td>
        </tr>
    </table>
</form>

</br></br>or</br>
<p>Register new user: </p>
<form method="post" action="register">
    mail: <input name="email" type="text" />
    <br />
    Password: <input name="password" type="password" />
    <br />
    <input type="submit" value="register"/>
</form>

</body>
</html>