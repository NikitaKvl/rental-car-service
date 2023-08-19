<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<style>--%>
<%--    <%@include file='/WEB-INF/views/css/table_dark.css' %>--%>
<%--</style>--%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<style>
    .center {
        text-align: center;" border="1"
    }
</style>
<html>
<head>
    <title>Rental Car Service</title>
</head>
<body style="background-color:#343a40;">
<h1 style="color: #ffffff" align="center">Welcome, ${userName}</h1>
<table class="table table-dark table-striped, center">
    <tr>
        <th></th>
    </tr>
    <tr>
        <th>Links to display Renters, Cars, Manufacturers</th>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/renters">All Renters</a></td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/rental-cars">All Rental Cars</a></td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/manufacturers">All Manufacturers</a></td>
    </tr>
    <tr>
        <th></th>
    </tr>
    <tr>
        <th>Links to create Renter, Car, Manufacturer</th>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/renters/add">Create new Renter</a></td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/rental-cars/add">Create new Rental Car</a></td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/manufacturers/add">Create new Manufacturer</a></td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/rental-cars/renters/add">Add Renter to Rental Car</a></td>
    </tr>
</table>
</body>
<%@include file="footer.jsp" %>
</html>