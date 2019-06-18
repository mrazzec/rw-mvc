<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Settings</title>
    <style>
        .error {color: red}
    </style>
</head>
<body>

<spring:form modelAttribute="changeUser" method="post" action="/settings">

    <table>
        <tr>
            <td>
                <spring:hidden path="id" />
            </td>
        </tr>

        <tr>
            <td><label>First Name:</label></td>
            <td>
                <spring:input path="firstName" />
                <spring:errors path="firstName" cssClass="error"/>
            </td>

        </tr>

        <tr>
            <td><label>Last Name:</label></td>
            <td>
                <spring:input path="lastName" />
                <spring:errors path="lastName" cssClass="error"/>
            </td>

        </tr>

        <tr>
            <td><label>Email:</label></td>
            <td>
                <spring:input path="email" />
                <spring:errors path="email" cssClass="error"/>
            </td>

        </tr>

        <tr>
            <td><label>Password:</label></td>
            <td>
                <spring:input path="password" />
                <spring:errors path="password" cssClass="error"/>
            </td>

        </tr>

        <tr>
            <td><label></label></td>
            <td><spring:button>Add</spring:button></td>
        </tr>

    </table>
</spring:form>
</body>
</html>
