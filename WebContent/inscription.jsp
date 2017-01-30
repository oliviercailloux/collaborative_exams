<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
</head>
<body>
	<form method="post" action="inscription">
		<fieldset>
			<legend>Inscription</legend>
			<p>Venez vous inscrire.</p>

			<label for="login">Identifiant</label>
			<input type="text" id="login" name="login" value="" size="50" /> <br /> 
			
			<label for="pass">Mot de passe</label> 
			<input type="text" id="pass" name="pass" value="" /> <br /> 
			<label for="pass">Nom</label> 
			<input type="text" id="nom" name="nom" value="" /> <br />
			<label for="pass">Prenom</label> 
			<input type="text" id="prenom" name="prenom" value="" /> <br />
			<label for="pass">Mail</label> 
			<input type="text" id="mail" name="mail" value="" /> <br />
			<input type="submit"value="Poster" /> <br />
            <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
		</fieldset>
	</form>
</body>
</html>