<%-- 
    Document   : login
    Created on : Nov 18, 2019, 2:00:10 PM
    Author     : hams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:set var="errmsg" scope="page" value="${errorMsg}"></c:set>
                                        <c:if test="${errmsg != null}">
                                            ${errmsg}
                                           
                                        </c:if>
        <form action="dashboard" method="POST">
            <label>Email</label>
            <input type="text" name="email"><br>
            <label>Password</label>
            <input type="password" name="password"><br>
            <button type="submit" name="submitbtn">Login</button>
        </form>
    </body>
</html>
