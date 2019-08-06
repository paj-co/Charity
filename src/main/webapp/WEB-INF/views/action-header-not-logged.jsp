<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<sec:authorize access="!isAuthenticated()">
<ul class="nav--actions">
    <li><a href="/login">Zaloguj</a></li>
    <li class="highlighted"><a href="/register">Załóż konto</a></li>
</ul>
</sec:authorize>