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
    <h2>Moje dary:</h2>
    <div class="fontCustom2">
        <table id="dataTable" border = "1">
            <thead>
                <tr>
                    <th>Instytucja</th>
<%--                    <th>Kategorie</th>--%>
<%--                    <th>Worki</th>--%>
<%--                    <th>Adres odbioru</th>--%>
<%--                    <th>Data i czas odbioru</th>--%>
<%--                    <th>Uwagi dla kuriera</th>--%>
                    <th>
                        Utworzono
                        <a href="/user/donations?sort=created">
                            <button class="sortButton">
                                Sortuj
                            </button>
                        </a>
                    </th>
                    <th>
                        Odebrane
                        <a href="/user/donations?sort=picked-up">
                            <button class="sortButton">
                                Sortuj
                            </button>
                        </a>
                    </th>
                    <th>
                        Data odebrania
                        <a href="/user/donations?sort=take-over-date">
                            <button class="sortButton">
                                Sortuj
                            </button>
                        </a>
                    </th>
                    <th>Akcja</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>Instytucja</th>
<%--                    <th>Kategorie</th>--%>
<%--                    <th>Worki</th>--%>
<%--                    <th>Adres odbioru</th>--%>
<%--                    <th>Data i czas odbioru</th>--%>
<%--                    <th>Uwagi dla kuriera</th>--%>
                    <th>Utworzono</th>
                    <th>Odebrane</th>
                    <th>Data odebrania</th>
                    <th>Akcja</th>
                </tr>
            </tfoot>
            <tbody>
            <c:forEach items="${donations}" var="donation" >
                <tr>
                    <td>${donation.institution.name}</td>
<%--                    <td>--%>
<%--                        <c:forEach items="${donation.categories}" var="category">--%>
<%--                            <span style="display: block"> ${category.name}</span>--%>
<%--                        </c:forEach>--%>
<%--                    </td>--%>
<%--                    <td>${donation.quantity}</td>--%>
<%--                    <td>--%>
<%--                        ${donation.street}--%>
<%--                        ${donation.city}--%>
<%--                        ${donation.zipCode}--%>
<%--                        ${donation.phone}--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                            ${donation.pickUpDate}--%>
<%--                            ${donation.pickUpTime}--%>
<%--                    </td>--%>
<%--                    <td>${donation.pickUpComment}</td>--%>
                    <td style="text-align: center">${donation.created}</td>
                    <td style="text-align: center">
                        <c:choose>
                            <c:when test="${donation.pickedUp == true}">
                                Tak
                            </c:when>
                            <c:otherwise>
                                Nie
                            </c:otherwise>
                        </c:choose>
                    </td>
                <%--<td>${donation.takeOverDate}</td>--%>
                    <td style="text-align: center">
                        <c:choose>
                            <c:when test="${empty donation.takeOverDate}">
                                brak
                            </c:when>
                            <c:otherwise>
                                ${donation.takeOverDate}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center">
                        <a style="color: #0f6848; font-weight: bold" href="/user/donation/${donation.id}">Szczegóły</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>