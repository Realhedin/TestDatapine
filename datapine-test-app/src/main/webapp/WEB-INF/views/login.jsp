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
<br>Current users:
 <br>admin@admin.com/admin  - has ROLE_ADMIN (all privileges),
    <br>user@user.com/user  - has ROLE_USER,
    <br>acl@acl.com/acl - has ROLE_USER (but is not allowed to add Item via acl security)</p>
<form method="POST" action="<c:url value="/j_spring_security_check" />" onsubmit="return validateEmail(j_username.value)">
    <table>
        <tr>
            <td align="right"><spring:message code="label.login" /></td>
            <td><label>Email:</label>
                <input type="text" name="j_username" required placeholder="Example@test.com"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="label.password"/></td>
            <td><label>Password:</label>
                <input type="password" name="j_password" required/></td>
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
<form name="regForm" method="post" action="register" onsubmit="return validateEmail(email.value)">
    Email: <input name="email" type="text" required placeholder="Example@test.com"/>
    <br />
    Password: <input name="password" type="password" required/>
    <br />
    <input type="submit" value="register" />
</form>

<script type="application/javascript">
    function validateEmail(email) {
        var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if(re.test(email) == false) {
            alert("Invalid email address. Please, use pattern: Example@test.com");
            return false;
        } else {
            return true;
        }
    }
</script>

</body>
</html>

