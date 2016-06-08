<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="full" lang="en">
<head>
    <title>Chat</title>
    <%@ include file="fragment/head.jspf" %>
    <script src="https://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>

    <script language="JavaScript">
        window.userId = "${loggedInUserId}";
    </script>
    <script src="/resources/js/chat.js"></script>
</head>

<body>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/" />">GreenCampus</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">How it works</a>
                </li>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <li>
                    <sec:authorize access="!isAuthenticated()">
                        <a href="/login"><i class="fa fa-sign-in fa-lg"></i> Login</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="/logout"><i class="fa fa-sign-out fa-lg"></i> Log out</a>
                    </sec:authorize>
                </li>
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <a href="/user/account"><i class="fa fa-user fa-lg"></i> Profile</a>
                    </sec:authorize>
                </li>
                <li>
                    <sec:authorize access="!isAuthenticated()">
                        <a href="/user/create" class="btn btn-default join-us">Join us</a>
                    </sec:authorize>
                </li>
            </ul>
        </div>

    </div>
</nav>

<div class="container">
    <div class="row" style="padding-top:20px;">
        <h4 class="text-center">CHAT</h4>
        <br/>
        <div id="message-history" style="visibility: hidden" class="col-md-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    RECENT CHAT HISTORY
                </div>
                <div id="scroll-chat-panel" class="panel-body pre-scrollable" style="height: 100%">
                    <ul id="chat-messages-panel" class="media-list">

                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="new-message-text" type="text" class="form-control" placeholder="Enter Message"/>
                        <span class="input-group-btn">
                            <button id="send-message-button" class="btn btn-info" type="button">SEND</button>
                        </span>
                        <input type="hidden" id="current-dialog-id"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">USERS</div>
                <div class="panel-body">
                    <ul id="user_panel_id" class="media-list">

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>

