<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
List of Schedules:

<c:if test="${user.role == 'ROLE_MANAGER' || user.role == 'ROLE_ADMIN'}">
    <a href="${pageContext.request.contextPath}/schedule/add">Add new Schedule</a>
    <c:forEach var="schedule" items="${schedules}">
        <p>
        <a href="/schedule/update/${schedule.id}">${schedule}</a>
        <%--<a href="/train/addcarriage/${train.name}">add new carriage</a>--%>
        <%--<c:forEach var="carriage" items="${train.carriages}">--%>
            <%--<p>--%>
                <%--<a href="/train/updatecarriage/${carriage.id}">${carriage.number}</a>--%>
                <%--<a href="/train/deletecarriage/${carriage.id}">Удалить</a>--%>
            <%--</p>--%>
        <%--</c:forEach>--%>
        <a href="/schedule/delete/${schedule.id}">Удалить</a>
        </p>
    </c:forEach>
</c:if>
</body>
</html>
