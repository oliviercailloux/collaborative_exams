<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String author =(String) request.getAttribute("aut");
	String nb =  (String) request.getAttribute("nbAnswer");
	String id =(String) request.getAttribute("id");

	int answer = 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Réponse</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
<html>
<body>

	<label for="idQuestion"> Identifiant question </label> 
	<input type="text" id="idQuestion" name="idQuestion"value="<%= id%>" readonly/> <br /> 
			
	<label for="author">Nom Auteur</label> 
	<input type="text" id="author" name="author" value="<%= author%>" readonly/> <br /> 
	<form method="post" action="Pform">
		<input type="hidden" id="idQuest" name="iduest" value="<%= id %>" size="50" /> <br /> 
		<input type="hidden" id="nb" name="nb" value="<%= nb %>" size="50" /> 
		<fieldset>
			<legend>Créer une réponse</legend>
			<p>Vous pouvez enregistrer votre réponse.</p>
			<% while(answer <= Integer.parseInt(nb) )
			{%>
				<label for="textAnswer">Réponse <%= answer %> : </label> 
				<input type="text" id="textAnswer<%= answer %>" name="textAnswer<%= answer %>" value="" size="70" /> <br /> 
			<%
				if(Integer.parseInt(nb)==1)
				{%>
					<label for="pos<%= answer %>">Position Réponse <%= answer %> : </label> 
					<SELECT name="pos<%= answer %>">
		          		<OPTION value="V">V</OPTION>
		      		</SELECT> <br />
				<%}
				else
				{%>
					<label for="pos<%= answer %>"> Position Réponse <%= answer %> : </label> 
					<SELECT name="pos<%= answer %>">
		          		<OPTION value="V">V</OPTION>
		          		<OPTION value="F">F</OPTION>
		      		</SELECT> <br />
				<%}
				answer=answer+1; 
			}%>
			<!-- NE PAS OUBLIER DE FAIRE UNE FONCTION ONCLICK POUR VEFIRIER SELON CM-CU  -->
			<input type="submit"value="Poster" /> <br />
		</fieldset>
	</form>

</body>
</html>