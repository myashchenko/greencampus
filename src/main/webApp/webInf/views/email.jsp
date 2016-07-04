<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
    <section id="form-id" class="sign-up text-center">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h3>Enter your email</h3>
                    <form:form cssClass="signup-form" method="POST" modelAttribute="userDto" action="/signup/email">
                        <spring:bind path="email">
                            <div class="form-input-group">
                                <i class="fa fa-envelope"></i><form:input id="email" type="email" required="required" placeholder="Enter your email" path="email" />
                            </div>
                        </spring:bind>
                        <button id="btnSub" type="submit" class="btn-fill sign-up-btn">Continue</button>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>