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
    <title>Moje dary | Charity</title>
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
    <h2>Podsumowanie Twojej darowizny:</h2>
    <div class="fontCustom2">
        <div data-step="5">
            <div class="summary">
                <div class="form-section">
                    <h4>Oddajesz:</h4>
                    <ul>
                        <li>
                            <span class="icon icon-bag"></span>
                            <span class="summary--text">
                                ${donation.quantity} worki<br>
                               <c:forEach items="${donation.categories}" var="category">
                                   <span style="display: block"> ${category.name}</span>
                               </c:forEach></span>
                        </li>

                        <li>
                            <span class="icon icon-hand"></span>
                            <span class="summary--text">${donation.institution.name}</span>
                        </li>
                    </ul>
                </div>

                <div class="form-section form-section--columns address">
                    <div class="form-section--column">
                        <h4>Adres odbioru:</h4>
                        <ul>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru:</h4>
                        <ul>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>