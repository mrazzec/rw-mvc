<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trains</title>
</head>
<body>
${trainName}:
<c:if test="${user.role == 'ROLE_USER' || user.role == null}">
    <c:forEach var="schedule" items="${schedules}">
        <p>
            Отправляется с:  <a href="/station/${schedule.departureStation.name}">${schedule.departureStation.name}</a>
            В ${schedule.departureDate}
        </p>
        <p>
            Прибывает в:  <a href="/station/${schedule.arrivalStation.name}">${schedule.arrivalStation.name}</a>
            В ${schedule.arrivalDate}
        </p>
    </c:forEach>
</c:if>

</body>
</html>
