<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html ng-app="greenApp.theme">
<head>
    <title>${message}</title>
    <%@ include file="fragment/head.jspf" %>
    <script src="/rest/js/controller/theme.js"></script>
    <link rel="stylesheet" href="/resources/css/test/styles.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/fileInput.css" />
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<script src="/resources/js/custom-file-input.js"></script>
<div id="container">

    <section class="create section-padding text-center">
        <div class="container">
            <div class="row">
                <div id="main" class="col-md-6 col-md-offset-3" ng-controller="CreateThemeCtrl">
                    <h3>Please ${message} theme</h3>
                    <form class="custom-form">
                        <div class="form-input-group">
                            <input ng-model="title" type="text" placeholder="Name of course" required>
                        </div>
                        <label for="fileUploading">Attach materials to your theme</label>
                        <div class="form-group" id="fileUploading">
                            <div class="box">
                                <input file-model="file" style="width: 0.1px; height: 0.1px;opacity: 0;overflow: hidden;position: absolute;z-index: -1;" type="file" name="file" id="file-2" class="inputfile js inputfile-2" data-multiple-caption="{count} files selected" multiple />
                                <label for="file-2"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg>
                                    <span>Choose a file&hellip;</span>
                                </label>
                            </div>
                        </div>
                        <button ng-click="attachOneMore()" class="btn btn-default" id="attachOneMore">Attach on more</button>

                        <button ng-click="themeCourse();$event.preventDefault()" class="btn-fill custom-form-btn btn btn-lg btn-primary btn-block" id="btnSub" type="button">${message}</button>
                        <button type="submit" hidden></button>
                    </form>
                </div>
            </div>
        </div>

        <h1><div id="actionDone" class="col-lg-6 col-lg-offset-3 text-center"></div></h1>
    </section>
</div>

</body>
</html>