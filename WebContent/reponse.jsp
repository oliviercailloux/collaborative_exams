<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Réponse</title>
</head>
<body>

	<form method="post" action="Pform">
		<fieldset>
			<legend>Créer une réponse</legend>
			<p>Vous pouvez enregistrer votre réponse.</p>

			<label for="identifiant"> Identifiant question </label> 
			<input type="text" id="id" name="id"value="" /> <br /> 
			
			<label for="auteur">Nom Auteur</label> 
			<input type="text" id="auteur" name="auteur" value="" /> <br /> 
			
			<label for="auteur">Réponse</label> 
			<input type="text" id="reponse" name="reponse" value="" size="70" /> <br /> 

			
			<input type="submit"value="Poster" /> <br />
		</fieldset>
	</form>
</body>
</html>