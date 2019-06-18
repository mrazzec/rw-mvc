<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding Station</title>
    <style>
        .error {color: red}
    </style>
</head>
<body>

<spring:form modelAttribute="stationAddForm" method="post" action="/station/add">

    <table>
        <table>
            <tr>
                <td><label>Name</label></td>
                <td>
                    <spring:input path="name" />
                    <spring:errors path="name" cssClass="error" />
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
