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
    <title>CreerQuestion</title>
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
	<form method="post" action="FormInsert">
		<fieldset>
			<legend>Creation Question</legend>
			<p>Vous pouvez enregistrer une question.</p>

			<label for="question">Question</label>
			<input type="text" id="question" name="question" value="test" size="50" /> 			
			<label for="coef">Coefficient question</label> 			
			<SELECT name="coef">
         	<OPTION value="">--- Coefficient ---</OPTION>
        	 <%
        		 for(Integer i : coef )
        		 {
        	 %>
          			<OPTION value="<%=i %>"><%=i %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br />
			
			<label for="type">Type question</label> 			
			<SELECT name="type">
         	<OPTION value="">--- Type ---</OPTION>
        	 <%
        		 for(String type : typeQ )
        		 {
        	 %>
          			<OPTION value="<%=type %>"><%=type %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br />
			
			<label for="author">Nom Auteur</label> 
			<input type="text" id="author" name="author" value="tonton" /> <br /> 
      		
			<label for="language">Langue</label> 			
			<SELECT name="languageN">
         	<OPTION value="">--- Langue ---</OPTION>
        	 <%
        		 for(String languageName : language )
        		 {
        	 %>
          			<OPTION value="<%=languageName %>"><%=languageName %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br /> 
      		
      	
      		
      		
			<label for="skill">Competence</label> 
			<SELECT name="skillN">
         	<OPTION value="">--- Competence ---</OPTION>
        	 <%
        		 for(String skillName : skill )
        		 {
        	 %>
          			<OPTION value="<%=skillName %>"><%=skillName %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br />
      		
      		<label for="level">Niveau</label> 
			<SELECT name="level">
         	<OPTION value="">--- Niveau ---</OPTION>
        	 <%
        		 for(String l : level )
        		 {
        	 %>
          			<OPTION value="<%=l %>"><%=l %></OPTION>
         	<%
        	 	 }
        	 %>
      		</SELECT> <br />
      		 
			<label for="id"> Identifiant question </label> 
			<input type="text" id="id" name="id"value="1" /> <br /> 
			
			<label for="nbAnswer"> Nombre de Réponses souhaitées </label> 
			<SELECT name="nbAnswer">
         		<OPTION value="">--- Nombre ---</OPTION>
         		<OPTION value="1">1</OPTION>
          		<OPTION value="2">2</OPTION>
          		<OPTION value="3">3</OPTION>
          		<OPTION value="4">4</OPTION>
          		<OPTION value="5">5</OPTION>
      		</SELECT> <br /> 

			<input type="submit"value="Poster" /> <br /> 
		</fieldset>
	</form>
	
	

		
</body>
</html>