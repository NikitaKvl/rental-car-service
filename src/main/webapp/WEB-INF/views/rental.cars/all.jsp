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
    <title>All Rental Cars</title>
</head>
<body style="background-color:#343a40;">
<h1 style="color: #ffffff" align="center">All Rental Cars:</h1>
<table class="table table-dark table-striped, center">
    <tr>
        <th>ID</th>
        <th>Model</th>
        <th>Manufacturer name</th>
        <th>Manufacturer country</th>
        <th>Renters</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="rentalCar" items="${rentalCars}">
        <tr>
            <td>
                <c:out value="${rentalCar.id}"/>
            </td>
            <td>
                <c:out value="${rentalCar.model}"/>
            </td>
            <td>
                <c:out value="${rentalCar.manufacturer.name}"/>
            </td>
            <td>
                <c:out value="${rentalCar.manufacturer.country}"/>
            </td>
            <td>
                <c:forEach var="renter" items="${rentalCar.renters}">
                    ${renter.id} ${renter.name} ${renter.driverLicenseNumber} <br>
                </c:forEach>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/rental-cars/delete?id=${rentalCar.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<center><a style="font-size:1.5em" href="${pageContext.request.contextPath}/index">Main menu</a></center>
<%@include file="/WEB-INF/views/footer.jsp" %>
</html>