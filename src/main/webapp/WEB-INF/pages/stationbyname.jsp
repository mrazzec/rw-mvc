<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>
${stationName}:
<c:if test="${user.role == 'ROLE_USER' || user.role == null}">
    <c:forEach var="schedule" items="${schedules}">
        <p>
            Поезд:  <a href="/train/${schedule.train.name}">${schedule.train.name}</a>
            Время прибытия: ${schedule.arrivalDate}
            Время отправления: ${schedule.departureDate} ${schedule.arrivalStation.name}
        </p>
    </c:forEach>
</c:if>

</body>
</html>
