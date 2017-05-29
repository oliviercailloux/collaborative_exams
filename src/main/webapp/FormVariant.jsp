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
    <title>Creer Variation</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
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
	<%@page import="collaborative_exams.*" %>
	<%
  		Question questionI = new Question();
		questionI = (Question) request.getAttribute("questionSearch");
		String variantType = (String) request.getAttribute("varType");
		String idParent = (String) request.getAttribute("idParent");
		String[] language = Data.findLanguage();
   		String[] skill = Data.findSkills();
   		String[] level = Data.findLevel();

   	%>
	<form method="post" action="FormAnswer" class="form-horizontal">
	<input type="hidden" id="idParent" name="idParent" value="<%= idParent %>" size="50" /> <br /> 
		<fieldset>
			<h2 class="text-center">Creation Variante</h2>
			<hr>
			<p>Vous pouvez enregistrer une variante.</p>
			
			<div class="form-group">
			    <label for="question" class="col-sm-2 control-label">Question</label>
			    <div class="col-sm-8">
			      <input readonly type="text" class="form-control" name="question" id="question" value="<%=questionI.getStatement()%>">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="variant" class="col-sm-2 control-label">Variante</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" name="variant" id="variant" placeholder="Variante">
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
		         			
		        	 <%
				if(variantType.equalsIgnoreCase("Improvement"))
				{%>
					<OPTION value="<%=questionI.getSkill() %>"><%=questionI.getSkill() %></OPTION>
				<% }
				else
				{%>
					<OPTION value="">--- Competence ---</OPTION>
        	 	<%
        		 	for(String skillName : skill )
        		 	{
        		 %>
          				<OPTION value="<%=skillName %>"><%=skillName %></OPTION><%}} %>
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
			    <label for="opinion" class="col-sm-2 control-label">Opinion</label>
			    <div class="col-sm-2">
			<SELECT name="opinionN" class="form-control">
         		
         		<%
				if(variantType.equalsIgnoreCase("Improvement"))
				{%>
					<OPTION value="Objectivement Meilleure">Objectivement Meilleure</OPTION>
			  <%}
				else
				{%>
					<OPTION value="">--- Opinion ---</OPTION>
        	 		<OPTION value="plus subtile">plus subtile</OPTION>
        	 		<OPTION value="aussi subtile">aussi subtile</OPTION>
        	 		<OPTION value="moins subtile">moins subtile</OPTION>
        	 		<OPTION value="ni l'un ni l'autre">ni l'un ni l'autre</OPTION><%} %>
    		</SELECT>
    		</div>
    		</div>
    		<div class="col-md-4 text-center"> 
			<input class="btn btn-success" type="submit"value="Poster" />
			</div>
		</fieldset>
	</form>
	</div>
</body>
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</html>


			