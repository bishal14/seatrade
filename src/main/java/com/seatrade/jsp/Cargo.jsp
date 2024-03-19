<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.seatrade.entity.Cargo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cargo List</title>
</head>
<body>
<h2>List of Cargo</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Source Harbour</th>
        <th>Destination Harbour</th>
        <th>Value</th>
    </tr>
    <c:forEach var="cargo" items="${cargos}">
        <tr>
            <td>${cargo.cargoId}</td>
            <td>${cargo.sourceHarbour}</td>
            <td>${cargo.destinationHarbour}</td>
            <td>${cargo.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
