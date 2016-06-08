<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="full" lang="en" ng-app="greenApp.user">
<head>
    <title>home</title>
    <%@ include file="fragment/head.jspf" %>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular-route.js"></script>
<script src="/rest/js/controller/user.js"></script>

<input type="hidden" id="userid" value="${userid}">
    <div id="user" class="container" ng-controller="UserController as controller">
        <div class="jumbotron col-lg-6 col-md-6 col-sm-offset-3">
            <div class="row">
                <div class="user-avatar">
                    <img ng-if="controller.user.avatar" src="{{controller.user.avatar.path}}" alt="avatar" id="avatar">
                    <img ng-if="!controller.user.avatar" src="http://www.localevolutionmedia.com/wp-content/uploads/2015/02/ncf_userpic1.png" alt="avatar" id="avatar">
                </div>

            </div>
            <span class="label label-info">Name</span>
            <div class="well well-sm" id="Name">{{controller.user.name}}</div>
            <span class="label label-info">Mail</span>
            <div class="well well-sm" id="Mail">{{controller.user.email}}</div>
            <div class="row">
                <div class="text-center">
                    <sec:authorize access="isAuthenticated()">
                        <script>
                            $('document').ready(function () {
                                $('#send-message-item').click(function () {
                                    var form = document.createElement("form");
                                    form.setAttribute("method", 'POST');
                                    form.setAttribute("action", '/chat/new/' + $('#userid').val());
                                    form.submit();
                                });
                            });
                        </script>
                        <button id="send-message-item" class="btn-new btn-fill btn-large-new" type="button">Send message</button>
                    </sec:authorize>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="text-center">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <button onclick="window.location='/user/update/' + $('#userid').val()" class="btn-new btn-fill btn-large-new" type="button">Edit</button>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
