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
   	 	hidden_field.value = document.getElementById("nameSubject").value;
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
   	 	hidden_field.value = document.getElementById("nameSubject").value;
    	document.getElementById("formC").Submit();
	}</script>
  </head>
<html>
<body>
     <div class="navbar navbar-default navbar-default-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>  Collaborative Exams</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Question <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	            	<li><a href="FormQ.jsp">Création Question</a></li>
	            	<li><a href="Pform">Liste Question</a></li>
	            </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sujet <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	            	<li><a href="createSubject.jsp">Création Sujet</a></li>
	            </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Questionnaire <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	            	<li><a href="QuestionnaryForm">Création Questionnaire</a></li>
	            	<li><a href="displayQuestionnary">Liste Questionnaire</a></li>
	            </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	<%@page import="collaborative_exams.*" %>
	<%@page import="java.util.*"%>
	<%
	  	String[] skill = Data.findSkills();
		String nameSubject = "";
		if(request.getAttribute("subjectTest")!=null)
		{
			nameSubject =(String) request.getAttribute("subjectTest");
		}
	   	
	%>
	<div class="container">
<div class="row mt">
<legend class="text-center">
<h2>Création sujet</h2>
 </legend>
</div>

	<div class="container">
      <!-- Example row of columns -->
      <div class="row">

            <div class="form-group">
			    <h3>Donner un nom à votre sujet :</h3>
				<input type="text" class="form-control" id="nameSubject" name="nameSubject" value="<%=nameSubject%>"/><br />		  
          </div>
                  <div class="col-md-6">
          <h3>Choisir la compétence du sujet :</h3>
          <p>           <div class="col-sm-10">
          <form name="formC" id="formC"action="SubjectForm" method="get">
          <div class="input-group">
      <SELECT name="skillR" class="form-control">
         <OPTION value="">--- Competence ---</OPTION>
         <%
         for(String skillName : skill ){
         %>
          <OPTION value="<%=skillName %>"><%=skillName %></OPTION>
         <%
         }
         %>
      </SELECT>
      <input type="hidden" name=test1 id="test1" value="">
            <span class="input-group-btn">
        <button type="submit" class="btn btn-default" onclick ="modify_valueP()" aria-label="Left Align">
		 <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	</button>
      </span>
    </div>
   </form>
   </div></p>
          </div>
      	</div>
	<% 
	if ( request.getAttribute("listQuestionR") != null)
	{
		List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
		if (list.size() > 0) 
		{%>
			
			<form name="testform" id="testform" action="SubjectForm" method="Post" >
			<h4>L'ensemble des questions : <input type="submit" class="btn btn-success" value="Enregistrer sujet" onclick="modify_value()"/> </h4>
				<input type="hidden" name="nameSubject" id="test2" value="">
				<table class="table table-hover">
					<thead>
	   	 				<tr>
	     					<th>Enonce</th>
					      	<th>Langue</th>
					      	<th>Competence</th>
					      	<th>Niveau</th>
					      	<th>Auteur</th>
					      	<th>Id</th>
					      	<th>Pertinence</th>
	     				</tr>
	      			</thead>
	     			<tbody>
	 				<%
					for(Question question : list)
					{
		  				System.out.println("--------------" + question.getLanguage());
	
					%>
						<tr>
							<td>
							<div class="checkbox">
						    	<label>
						          <input type="checkbox" name="questionSelect" value="<%=question.getIdTech()%>"><%=question.getStatement()%>
						        </label>
						    </div>
							</td>
						 	<td><div class="checkbox"><%=question.getLanguage()%></div></td>
							<td><div class="checkbox"><%=question.getSkill()%></div></td>
							<td><div class="checkbox"><%=question.getLevel()%></div></td>
						 	<td><div class="checkbox"><%=question.getAut()%></div></td>
						 	<td><div class="checkbox"><%=question.getId()%></div></td>
						 	<td><div class="checkbox"><%=question.getRelevanceMark()%></div></td>
						 	
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
   </div>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>