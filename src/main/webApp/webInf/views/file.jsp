<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${message}</title>
    <%@ include file="fragment/head.jspf" %>
    <script src="/resources/js/file.js"></script>
</head>

<body>
<%@ include file="fragment/header.jspf" %>
<div id="container">
    <form class="form-file" formenctype="multipart/form-data">
        <input type="file" id="avatar" name="file" multiple="multiple"/>
        <button class="btn btn-lg btn-primary btn-block" id="sendava" type="button">Send</button>
    </form>
</div>
</body>
</html>


