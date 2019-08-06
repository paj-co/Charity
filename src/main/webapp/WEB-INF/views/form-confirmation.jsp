<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Formularz potwierdzenie</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css" />" />
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <%@ include file="action-header-logged.jsp"%>
        <%@ include file="header.jsp" %>
    </nav>

    <div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
            informacje o odbiorze.
        </h2>
    </div>
</header>

<%@ include file="footer.jsp" %>

<script src="<c:url value="resources/js/app.js" />"></script>
</body>
</html>