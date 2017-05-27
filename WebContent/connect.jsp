<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CreerQuestion</title>
</head>
<body>

	<form method="post" action="connect">
		<fieldset>
			<legend>Creation Question</legend>
			<p>Vous pouvez enregistrer une question.</p>

			<label for="login">Identifiant</label>
			<input type="text" id="login" name="login" value="" size="50" /> <br /> 
			
			<label for="pass">Mot de passe</label> 
			<input type="text" id="pass" name="pass" value="" /> <br /> 
			<input type="submit"value="Poster" /> <br />
            <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
		</fieldset>
	</form>
</body>
</html>