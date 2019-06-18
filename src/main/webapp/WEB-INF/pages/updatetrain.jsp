<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Train</title>
    <style>
        .error {color: red}
    </style>
</head>
<body>

<spring:form modelAttribute="trainUpdateForm" method="post" action="/train/update">

    <table>
        <table>
            <tr>
                <td>
                    <spring:hidden path="id" />
                </td>
            </tr>

            <tr>
                <td>
                    <spring:hidden path="name" />
                </td>

            </tr>

            <tr>
                <td><label>Name</label></td>
                <td>
                    <spring:input path="newName" />
                    <spring:errors path="newName" cssClass="error" />
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
