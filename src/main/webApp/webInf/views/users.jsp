<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="full" lang="en" ng-app="greenApp.users">
<head>
    <title>home</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="/rest/js/controller/users.js"></script>

<section class="user text-center" ng-controller="UsersController as controller">
    <div class="container-fluid">
        <div class="row" ng-repeat="users in controller.users">
            <div class="col-md-3" ng-repeat="user in users">
                <article class="user">
                    <figure>
                        <a href="/user/{{user.id}}" class="single_image">
                            <div class="user-img-wrap user-avatar">
                                <div class="overlay">
                                    <i class="fa fa-search"></i>
                                </div>
                                <img ng-if="user.avatar" src="{{user.avatar.path}}" alt="blog image"/>
                                <img ng-if="!user.avatar" src="http://www.localevolutionmedia.com/wp-content/uploads/2015/02/ncf_userpic1.png" alt="blog image"/>
                            </div>
                        </a>
                        <figcaption>
                            <h2><a href="/user/{{user.id}}" class="user-category" data-toggle="tooltip" data-placement="top"
                                   data-original-title="See more posts">{{user.name}}</a></h2>
                        </figcaption>
                    </figure>
                </article>
            </div>
        </div>
    </div>
</section>
</body>
</html>

