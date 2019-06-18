<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<html>
<body>
<a href="${pageContext.request.contextPath}/station">Станции</a>
<a href="${pageContext.request.contextPath}/train">ПОезда</a>
<c:if test="${user == null}">
    <a href="${pageContext.request.contextPath}/reg">Регистрация</a>
    <a href="${pageContext.request.contextPath}/login">Вход</a>
</c:if>

<c:if test="${user != null}">
    <a href="${pageContext.request.contextPath}/settings">Настройки</a>
    <a href="${pageContext.request.contextPath}/logout">Выход</a>
</c:if>

<c:if test="${user.role == 'ROLE_ADMIN'}">
    <%--users page whree admin will delete users--%>
</c:if>


<%--${error}--%>
<%--${sessionScope.get("user")}--%>
<%--${userException}--%>
<%--<c:forEach var="user" items="${users}">--%>
    <%--<p>${user.name}</p>--%>
<%--</c:forEach>--%>
</body>
</html>
