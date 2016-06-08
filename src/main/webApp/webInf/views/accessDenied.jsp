<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="full" lang="en">

<head>
    <title>Access Denied</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body style="background-color: #dff0d8;">
<%@ include file="fragment/header.jspf" %>

<section class="hero-access-denied">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="hero-content text-center">
                    <h1>You do not have sufficient permissions to access this page</h1>
                    <p class="intro">Only authorized person have permissions to view this page</p>

                    <a href="/login" class="btn-new btn-accent btn-large-new btn-fill">Sign in</a>
                    <a href="/user/create" id="create-course" class="btn-new btn-accent btn-large-new btn-fill">Join us</a>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

</html>