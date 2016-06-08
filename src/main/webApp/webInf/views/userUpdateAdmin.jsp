<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${message}</title>
    <%@ include file="fragment/head.jspf" %>
    <script src="/resources/js/userUpdateAdmin.js"></script>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<input type="hidden" id="userid" value="${userid}">
<section id="form-id" class="sign-up text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <h3>Update your profile</h3>
                <form id="signup-form" class="signup-form" method="POST" role="form">
                    <div class="row">
                        <label for="avatar-input" class="user-avatar" style="cursor: pointer">
                            <img src="http://placehold.it/150x150" alt="avatar" id="avatar">
                        </label>
                    </div>
                    <div class="form-input-group">
                        <i class="fa fa-envelope"></i><input id="inputEmail" type="email"
                                                             placeholder="Enter your email" required>
                    </div>
                    <div class="form-input-group">
                        <i class="fa fa-user"></i><input id="inputName" type="text" placeholder="Enter your name"
                                                         required>
                    </div>
                    <select class="form-control form-input-group" id="role" name="role">
                        <option value="0">ROLE_UNACTIVE</option>
                        <option value="1">ROLE_STUDENT</option>
                        <option value="2">ROLE_TEACHER</option>
                        <option value="3">ROLE_ADMIN</option>
                    </select>
                    <div hidden>
                        <input type="file" id="avatar-input" name="file" multiple="multiple"
                               accept="image/gif, image/jpeg, image/png"/>
                    </div>

                    <a href="/user/update/pass" class="btn-fill sign-up-btn">Update password</a>
                    <button id="btnSub" type="button" class="btn-fill sign-up-btn">Update</button>
                    <button type="submit" hidden></button>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>