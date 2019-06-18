<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .error {color: red}
    </style>
</head>
<body>
<c:out value="${userException}"/>
<spring:form modelAttribute="logUser" method="post" action="/login">

    <table>
        <table>
            <tr>
                <td><label>Email:</label></td>
                <td>
                    <spring:input path="email" />
                    <spring:errors path="email" cssClass="error" />
                </td>

            </tr>

            <tr>
                <td><label>Pass:</label></td>
                <td>
                    <spring:password path="password" />
                    <spring:errors path="password" cssClass="error" />
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
