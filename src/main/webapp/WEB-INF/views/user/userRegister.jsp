<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Zarejestruj się</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/> "/>
</head>
<body>
<header>
    <nav class="container container--70">
        <%@ include file="../action-header-not-logged.jsp"%>
        <%@ include file="../header.jsp"%>
    </nav>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="userDTO">
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email" />
            <form:errors path="email" cssClass="error" element="p"  />
        </div>
        <div class="form-group">
            <form:input path="firstName" type="text" placeholder="Imię" />
            <form:errors path="firstName" cssClass="error" element="p"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" type="text" placeholder="Nazwisko" />
            <form:errors path="lastName" cssClass="error" element="p"/>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" />
            <form:errors path="password" cssClass="error" element="p" />
        </div>
        <div class="form-group">
            <form:password path="matchingPassword" name="password2" placeholder="Powtórz hasło" />
            <form:errors path="*" cssClass="error" element="p" />
<%--            <c:if test="${not empty pass1NotEqPass2}">--%>
<%--                <p class="error">${pass1NotEqPass2}</p>--%>
<%--            </c:if>--%>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>