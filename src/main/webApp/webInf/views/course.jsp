<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="full" lang="en" ng-app="greenApp.course">
<head>
    <title>Course</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<script src="/rest/js/controller/course.js"></script>

<section class="course-slider section-padding-course text-center">
    <div class="container">
        <div class="row" ng-controller="CourseController as controller">
            <div class="col-md-12">
                <div class="avatar">
                    <img src="https://lagunita.stanford.edu/c4x/Education/EDUC115N/asset/images_course_image.jpg" alt="Avatar">
                </div>
                <h2>{{controller.course.title}}</h2>
                <h2>{{controller.course.description}}</h2>
                <p class="author">{{controller.course.author}}</p>
            </div>
        </div>
    </div>
</section>

</body>

</html>
