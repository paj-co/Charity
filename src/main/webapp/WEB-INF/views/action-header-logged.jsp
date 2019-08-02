<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('USER')">
<ul class="nav--actions">
    <li class="logged-user">
        Witaj Agata
        <ul class="dropdown">
            <li><a href="#">Profil</a></li>
            <li><a href="#">Ustawienia</a></li>
            <li><a href="#">Moje zbiórki</a></li>
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