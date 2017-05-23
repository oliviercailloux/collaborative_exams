<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<!-- 
		Permet de récupérer la valeur du nom sujet saisie par la personne
		Et Submit un form d'une autre façon						 			-->
	<script>
	function modify_value()
	{
    	var hidden_field = document.getElementById('test2');
   	 	hidden_field.value = document.getElementById("nomSujet").value;
    	document.getElementById("testform").Submit();
	}</script>
<title>Insert title here</title>
</head>
<body>
<%@page import="mainPackage.*" %>
	<%@page import="java.util.*"%>
<% 
	if ( request.getAttribute("listQ") != null)
	{
		Question list = (Question) request.getAttribute("listQ");
		List <Answer> listR = (List<Answer>) request.getAttribute("listR");
		%>
			<h4>L'ensemble des lists :</h4>
			<form name="testform" id="testform" action="FormTest" method="Post" >
				<input type="submit"value="Enregistrer rep" onclick="modify_value()"/>
				<input type="hidden" name=sujetNom id="test2" value="">
				<table class="table" >
					
	     			<tbody>
	 					<%
					for(Answer rep : listR)
					{
	
					%>
						<tr>
							<td><input type="checkbox" name="listSelect" value="<%=list.getIdTech()%>+<%=rep.getid()%>"><%=rep.getText()%></td>
						</tr>
						 	
					<%
					}}
	 			%>
				</tbody>
			</table>
		</form>
</body>
</html>