<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>403 | Charity</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <%@ include file="../action-header-not-logged.jsp"%>
        <%@ include file="../action-header-logged.jsp"%>
        <%@ include file="../header.jsp"%>
    </nav>
</header>
<section class="login-page">
    <h2 style="color: red">Dostęp zabroniony!</h2>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>