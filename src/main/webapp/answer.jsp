<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String author =(String) request.getAttribute("aut");
	String nb =  (String) request.getAttribute("nbAnswer");
	String statement = (String) request.getAttribute("statement");
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
<div class="container">
			<legend>Créer une réponse</legend>

<blockquote>
			  <p><b>Question :</b>
			  <br>
			   <%=statement %>
			   </p>
			  <footer><b>Auteur :</b> <%=author %></footer>
			  <footer><b>Identifiant question :</b> <%=id %></footer>
			</blockquote>
	<form method="post" action="Pform" class="form-horizontal">
		<input type="hidden" id="idQuest" name="idQuest" value="<%= id %>" size="50" /> <br /> 
		<input type="hidden" id="nb" name="nb" value="<%= nb %>" size="50" /> 
		<fieldset>
			<p>Vous pouvez enregistrer votre réponse.</p>
			
			<% while(answer <= Integer.parseInt(nb) )
			{%>
				<div class="form-group">
			    <label for="textAnswer" class="col-sm-2 control-label">Réponse N°<%= answer %></label>
				    <div class="col-sm-2">
				      <input type="text" class="form-control" name="textAnswer<%= answer %>" id="textAnswer<%= answer %>" placeholder="">
				    </div>
			  	
				<%
				if(Integer.parseInt(nb)==1)
				{%>
		          		<input type="hidden" name="pos<%= answer %>" value="V">
		          		</div>
				<%}
				else
				{%>
				<label class="radio-inline">
				  <input type="radio" name="pos<%= answer %>" id="pos<%= answer %>" value="V"> Vraie
				</label>	
				<label class="radio-inline">
				  <input type="radio" name="pos<%= answer %>" id="pos<%= answer %>" value="F"> Faux
				</label>	
				</div>	    
				<%}
				answer=answer+1; 
			}%>
			<!-- NE PAS OUBLIER DE FAIRE UNE FONCTION ONCLICK POUR VEFIRIER SELON CM-CU  -->
		<div class="col-md-4 text-center"> 
		     <input type="submit" value="Poster" class="btn btn-success"/>
		</div>
		</fieldset>
	</form>
</div>
</body>
</html>