<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    
    <script>
	function modify_value()
	{
    	var hidden_field = document.getElementById('test2');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("testform").Submit();
	}
	</script>
	<script>
	function modify_valueLast()
	{
    	var hidden_field = document.getElementById('test4');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("testform").Submit();
	}</script>
	<script>
	function modify_valueP()
	{
    	var hidden_field = document.getElementById('test1');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("formC").Submit();
	}</script>
	<script>
	function modify_valuePbis()
	{
    	var hidden_field = document.getElementById('test3');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
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
	List <String> listSubject = (List<String>) request.getAttribute("listSubject");
	String nameQuestionnary ="";
	if(request.getAttribute("questionnaryVal")!=null)
	{
		nameQuestionnary =(String) request.getAttribute("questionnaryVal");
	}
%>
	<div class="container">
	<div class="row mt">
		<legend class="text-center">
		<h2>Création questionnaire</h2>
		 </legend>
	</div>
      <!-- Example row of columns -->
 		 	<div class="form-group">
			    <label for="questionnary" class="col-sm-2 control-label">Titre du questionnaire : </label>
			    <div class="col-sm-8">
          			<input type="text" class="form-control" id="nameQuestionnary" name="nameQuestionnary" value="<%=nameQuestionnary%>" size="70"/>
			    </div>
			  </div>
 <br>
         <div class="col-md-4">
          <h3>Filtrer par compétence</h3>
          <p> Merci de choisir une compétence :
          <div class="col-sm-10">
          <form id="formC" action="QuestionnaryForm" method="get">
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
            <span class="input-group-btn">
        <button type="submit" class="btn btn-default" onclick ="modify_valueP()" aria-label="Left Align">
		 <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	</button>
      </span>
    </div>
   </form>
   </div>
        </div>
        <div class="col-md-4">
          <h3>Filtrer par sujet</h3>
          <p>Merci de choisir un sujet :
   <form id="formC" action="QuestionnaryForm" method="get">
     <div class="col-sm-10">
     <div class="input-group">
      <SELECT name="subjectR" class="form-control">
         <OPTION value="">--- Sujet ---</OPTION>
         <%
         
         for(String subjectName : listSubject ){
         %>
          <OPTION value="<%=subjectName.toString() %>"><%=subjectName.toString() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <input type="hidden" name=test3 id="test3" value="">
            <span class="input-group-btn">
        <button type="submit" class="btn btn-default" aria-label="Left Align">
		 <span class="glyphicon glyphicon-search" onclick ="modify_valuePbis()" aria-hidden="true"></span>
	</button>
      </span>
    </div>
   </form>
   </div>
   </div>
   </div>
  <div class="container">
	<form name="testform" id="testform" action="QuestionnaryForm" method="Post" >
		<input type="hidden" name=questionnaryName id="test2" value="">
		<input type="hidden" name=questionnaryNameBis id="test4" value="">
   <% 
   	  /***************
   	  		TEST display QUESTION
   	  							***************/
   	  //Test if question list exist					
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
        List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
   	  	//Test if the list is empty
   	  	if (list.size() > 0) 
		{ 
   	  		// test if there is a filter
	   	  	if ((request.getAttribute("skillR") != null) || (request.getAttribute("subjectNameR")!=null))
	   	  	{
	   	  		//test if the call is null
	   	  		if(((request.getAttribute("skillR") != null)&&(request.getAttribute("skillR").equals(""))) || ((request.getAttribute("subjectNameR")!=null)&&(request.getAttribute("subjectNameR").equals(""))))
	   	  		{
	   	  			
	   	  			%><h3>L'ensemble des questions :
	   	  			   	  			<input type="submit" class="btn btn-success" value="Enregistrer et continuer la selection" onclick="modify_valueLast()"/>
				<input type="submit" class="btn btn-success"  value="Enregistrer questionnaire" onclick="modify_value()"/>
				</h3><%
	   	  		}
	   	  		//test if the filter is on a skill
	   	  		else if(((request.getAttribute("skillR") != null)&&(request.getAttribute("skillR").equals("")==false)))
	   	  		{%>
      				<h3>Les questions liées à la competence <%= request.getAttribute("skillR") %>
      				   	  			<input type="submit" class="btn btn-success" value="Enregistrer et continuer la selection" onclick="modify_valueLast()"/>
				<input type="submit" class="btn btn-success"  value="Enregistrer questionnaire" onclick="modify_value()"/>
				 </h3><%	
	   	  		}
	   	  		//test if the filter is on a subject
	   	  		else if(((request.getAttribute("subjectNameR")!=null)&&(request.getAttribute("subjectNameR").equals("")==false)))
	   	  		{%>
	      			<h3>Les questions liées au sujet <%= request.getAttribute("subjectNameR") %> 
	      			   	  			<input type="submit" class="btn btn-success" value="Enregistrer et continuer la selection" onclick="modify_valueLast()"/>
				<input type="submit" class="btn btn-success"  value="Enregistrer questionnaire" onclick="modify_value()"/>
				</h3><%
	      		}
   	  		}
   	  		else
   	  		{
   	  			%><h3>L'ensemble des questions : 		
   	  			<input type="submit" class="btn btn-success" value="Enregistrer et continuer la selection" onclick="modify_valueLast()"/>
				<input type="submit" class="btn btn-success"  value="Enregistrer questionnaire" onclick="modify_value()"/>
				</h3><%	
   	  		} %>
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