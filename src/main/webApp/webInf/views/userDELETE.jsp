<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="full" lang="en">
<head>
    <title>home</title>
    <%@ include file="fragment/head.jspf" %>
    <script src="/resources/js/userDELETE.js"></script>
</head>

<body>
<%@ include file="fragment/header.jspf" %>

<input type="hidden" id="userid" value="${userid}">
<div class="container">
    <div id="user">

    </div>
    <button class="btn btn-lg btn-primary btn-block" id="btnSub" type="button">Delete</button>
</div>

</body>

</html>

