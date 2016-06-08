<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html ng-app="greenApp.course">
<head>
    <title>${message}</title>
    <%@ include file="fragment/head.jspf" %>
    <script src="/rest/js/controller/course.js"></script>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<div id="container">

    <section class="create section-padding text-center">
        <div class="container">
            <div class="row">
                <div id="main" class="col-md-6 col-md-offset-3" ng-controller="CreateCourseCtrl">
                    <h3>Please ${message} course</h3>
                    <form class="custom-form">
                        <div class="form-input-group">
                            <input ng-model="title" type="text" placeholder="Name of course" required>
                        </div>
                        <div class="form-input-group">
                            <input ng-model="description" type="text" placeholder="Description" required>
                        </div>
                        <button id="btnSub" ng-click="createCourse();$event.preventDefault()" type="button" class="btn-fill custom-form-btn">${message}</button>
                        <button type="submit" hidden></button>
                    </form>
                </div>
            </div>
        </div>

        <h1><div id="actionDone" class="col-lg-6 col-lg-offset-3 text-center"></div></h1>
    </section>
</div>
</body>
</html>