<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Twój profil</title>
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

<sec:authorize access="isAuthenticated()">
<section class="login-page">



    <h2>Twój profil</h2>
    <div class="form-group fontCustom">
        <table>
            <tbody>
            <tr>
                <th>Imię:</th>
                <td><sec:authentication property="principal.user.firstName"/></td>
                <td rowspan="3">
                    <p><a class="specialCustomButtons" href="/user/update/<sec:authentication property="principal.user.id"/>">Edytuj dane</a></p>
                    <p><a class="specialCustomButtons" href="/user/update/password/<sec:authentication property="principal.user.id"/>">Zmień hasło</a></p>
                </td>
            </tr>
            <tr>
                <th>Nazwisko:</th>
                <td><sec:authentication property="principal.user.lastName"/></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><sec:authentication property="principal.user.email"/></td>
            </tr>
            </tbody>
        </table>
    </div>

    </section>
</sec:authorize>

<%@ include file="../footer.jsp" %>
</body>
</html>