<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<style>
    .center {
        text-align: center;" border="1"
    }
</style>
<html>
<head>
    <title>All renters</title>
</head>
<body style="background-color:#343a40;">
<h1 style="color: #ffffff" align="center">All renters:</h1>
<table class="table table-dark table-striped, center">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Driver License Number</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="renter" items="${renters}">
        <tr>
            <td>
                <c:out value="${renter.id}"/>
            </td>
            <td>
                <c:out value="${renter.login}"/>
            </td>
            <td>
                <c:out value="${renter.name}"/>
            </td>
            <td>
                <c:out value="${renter.driverLicenseNumber}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/renters/delete?id=${renter.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<center><a style="font-size:1.5em" href="${pageContext.request.contextPath}/index">Main menu</a></center>
<%@include file="/WEB-INF/views/footer.jsp" %>
</html>