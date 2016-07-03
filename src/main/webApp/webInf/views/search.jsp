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

    <section class="course text-center">
        <div id="all-courses" class="container-fluid">

        </div>
    </section>

</body>

</html>

