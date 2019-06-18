<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding Carriage</title>
    <style>
        .error {color: red}
    </style>
</head>
<body>

<spring:form modelAttribute="carriageAddForm" method="post" action="/train/addcarriage">

    <table>
        <table>

            <tr>
                <td>
                    <spring:hidden path="id" />
                </td>

            </tr>

            <tr>
                <td><label>Number</label></td>
                <td>
                    <spring:input path="number" />
                    <spring:errors path="number" cssClass="error" />
                </td>

            </tr>

            <tr>
                <td>
                    <spring:hidden path="train.name" />
                </td>

            </tr>

            <tr>
                <td><label>Колличество мест:</label></td>
                <td>
                    <spring:input path="countSeats" />
                    <spring:errors path="countSeats" cssClass="error" />
                </td>
            </tr>

            <tr>
                <td><label>Type:</label></td>
                <td>
                    <spring:input path="type" />
                    <spring:errors path="type" cssClass="error" />
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
