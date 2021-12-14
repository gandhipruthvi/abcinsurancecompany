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
<%
String name = (String) request.getAttribute("name");
%>
<%
String model = (String) request.getAttribute("model");
%>
<%
String year = (String) request.getAttribute("year");
%>
<%
String id = (String) request.getAttribute("id");
%>
<body>
	<form action="./Admin_Product_Add" method="post">
		<input name="id" type="hidden" value="<%=id%>" />
		<table border="0">
			<tr>
				<td><label class="name_lbl">Name: </label></td>
				<td><input name="name" type="text" class="name_ipt"
					value="<%=name%>" /></td>
			</tr>
			<tr>
				<td><label class="model_lbl">Model: </label></td>
				<td><input name="model" type="text" class="model_ipt"
				value="<%=model%>" /></td>
			</tr>
			<tr>
				<td><label class="ym_lbl">Year of Manufacture: </label></td>
				<td><input name="year" type="text" class="ym_ipt" value="<%=year%>" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</form>
</body>
</html>