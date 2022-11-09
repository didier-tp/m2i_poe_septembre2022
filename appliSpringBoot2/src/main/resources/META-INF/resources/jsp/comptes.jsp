<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>comptes</title>
</head>
<body>
     <h3>client</h3>
     <ul>
         <li> premom = ${client.prenom} </li>
         <li> nom= ${client.nom} </li>
     </ul>
	<h3>comptes du client</h3>
	<table border="1">
		<tr>
			<th>numero</th>
			<th>label</th>
			<th>solde</th>
		</tr>
		<c:forEach var="c" items="${comptes}">
			<tr>
				<td>${c.numero}</td>
				<td>${c.label}</td>
				<td>${c.solde}</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="index.html">retour index</a>
	
</body>
</html>