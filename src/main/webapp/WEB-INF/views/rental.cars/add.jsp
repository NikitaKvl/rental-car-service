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
    <title>Add Rental Car</title>
</head>
<body style="background-color:#343a40;">
<form method="post" id="rentalCar" action="${pageContext.request.contextPath}/rental-cars/add"></form>
<h1 style="color: #ffffff" align="center">Add Rental Car:</h1>
<table class="table table-dark table-striped, center">
    <tr>
        <th>Model</th>
        <th>Manufacturer ID</th>
        <th>Add</th>
    </tr>
    <tr>
        <td>
            <input type="text" name="model" form="rentalCar" required>
        </td>
        <td>
            <input type="number" name="manufacturerId" form="rentalCar" required>
        </td>
        <td>
            <input type="submit" name="add" form="rentalCar">
        </td>
    </tr>
</table>
</body>
<center><a style="font-size:1.5em" href="${pageContext.request.contextPath}/index">Main menu</a></center>
<%@include file="/WEB-INF/views/footer.jsp" %>
</html>