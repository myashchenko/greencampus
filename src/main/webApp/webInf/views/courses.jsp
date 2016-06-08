<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="full" lang="en" ng-app="greenApp.courses">
<head>
    <title>Courses</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
    <%@ include file="fragment/header.jspf" %>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
    <script src="/rest/js/controller/courses.js"></script>

    <section class="course text-center">
        <div id="all-courses" class="container-fluid" ng-controller="CoursesController as controller">
            <div class="row" ng-repeat="courses_arr in controller.courses">
                <div class="col-md-4" ng-repeat="course in courses_arr">
                    <article class="course-post">
                        <figure>
                            <a href="/course/{{course.id}}" class="single_image">
                                <div class="course-img-wrap">
                                    <div class="overlay">
                                        <i class="fa fa-search"></i>
                                    </div>
                                    <img src="https://lagunita.stanford.edu/c4x/Education/EDUC115N/asset/images_course_image.jpg" class="single_image"/>
                                </div>
                            </a>
                            <figcaption>
                                <h2><a href="/course/{{course.id}}" class="course-category" data-toggle="tooltip" data-placement="top"
                                       data-original-title="See more posts">{{course.title}}</a></h2>
                                <p><a href="/course/{{course.id}}" class="course-post-title">{{course.description}}<i class="fa fa-angle-right"></i></a></p>
                            </figcaption>
                        </figure>
                    </article>
                </div>
            </div>
        </div>
    </section>

</body>

</html>

