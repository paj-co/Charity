<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
<ul class="nav--actions">
    <li class="logged-user">
        Witaj <sec:authentication property="principal.user.firstName"/>
        <ul class="dropdown">
            <li><a href="/user/profile">Profil</a></li>
            <li><a href="#">Ustawienia</a></li>
            <li><a href="/user/donations?sort=created">Moje dary</a></li>
            <li>
                <form action="<c:url value="/logout"/>" method="post">
                    <input type="submit" value="Wyloguj"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </li>
        </ul>
    </li>
</ul>
</sec:authorize>