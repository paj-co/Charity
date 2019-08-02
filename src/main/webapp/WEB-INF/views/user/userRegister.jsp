<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%--    <form>--%>
<%--        <div class="form-group">--%>
<%--            <input type="email" name="email" placeholder="Email" />--%>
<%--        </div>--%>
<%--        <div class="form-group">--%>
<%--            <input type="password" name="password" placeholder="Hasło" />--%>
<%--        </div>--%>
<%--        <div class="form-group">--%>
<%--            <input type="password" name="password2" placeholder="Powtórz hasło" />--%>
<%--        </div>--%>

<%--        <div class="form-group form-group--buttons">--%>
<%--            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>--%>
<%--            <button class="btn" type="submit">Załóż konto</button>--%>
<%--        </div>--%>
<%--    </form>--%>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email" />
            <form:errors path="email" cssClass="error" element="p" />
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" />
            <form:errors path="password" cssClass="error" element="p" />
        </div>
        <div class="form-group">
            <form:password path="password2" placeholder="Powtórz hasło" />
            <c:if test="${not empty pass1NotEqPass2}">
                <p class="error">${pass1NotEqPass2}</p>
            </c:if>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>