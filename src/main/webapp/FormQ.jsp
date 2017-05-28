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
   	<div class="container">
	<form method="post" action="FormInsert" class="form-horizontal">
		<fieldset>
			<h2>Creation Question</h2>
			<p>Vous pouvez enregistrer une question.</p>
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
	

		
</body>
</html>