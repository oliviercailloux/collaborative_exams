<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CreerQuestion</title>
</head>
<body>
	<%@page import="QuestionV1.Question" %>
	<%
  		Question questionI = new Question();
		String[] langue = Question.trouveLangueP();
   		String[] competence = Question.trouveCompetenceP();
   	%>
	<form method="post" action="Pform">
		<fieldset>
			<legend>Creation Question</legend>
			<p>Vous pouvez enregistrer une question.</p>

			<label for="question">Question</label>
			<input type="text" id="question" name="question" value="" size="50" /> <br /> 
			
			<label for="auteur">Nom Auteur</label> 
			<input type="text" id="auteur" name="auteur" value="" /> <br /> 
      		
			<label for="langue">Langue</label> 			
			<SELECT name="langueN">
         	<OPTION value="">--- Langue ---</OPTION>
        	 <%
        		 for(String langueName : langue )
        		 {
        	 %>
          			<OPTION value="<%=langueName %>"><%=langueName %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br /> 
      		
			<label for="competence">Competence</label> 
			<SELECT name="competenceN">
         	<OPTION value="">--- Competence ---</OPTION>
        	 <%
        		 for(String competenceName : competence )
        		 {
        	 %>
          			<OPTION value="<%=competenceName %>"><%=competenceName %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br /> 
			<label for="identifiant"> Identifiant question </label> 
			<input type="text" id="id" name="id"value="" /> <br /> 
			
			<input type="submit"value="Poster" /> <br />
		</fieldset>
	</form>
</body>
</html>