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
<div class="container">
<div class="row mt">
<legend class="text-center">
<h2>Liste des questions</h2>
 </legend>
</div>
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>
<%
	String[] skill = Data.findSkills();
	List <String> listSubject = (List<String>) request.getAttribute("listSubject");
	
%>
<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h3>Filtrer par compétence</h3>
          <p> Merci de choisir une compétence :
          <div class="col-sm-10">
          <form action="Pform" method="get">
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
        <button type="submit" class="btn btn-default" aria-label="Left Align">
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
   <form action="Pform" method="get">
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
            <span class="input-group-btn">
        <button type="submit" class="btn btn-default" aria-label="Left Align">
		 <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	</button>
      </span>
    </div>
   </form>
   </div>
   </div>
           <div class="col-md-4">
          <h3>Export au format XML :</h3>
          <p>Export du sujet au format XML : </p>
          <p><form action="exportXml" method="post">
               <div class="col-sm-10">
			     <div class="input-group">
			      <SELECT name="subject" class="form-control">
			         <OPTION value="">--- Sujet ---</OPTION>
			         <%
			         for(String sujetName : listSubject ){
			         %>
			          <OPTION value="<%=sujetName.toString() %>"><%=sujetName.toString() %></OPTION>
			         <%
			         }
			         %>
			      </SELECT>
			      <span class="input-group-btn">
			        <button type="submit" value="Export" class="btn btn-default" aria-label="Left Align">
					 <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
				</button>
				      </span>
   				 </div>
   				 </div>
   </form></p>
        </div>
       </div>
      </div>  
   </div>
    	  		<br>
   	  		<br> 
   	  		<div class="container">
   <% 
   	  /***************
   	  		TEST DISPLAY QUESTION
   	  							***************/
   	  //Test if the question list exist				
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
	   	  			%><h2>L'ensemble des questions :</h2><%
	   	  		}
	   	  		//test if the filter is on a skill
	   	  		else if(((request.getAttribute("skillR") != null)&&(request.getAttribute("skillR").equals("")==false)))
	   	  		{%>
      				<h3>Les questions liées à la competence <%= request.getAttribute("skillR") %><%	
	   	  		}
	   	  		//test if the filter is on a subject
	   	  		else if(((request.getAttribute("subjectNameR")!=null)&&(request.getAttribute("subjectNameR").equals("")==false)))
	   	  		{%>
	      			<h3>Les questions liées au sujet <%= request.getAttribute("subjectNameR") %><%
	      		}
   	  		}
   	  		else
   	  		{
   	  			%><h3>L'ensemble des questions :<%	
   	  		} %>
	  		<a href="createSubject.jsp" class="btn btn-success">Créer un sujet</a>
   	  		<a href="FormQ.jsp" class="btn btn-success">Créer une question</a></h3>
   	  		<form action="FormAnswer" method="get">
      		<table class="table table-hover">
      		<thead>
          	 <tr>
            	<th>Enonce</th>
             	<th>Langue</th>
             	<th>Competence</th>
             	<th>Niveau</th>
             	<th>Id</th>
             	<th>Pertinence</th>
             	<th>Variante de</th>
             	<th>Plus d'info</th>
             </tr>
             </thead>
             <tbody>
         	<%
         	for(Question question : list)
         	{
          	  	System.out.println("--------------" + question.getLanguage());%>
	           	<tr>
	            	<td><%=question.getStatement()%></td>
	             	<td><%=question.getLanguage()%></td>
	            	<td><%=question.getSkill()%></td>
	            	<td><%=question.getLevel()%></td>
	             	<td><%=question.getId()%></td>
	             	<td><%=question.getRelevanceMark()+" ("+question.getNbVoteRelevance()+" vote(s))"%></td>
	             	<td><%=question.getVar()%></td>
	             	<td>
	             	<button type="submit" class="btn btn-info" aria-label="Left Align" name="getDetail" value="<%=question.getIdTech()%>">
					  <span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span> Détails
					</button>
					</td>
	            </tr>
         	<%
         	}
         }
   	  	
         %>
       </tbody></table></form>
    <%}%>
</div>
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>