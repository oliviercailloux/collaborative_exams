<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <title>Creer Question</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
<body>
	<%@page import="collaborative_exams.*" %>
	<%
		String[] language = Data.findLanguage();
   		String[] skill = Data.findSkills();
   		String[] level = Data.findLevel();
   		String[] typeQ = Data.findTypeQ();
   		Integer[] coef = Data.findCoef();
   		RequestDispatcher rd;
   	%>
   	
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
	<form method="post" action="FormInsert" class="form-horizontal">
		<fieldset>
			<h2 class="text-center">Creation Question</h2>
			<hr>
			<p class="text-center">Vous pouvez enregistrer une question.</p>
			<br>
			  <div class="form-group">
			    <label for="question" class="col-sm-2 control-label">Question</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" name="question" id="question" placeholder="Enoncé">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="type" class="col-sm-2 control-label">Type question</label>
			    <div class="col-sm-2">
			      	<SELECT name="type" class="form-control">
			         	<OPTION value="">--- Type ---</OPTION>
			        	 <%
			        		 for(String type : typeQ )
			        		 {
			        	 %>
			          			<OPTION value="<%=type %>"><%=type %></OPTION>
			         	<%
			        	 	 }
			        	 %>
		      		</SELECT>
			    </div>
			  </div>
			 <div class="form-group">
			    <label for="author" class="col-sm-2 control-label">Nom Auteur</label>
			    <div class="col-sm-2">
			      <input type="text" class="form-control" name="author" id="author" placeholder="Auteur">
			    </div>
			  </div>
      		 <div class="form-group">
			    <label for="language" class="col-sm-2 control-label">Langue</label>
			    <div class="col-sm-2">
					<SELECT name="languageN" class="form-control">
         				<OPTION value="">--- Langue ---</OPTION>
			        	 <%
			        		 for(String languageName : language )
			        		 {
			        	 %>
			          			<OPTION value="<%=languageName %>"><%=languageName %></OPTION>
			         	<%
			        	 	 }
			        	 %>
      				</SELECT>   
      			</div>
			  </div>
			  <div class="form-group">
			    <label for="skill" class="col-sm-2 control-label">Competence</label>
			    <div class="col-sm-2">
					<SELECT name="skillN" class="form-control">
		         			<OPTION value="">--- Competence ---</OPTION>
		        	 <%
		        		 for(String skillName : skill )
		        		 {
		        	 %>
		          			<OPTION value="<%=skillName %>"><%=skillName %></OPTION>
		         	<%
		        	 	 }
		        	 %>
      				</SELECT>
			    </div>
			  </div>
      		  <div class="form-group">
			    <label for="level" class="col-sm-2 control-label">Niveau</label>
			    <div class="col-sm-2">
					<SELECT name="level" class="form-control">
			         	<OPTION value="">--- Niveau ---</OPTION>
		        	 <%
		        		 for(String l : level )
		        		 {
		        	 %>
		          			<OPTION value="<%=l %>"><%=l %></OPTION>
		         	<%
		        	 	 }
		        	 %>
      				</SELECT>
     			 </div>
			  </div>
      		 <div class="form-group">
			    <label for="id" class="col-sm-2 control-label">Identifiant question</label>
			    <div class="col-sm-2">
			      <input type="text" class="form-control" name="id" id="id" placeholder="Identifiant question">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="nbAnswer" class="col-sm-2 control-label">Nombre de Réponses souhaitées</label>
			    <div class="col-sm-2">
					<SELECT name="nbAnswer" class="form-control">
			       		<OPTION value="">--- Nombre ---</OPTION>
			         		<OPTION value="1">1</OPTION>
			          		<OPTION value="2">2</OPTION>
			          		<OPTION value="3">3</OPTION>
			          		<OPTION value="4">4</OPTION>
			          		<OPTION value="5">5</OPTION>
			      		</SELECT>
     			 </div>
			  </div>
		</fieldset>
		<div class="col-md-4 text-center"> 
		     <input type="submit" value="Poster" class="btn btn-success"/>
		</div>
		
	</form>
	</div>
	
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
		
</body>
</html>