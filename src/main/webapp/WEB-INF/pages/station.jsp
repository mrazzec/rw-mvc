<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>
List of Stations:
<c:if test="${user.role == 'ROLE_USER' || user.role == null}">
    <c:forEach var="station" items="${stations}">
        <a href="/station/${station.name}">${station.name}</a>
    </c:forEach>
</c:if>

<c:if test="${user.role == 'ROLE_MANAGER' || user.role == 'ROLE_ADMIN'}">
    <a href="${pageContext.request.contextPath}/station/add">Add new Station</a>
    <c:forEach var="station" items="${stations}">
        <p>
        <a href="/station/update/${station.name}">${station.name}</a>
        <a href="/station/delete/${station.name}">Удалить</a>
        </p>
    </c:forEach>
</c:if>
</body>
</html>
