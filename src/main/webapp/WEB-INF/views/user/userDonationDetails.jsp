<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<section id="user-donation-details" class="login-page">
    <h2>Podsumowanie Twojej darowizny:</h2>
    <div class="fontCustom2">
        <div class="summary">
            <div class="form-section">
                <h4>W dniu ${donation.created} utworzyłeś darowiznę:</h4>
                <ul>
                    <li>
                        <span class="icon icon-bag"></span>
                        <span class="summary--text">
                            <span data-quantity="${donation.quantity}">${donation.quantity}</span>
                            <span data-conjugate-bags>worki </span>
                            <span>z zawartością: </span>
                           <c:forEach items="${donation.categories}" var="category" varStatus="theCount" >
                               <c:choose>
                                   <c:when test="${donation.categories.size() == theCount.count}">
                                       <span style="display: inline-block"> ${category.name}</span>
                                   </c:when>
                                   <c:otherwise>
                                       <span style="display: inline-block"> ${category.name},</span>
                                   </c:otherwise>
                               </c:choose>
                           </c:forEach>

                        </span>
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
                    <table>
                        <tbody>
                            <tr>
                                <td style="color: gray;">Ulica:</td>
                                <td>${donation.street}</td>
                            </tr>
                            <tr>
                                <td style="color: gray;">Miasto:</td>
                                <td>${donation.city}</td>
                            </tr>
                            <tr>
                                <td style="color: gray;">Kod pocztowy:</td>
                                <td>${donation.zipCode}</td>
                            </tr>
                            <tr>
                                <td style="color: gray;">Telefon:</td>
                                <td>${donation.phone}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="form-section--column">
                    <h4>Termin odbioru:</h4>
                    <table>
                        <tbody>
                        <tr>
                            <td style="color: gray;">Data:</td>
                            <td>${donation.pickUpDate}</td>
                        </tr>
                        <tr>
                            <td style="color: gray;">Godzina:</td>
                            <td>${donation.pickUpTime}</td>
                        </tr>
                        <tr>
                            <td style="color: gray;">Uwagi dla kuriera:</td>
                            <td>${donation.pickUpComment}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="form-section--column">
                    <h4>
                        Faktyczny odbiór:
                        <a href="/user/donation/${donation.id}/take-over-update">
                            <button style="background-color: orange; border-radius: 5px;">Aktualizuj</button>
                        </a>
                    </h4>
                    <table>
                        <tbody>
                        <tr>
                            <td style="color: gray;">Zaktualizowano:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty donation.dateOfUserActualizationOfPickUpDetails}">
                                        brak
                                    </c:when>
                                    <c:otherwise>
                                        ${donation.dateOfUserActualizationOfPickUpDetails}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="color: gray;">Odebrano:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${donation.pickedUp == 'true'}" >
                                        tak
                                    </c:when>
                                    <c:otherwise>
                                        nie
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="color: gray;">Data odbioru:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty donation.takeOverDate}">
                                        brak
                                    </c:when>
                                    <c:otherwise>
                                        ${donation.takeOverDate}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <a class="btn" href="/user/donations?sort=created">
        Powrot do listy darów
    </a>
</section>

<%@ include file="../footer.jsp" %>
<script src="<c:url value="/resources/js/user.js" />"></script>
</body>
</html>