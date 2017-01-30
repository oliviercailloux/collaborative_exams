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
		questionI = Question.getQuestion(IdQPere, CreationEtAffichageQuestionForm.listeQ);
		String[] langue = Donnees.trouveLangueP();
   		String[] competence = Donnees.trouveCompetenceP();
   		String[] niveau = Donnees.trouveNiveau();

   	%>
	<form method="post" action="FormReponse">
		<fieldset>
			<legend>Creation Variante</legend>
			<p>Vous pouvez enregistrer une variante.</p>
			
			<label for="question">Question</label>
			<input type="text" id="question" name="question" value="<%=questionI.getEnonce()%>" size="50" readonly/><br />
			<label for="variante">Variante</label>
			<input type="text" id="variante" name="variante" value="" size="50"></input><br /> 
			
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
			<%
				if(varianteType.equalsIgnoreCase("Amelioration"))
				{%>
					<OPTION value="<%=questionI.getCompetence() %>"><%=questionI.getCompetence() %></OPTION>
				<% }
				else
				{%>
					<OPTION value="">--- Competence ---</OPTION>
        	 	<%
        		 	for(String competenceName : competence )
        		 	{
        		 %>
          				<OPTION value="<%=competenceName %>"><%=competenceName %></OPTION><%}} %>
      		</SELECT> <br />
      		
      		<label for="niveau">Niveau</label> 			
			<SELECT name="niveau">
         	<OPTION value="">--- Niveau ---</OPTION>
        	 <%
        		 for(String niv : niveau )
        		 {
        	 %>
          			<OPTION value="<%=niv %>"><%=niv %></OPTION>
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
			