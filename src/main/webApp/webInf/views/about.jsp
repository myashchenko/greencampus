<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="full" lang="en">

<head>
    <title>About</title>
    <%@ include file="fragment/head.jspf" %>
</head>

<body style="background-color: #dff0d8;">
<%@ include file="fragment/header.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<div class="container aboutContent">
<center><h2>GreenCampus designed by: </h2></center>
<div class="col-sm-5 col-lg-offset-1">
    <h2 class="devs">Developers:</h2>
    <div class="col-sm-12 developer">
        <div class="col-sm-3">
            <img src="/resources/images/nikolay.jpg" alt="Nikolay Yashchenko" class="img-thumbnail img-responsive" height="90" width="90">
        </div>
        <div class="col-sm-9">
            <h3 class="aboutName">Nikolay Yashchenko</h3>
            <img width="20" src="/resources/images/linkedin_3361.png" alt="">
            <a href="https://www.linkedin.com/in/yashchenkon">LinkedIn</a>
        </div>
    </div>
    <div class="col-sm-12 developer">
        <div class="col-sm-3">
            <img src="/resources/images/arsenii.jpg" alt="Arsenii Andrieiev" class="img-thumbnail img-responsive" height="90" width="90">
        </div>
        <div class="col-sm-9">
            <h3 class="aboutName">Arsenii Andrieiev</h3>
            <img width="20" src="/resources/images/linkedin_3361.png" alt="">
            <a href="https://www.linkedin.com/in/arsenii-andrieiev-a65346b7">LinkedIn</a>
        </div>
    </div>
    <div class="col-sm-12 developer">
        <div class="col-sm-3">
            <img src="/resources/images/ivan.jpg" alt="Ivan Mikho" class="img-thumbnail img-responsive" height="90" width="90">
        </div>
        <div class="col-sm-9">
            <h3 class="aboutName">Ivan Mikho</h3>
            <img width="20" src="/resources/images/linkedin_3361.png" alt="">
            <a href="https://www.linkedin.com/in/imikho">LinkedIn</a>
        </div>
    </div>

</div>
<div class="col-sm-5">
    <div>
        <h2 class="devs">Supervisor:</h2>
        <div class="col-sm-12 developer">
            <div class="col-sm-3">
                <img src="/resources/images/kirill.jpg" alt="Kirill Zelensky" class="img-thumbnail img-responsive" height="90" width="90">
            </div>
            <div class="col-sm-9">
                <h3 class="aboutName">Kirill Zelensky</h3>
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                <a href="http://ict.vmurol.com.ua/informaciya_pro_zaklad/administratsiya_v_osobistostyahzelensjkij_kirilo_haritonovich/">Profile</a>
            </div>
        </div>

    </div>
</div>
</div>
</body>

</html>