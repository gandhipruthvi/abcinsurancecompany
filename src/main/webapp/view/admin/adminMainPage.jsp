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
<title>Admin Page</title>
</head>
<%
String report = (String) request.getAttribute("report");
%>
<body>
<div class="hholder"><span class="heading">ABC Company</span> <a href="DeleteCookies" class="logout">Logout</a></div>
	<h2><%=report%></h2>
	<h3><a href="./Admin_User">Users</a> <a href="./Admin_Product">Products</a> <a href="./Admin_Claims">Claims</a></h3>
</body>
</html>