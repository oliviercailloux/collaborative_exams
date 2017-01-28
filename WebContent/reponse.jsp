<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String auteur = request.getParameter("auteur");
	int identifiant = Integer.parseInt( request.getParameter("id"));
	String enonce = request.getParameter("question");
	String langueI = request.getParameter("langueN");
	String competenceI = request.getParameter("competenceN");
	String niveau = request.getParameter("niveau");
	int nb = Integer.parseInt( request.getParameter("nbRep"));
	int reponse = 1;
	//insertion données première form
	session.setAttribute( "auteur", auteur );
	session.setAttribute( "identifiant", identifiant );
	session.setAttribute( "question", enonce );
	session.setAttribute( "langueN", langueI );
	session.setAttribute( "competenceN", competenceI );
	session.setAttribute( "niveau", niveau );
	session.setAttribute( "nb", nb );
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
	<input type="text" id="idQuestion" name="idQuestion"value="<%= identifiant%>" readonly/> <br /> 
			
	<label for="auteur">Nom Auteur</label> 
	<input type="text" id="auteur" name="auteur" value="<%= auteur%>" readonly/> <br /> 
	
	<form method="post" action="Pform">
		<fieldset>
			<legend>Créer une réponse</legend>
			<p>Vous pouvez enregistrer votre réponse.</p>
			<% while(reponse <= nb )
			{%>
				<label for="textReponse">Réponse <%= reponse %> : </label> 
				<input type="text" id="textReponse<%= reponse %>" name="textReponse<%= reponse %>" value="" size="70" /> <br /> 
			<%
				if(nb==1)
				{%>
					<label for="pos<%= reponse %>">Position Réponse <%= reponse %> : </label> 
					<SELECT name="pos<%= reponse %>">
		          		<OPTION value="V">V</OPTION>
		      		</SELECT> <br />
				<%}
				else
				{%>
					<label for="pos<%= reponse %>"> Position Réponse <%= reponse %> : </label> 
					<SELECT name="pos<%= reponse %>">
		          		<OPTION value="V">V</OPTION>
		          		<OPTION value="F">F</OPTION>
		      		</SELECT> <br />
				<%}
				reponse=reponse+1; 
			}%>
			<!-- NE PAS OUBLIER DE FAIRE UNE FONCTION ONCLICK POUR VEFIRIER SELON CM-CU  -->
			<input type="submit"value="Poster" /> <br />
		</fieldset>
	</form>

</body>
</html>