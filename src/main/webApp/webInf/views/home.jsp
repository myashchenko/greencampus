<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="full" lang="en" ng-app="greenApp.home-courses">

<head>
    <title>Home</title>
    <%@ include file="fragment/head.jspf" %>

    <link rel="stylesheet" href="/resources/css/test/normalize.min.css">
    <link rel="stylesheet" href="/resources/css/test/styles.css">
    <link rel="stylesheet" href="/resources/css/test/queries.css">
    <link rel="stylesheet" href="/resources/css/test/etline-font.css">

</head>

<body>
<%@ include file="fragment/header.jspf" %>

<section class="hero">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="hero-content text-center">
                    <h1>GreenCampus</h1>
                    <p class="intro">Distance educational system. Try it everywhere.</p>

                    <a href="/course/all" class="btn-new btn-accent btn-large-new">View courses</a>

                    <sec:authorize access="!isAuthenticated()">
                        <a href="/login?redirect=/course/create" id="create-course"
                           class="btn-new btn-accent btn-large-new">Create a course</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="/course/create" id="create-course" class="btn-new btn-accent btn-large-new">Create a
                            course</a>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="intro section-padding">
    <div class="container">
        <div class="row">
            <div class="col-md-4 intro-feature">
                <div class="intro-icon">
                    <span data-icon="&#xe033;" class="icon"></span>
                </div>
                <div class="intro-content">
                    <h5>Easily used</h5>
                    <p>Developed by students for students.</p>
                </div>
            </div>
            <div class="col-md-4 intro-feature">
                <div class="intro-icon">
                    <span data-icon="&#xe030;" class="icon"></span>
                </div>
                <div class="intro-content">
                    <h5>Modern Design</h5>
                    <p>Designed with modern trends and techniques in mind, GreenCampus will help your to study new
                        courses.</p>
                </div>
            </div>
            <div class="col-md-4 intro-feature">
                <div class="intro-icon">
                    <span data-icon="&#xe046;" class="icon"></span>
                </div>
                <div class="intro-content last">
                    <h5>Many courses</h5>
                    <p>All teachers uploaded their materials to GreenCampus and you can use those from any device.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="testimonial-slider section-padding text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="avatar"><img src="/resources/images/kirill.jpg" alt="Avatar"></div>
                <h2>"Nulla aetas ad discendum sera. (Учиться никогда не поздно)"</h2>
                <p class="author">Kirill Zelensky, Project owner.</p>

            </div>
        </div>
    </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="/rest/js/controller/home-courses.js"></script>
<section class="blog text-center">
    <div class="container-fluid">
        <div class="row" ng-controller="HomeCoursesController as controller">
            <div class="col-md-4" ng-repeat="course in controller.courses">
                <article class="blog-post">
                    <figure>
                        <a href="/course/{{course.id}}" class="single_image">
                            <div class="blog-img-wrap">
                                <div class="overlay">
                                    <i class="fa fa-search"></i>
                                </div>
                                <img src="https://lagunita.stanford.edu/c4x/Education/EDUC115N/asset/images_course_image.jpg" alt="Sedna blog image"/>
                            </div>
                        </a>
                        <figcaption>
                            <h2><a href="/course/{{course.id}}" class="blog-category" data-toggle="tooltip" data-placement="top" data-original-title="See more posts">{{course.title}}</a></h2>
                            <p><a href="/course/{{course.id}}" class="blog-post-title">{{course.description}}<i class="fa fa-angle-right"></i></a></p>
                        </figcaption>
                    </figure>
                </article>
            </div>
            <a href="/course/all" class="btn-new btn-ghost btn-accent btn-small-new">More courses</a>
        </div>
    </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="/rest/js/app.js"></script>

</body>

</html>

