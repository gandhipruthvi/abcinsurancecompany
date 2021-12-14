<!--
 * ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Pruthvi Gandhi Student ID: N01415873 Date: 10-12-2021 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
</head>
<%
String table = (String) request.getAttribute("products");
%>
<body>
	<a href="./Admin">Go to Main Page</a>
	<p>
		<a href="./Admin_Product_Add">Add Product</a>
	</p>
	<form action="./Admin_Product" method="post">
		<input name="search" type="text" placeholder="Search Product" /><input
			type="submit" value="Search" />
	</form>
	<%=table%>
</body>
</html>