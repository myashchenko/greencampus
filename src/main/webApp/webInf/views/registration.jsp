<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <title>Registration Form</title>
    <%@ include file="fragment/head.jspf" %>
</head>

<body>
    <%@ include file="fragment/header.jspf" %>

    <h2 class="col-lg-6 col-lg-offset-3">Registration Form</h2>

    <div class="login-form col-lg-6 col-lg-offset-3">
        <form:form method="POST" modelAttribute="userDTO" action="registration">
            <spring:bind path="email">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><i class="fa fa-user"></i></div>
                        <form:input type="text" class="form-control" placeholder="Email" path="email"/>
                    </div>
                    <span class="help-block has-error" id="email-error"></span>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                        <form:input type="password" class="form-control" id="password" placeholder="Password" path="password"/>
                    </div>
                    <span class="help-block has-error" id="password-error"></span>
                </div>
            </spring:bind>
            <button type="submit" class="btn btn-group btn-success">Sign Up</button>
        </form:form>
    </div>
    <br/>

</body>
</html>