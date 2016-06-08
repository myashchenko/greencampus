<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@ include file="fragment/head.jspf" %>
    <script src="/resources/js/userCREATE.js"></script>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<div id="container">
    <section id="form-id" class="sign-up section-padding text-center">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h3>Get started with GreenCampus, absolutely free</h3>
                    <form id="signup-form" class="signup-form" method="POST" role="form">
                        <div class="form-input-group">
                            <i class="fa fa-envelope"></i><input id="inputEmail" type="email" placeholder="Enter your email" required>
                        </div>
                        <div class="form-input-group">
                            <i class="fa fa-user"></i><input id="inputName" type="text" placeholder="Enter your name" required>
                        </div>
                        <div class="form-input-group">
                            <i class="fa fa-lock"></i><input id="inputPassword" type="password" pattern="[A-Za-z0-9@]{5,}" placeholder="Enter your password" required>
                        </div>
                        <button id="btnSub" type="button" class="btn-fill sign-up-btn">Sign up</button>
                        <button type="submit" hidden></button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <h1>
        <div id="regDone" class="col-lg-6 col-lg-offset-3 text-center"></div>
    </h1>
</div>
</body>
</html>