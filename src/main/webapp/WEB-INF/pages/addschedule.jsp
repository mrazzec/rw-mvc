<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding Scchedule</title>
    <style>
        .error {
            color: red
        }
    </style>
</head>
<body>

<spring:form modelAttribute="scheduleAddForm" method="post" action="/schedule/add">

    <table>
        <table>

            <tr>
                <td>
                    <spring:hidden path="id"/>
                </td>

            </tr>

            <tr>
                <td><label>Station Departure</label></td>
                <td>
                    <spring:select path="departureStation.name">
                        <c:forEach var="station" items="${stations}">
                            <spring:option value="${station.name}" label="${station.name}" />
                        </c:forEach>
                    </spring:select>
                    <spring:errors path="departureStation.name" cssClass="error"/>
                </td>

            </tr>

            <tr>
                <td><label>Station Arrival</label></td>
                <td>
                    <spring:select path="arrivalStation.name">
                        <c:forEach var="station" items="${stations}">
                            <spring:option value="${station.name}" label="${station.name}" />
                        </c:forEach>
                    </spring:select>
                    <spring:errors path="arrivalStation.name" cssClass="error"/>
                </td>

            </tr>

            <tr>
                <td><label>Date Departure</label></td>
                <td>
                    <spring:input type="datetime-local" value="2019-06-10T20:20:00" path="departureDate" />
                    <spring:errors path="departureDate" cssClass="error"/>
                </td>

            </tr>

            <tr>
                <td><label>Date Arrival</label></td>
                <td>
                    <spring:input type="datetime-local" value="2019-06-10T20:20:00" path="arrivalDate" />
                    <spring:errors path="arrivalDate" cssClass="error"/>
                </td>

            </tr>

            <tr>
                <td><label>Train</label></td>
                <td>
                    <spring:select path="train.name">
                        <c:forEach var="train" items="${trains}">
                            <spring:option value="${train.name}" label="${train.name}" />
                        </c:forEach>
                    </spring:select>
                    <spring:errors path="train.name" cssClass="error"/>
                </td>

            </tr>

            <tr>
                <td><label></label></td>
                <td><spring:button>Submit</spring:button></td>
            </tr>
        </table>
    </table>

</spring:form>

</body>
</html>
