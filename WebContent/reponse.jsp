<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Réponse</title>
</head>
<body>

	<form method="post" action="FormReponse">
		<fieldset>
			<legend>Créer une réponse</legend>
			<p>Vous pouvez enregistrer votre réponse.</p>

			<label for="idQuestion"> Identifiant question </label> 
			<input type="text" id="idQuestion" name="idQuestion"value="" /> <br /> 
			
			<label for="auteur">Nom Auteur</label> 
			<input type="text" id="auteur" name="auteur" value="" /> <br /> 
			
			<label for="textReponse">Réponse</label> 
			<input type="text" id="textReponse" name="textReponse" value="" size="70" /> <br /> 

			
			<input type="submit"value="Poster" /> <br />
		</fieldset>
	</form>
	
	<% 
   	  if (request.getAttribute("idQuestion") != null && request.getAttribute("auteur")!= null && request.getAttribute("textReponse")!= null)
   	  {
	 %>
      <h3>La reponse</h3>
      <table border="1">
           <tr>
             <th>IdQuestion</th>
             <th>Auteur</th>
             <th>Text</th>
            </tr>
           <tr>
             <td><%=request.getAttribute("idQuestion")%></td>
             <td><%=request.getAttribute("auteur")%></td>
             <td><%=request.getAttribute("textReponse")%></td>
            </tr>
         <%
         }
         %>
       </table>
</body>
</html>