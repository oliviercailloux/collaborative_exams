<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String IdQPere = request.getParameter("IdQPere");
	String varianteType = request.getParameter("varianteType");	
	//insertion données première form
	session.setAttribute( "idPere", IdQPere );
	session.setAttribute( "varianteType", varianteType );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CreerQuestion</title>
</head>
<body>
	<%@page import="mainPackage.*" %>
	<%
  		Question questionI = new Question();
		questionI = Question.getQuestion(IdQPere, CreateDisplayQuestionForm.listQ);
		String[] language = Data.findLanguage();
   		String[] skill = Data.findSkill();
   		String[] level = Data.findLevel();

   	%>
	<form method="post" action="FormReponse">
		<fieldset>
			<legend>Creation Variante</legend>
			<p>Vous pouvez enregistrer une variante.</p>
			
			<label for="question">Question</label>
			<input type="text" id="question" name="question" value="<%=questionI.getStatement()%>" size="50" readonly/><br />
			<label for="variante">Variante</label>
			<input type="text" id="variante" name="variante" value="" size="50"></input><br /> 
			
			<label for="auteur">Nom Auteur</label> 
			<input type="text" id="auteur" name="auteur" value="" /> <br /> 
      		
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
			<%
				if(varianteType.equalsIgnoreCase("Amelioration"))
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
      		
			<label for="identifiant"> Identifiant question </label> 
			<input type="text" id="id" name="id"value="" /> <br /> 
			<label for="opinion">Opinion</label> 
			<SELECT name="opinionN">
         		
         		<%
				if(varianteType.equalsIgnoreCase("Amelioration"))
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
    		</SELECT> <br /> 
			<input type="submit"value="Poster" /> <br />
		</fieldset>
	</form>
</body>
</html>
			