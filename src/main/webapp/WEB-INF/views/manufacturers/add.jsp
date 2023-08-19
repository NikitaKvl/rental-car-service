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
    <title>Manufacturers</title>
</head>
<body style="background-color:#343a40;">
<form method="post" id="manufacturer" action="${pageContext.request.contextPath}/manufacturers/add"></form>
<h1 style="color: #ffffff" align="center" style="color: white">Add manufacturer</h1>
<table class="table table-dark table-striped, center">
    <tr>
        <th>Name</th>
        <th>Country</th>
        <th>Add</th>
    </tr>
    <tr>
        <td>
            <input type="text" name="name" form="manufacturer" required>
        </td>
        <td>
            <input type="text" name="country" form="manufacturer" required>
        </td>
        <td>
            <input type="submit" name="add" form="manufacturer" required>
        </td>
    </tr>
</table>
</body>
<center><a style="font-size:1.5em" href="${pageContext.request.contextPath}/index">Main menu</a></center>
<%@include file="/WEB-INF/views/footer.jsp" %>
</html>