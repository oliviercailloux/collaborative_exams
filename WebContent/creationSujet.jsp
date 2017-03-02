<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
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
	<!-- 
		Permet de récupérer la valeur du nom sujet saisie par la personne
		Et de récupérer sa valeur et de l'afficher si la personne choisi une compétence
																						-->
	<script>
	function modify_valueP()
	{
    	var hidden_field = document.getElementById('test1');
   	 	hidden_field.value = document.getElementById("nomSujet").value;
    	document.getElementById("formC").Submit();
	}</script>
  </head>
<html>
<body>
	<%@page import="mainPackage.*" %>
	<%@page import="java.util.*"%>
	<%
	  	String[] competence = Donnees.trouveCompetenceP();
		String nomSujet = "";
		if(request.getAttribute("sujetTest")!=null)
		{
			nomSujet =(String) request.getAttribute("sujetTest");
		}
	   	
	%>
	<legend>Création De Sujet</legend>
	<p>Vous pouvez enregistrer un sujet.</p>
	
	<label for="nomSujet">Donner un nom à votre sujet</label> 
	<input type="text" id="nomSujet" name="nomSujet" value="<%=nomSujet%>" size="70"/><br />
	
	<h3>Veuillez choisir une compétence pour créer un sujet</h3>
	<form name="formC" id="formC"action="SujetForm" method="get">
		Merci de choisir une compétence :
		<SELECT name="competenceR">
			<OPTION value="">--- Competence ---</OPTION>
			<%
			for(String competenceName : competence ){
			%>
			<OPTION value="<%=competenceName %>"><%=competenceName %></OPTION>
			<%
			}
			%>
		</SELECT>
		<input type="hidden" name=test1 id="test1" value="">
		<input type="submit" onclick ="modify_valueP()"></input>
	</form>
	
	<% 
	if ( request.getAttribute("listQuestionR") != null)
	{
		List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
		if (list.size() > 0) 
		{%>
			<h4>L'ensemble des questions :</h4>
			<form name="testform" id="testform" action="SujetForm" method="Post" >
				<input type="submit"value="Enregistrer sujet" onclick="modify_value()"/>
				<input type="hidden" name=sujetNom id="test2" value="">
				<table class="table" >
					<thead>
	   	 				<tr>
	     					<th>Enonce</th>
					      	<th>Langue</th>
					      	<th>Competence</th>
					      	<th>Niveau</th>
					      	<th>Auteur</th>
					      	<th>Id</th>
					      	<th>Pertinence</th>
					      	<th>Variante de</th>
	     				</tr>
	      			</thead>
	     			<tbody>
	 				<%
					for(Question question : list)
					{
		  				System.out.println("--------------" + question.getLangue());
	
					%>
						<tr>
							<td><input type="checkbox" name="questionSelect" value="<%=question.getIdTech()%>+<%=question.getAut()%>"><%=question.getEnonce()%></td>
						 	<td><%=question.getLangue()%></td>
							<td><%=question.getCompetence()%></td>
							<td><%=question.getNiveau()%></td>
						 	<td><%=question.getAut()%></td>
						 	<td><%=question.getId()%></td>
						 	<td><%=question.getNotePertinence()%></td>
						 	
					<%
					}
	 			%>
				</tbody>
			</table>
		</form>
		
	    <%
	    }
		else
		{%>
			<h4>Pas de question pour ce sujet</h4>
		<%}
	}%>
   
</body>
</html>