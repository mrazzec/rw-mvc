<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>
List of Trains:
<c:if test="${user.role == 'ROLE_USER' || user == null}">
    <c:forEach var="train" items="${trains}">
        <p><a href="/train/${train.name}">${train.name}</a></p>
    </c:forEach>
</c:if>

<c:if test="${user.role == 'ROLE_MANAGER' || user.role == 'ROLE_ADMIN'}">
    <a href="${pageContext.request.contextPath}/train/add">Add new Train</a>
    <c:forEach var="train" items="${trains}">
        <p>
        <a href="/train/update/${train.name}">${train.name}</a>
        <a href="/train/addcarriage/${train.name}">add new carriage</a>
        <c:forEach var="carriage" items="${train.carriages}">
            <p>
                <a href="/train/updatecarriage/${carriage.id}">${carriage.number}</a>
                <a href="/train/deletecarriage/${carriage.id}">Удалить</a>
            </p>
        </c:forEach>
        <a href="/train/delete/${train.name}">Удалить</a>
        </p>
    </c:forEach>
</c:if>
</body>
</html>
