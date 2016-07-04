<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
    <script>
        $('#password').attr('pattern', '[A-Za-z0-9@]{5,}');
    </script>
    <script src="/resources/js/social.js"></script>
</head>

<body>
    <%@ include file="fragment/header.jspf" %>
    <section id="form-id" class="sign-up text-center">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h3>Enter your credentials</h3>
                    <form:form cssClass="signup-form" method="POST" modelAttribute="userDto" action="/auth">
                        <spring:bind path="email">
                            <div class="form-input-group">
                                <i class="fa fa-envelope"></i><form:input id="email" type="email" required="required" placeholder="Enter your email" path="email" />
                            </div>
                        </spring:bind>

                        <spring:bind path="password">
                            <div class="form-input-group">
                                <i class="fa fa-lock"></i><form:input id="password" required="required" type="password" placeholder="Enter your password" path="password"/>
                            </div>
                        </spring:bind>
                        <button id="btnSub" type="submit" class="btn-fill sign-up-btn">Sign in</button>
                    </form:form>
                    <h3>or use social authorization</h3>
                    <form class="signup-form" action="/signin/vkontakte" method="post">
                        <input type="hidden" name="scope" value="notify,photos,pages,offline,email" />
                        <button type="submit" class="btn-fill sign-up-btn connectButton">Vkontakte</button>
                    </form>
                    <form class="signup-form" action="/signin/facebook" method="post">
                        <input type="hidden" name="scope" value="public_profile,email"/>
                        <button type="submit" class="btn-fill sign-up-btn connectButton">Facebook</button>
                    </form>
                    <form class="signup-form" action="/signin/google" method="post">
                        <input type="hidden" name="scope" value="profile email"/>
                        <button type="submit" class="btn-fill sign-up-btn connectButton">Google</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>