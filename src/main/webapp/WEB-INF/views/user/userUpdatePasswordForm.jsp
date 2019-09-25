<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Uaktualnienie danych | Charity</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/> "/>
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
    <h2>Uaktualnij swoje hasło:</h2>
    <form:form method="post" modelAttribute="userDTO">
        <form:hidden path="id" />
        <div class="form-group fontCustom">
            Imię: ${userDTO.firstName}
        </div>
        <div class="form-group fontCustom">
            Nazwisko: ${userDTO.lastName}
        </div>
        <div class="form-group fontCustom">
            Obecne hasło:
            <form:password path="oldPassword" placeholder="Obecne hasło" />
            <form:errors path="oldPassword" cssClass="error" element="p" />
            <c:if test="${not empty oldPasswordError}">
                <p class="error">${oldPasswordError}</p>
            </c:if>
        </div>
        <div class="form-group fontCustom">
            Hasło:
            <form:password path="password" placeholder="Nowe hasło" />
            <form:errors path="password" cssClass="error" element="p" />
        </div>
        <div class="form-group fontCustom">
            Powtórz hasło:
            <form:password path="matchingPassword" placeholder="Powtórz nowe hasło"/>
            <form:errors cssClass="error" element="p" />
                <%--            <c:if test="${not empty pass1NotEqPass2}">--%>
                <%--                <p class="error">${pass1NotEqPass2}</p>--%>
                <%--            </c:if>--%>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz</button>
            <a href="/user/profile" class="btn btn--without-border">Anuluj</a>
        </div>
    </form:form>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>