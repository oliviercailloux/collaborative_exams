<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript"> 
function afficheForm(id) 
{ 
	if (document.getElementById(id).style.visibility == "hidden")
			document.getElementById(id).style.visibility = "visible"; 
	else	document.getElementById(id).style.visibility = "hidden"; 
} 
</script> 
  </head>
<html>
<body>
 <%@page import="QuestionV1.Question" %>
 <%@page import="java.util.*"%>

   <% 
   	  Question list= new Question();
   	  if ( request.getAttribute("Question") != null)
   	  {
   	  	list = (Question) request.getAttribute("Question");
   	  }%>
   	  <h1>Les informations de la question : <%=list.getEnonce()%> </h1>
   	  <p>Enonce : <strong><%=list.getEnonce()%></strong> <a href="Difference?param1=<%=list.getIdTech()%>&param2=<%=list.getAut()%>">Difference originale</a></p>
   	  <p>Langue de la question : <strong><%=list.getLangue()%></strong></p>
   	  <p>La competence de la question : <strong><%=list.getCompetence()%></strong></p>
   	  <p>Le niveau de la question : <strong><%=list.getNiveau()%></strong></p>
   	  <p>L'auteur de la question : <strong><%=list.getAut()%></strong></p>
   	  <p>L'identifiant de la question : <strong><%=list.getId()%></strong></p>
   	  <p>Pertinence de la question : <strong><%=list.getNotePertinence()%></strong></p><button type="button" onclick="afficheForm('formPertinence');">Noter</button>
   	  <form id="formPertinence" action="ServletPertinence" method="get" style="visibility: hidden">
   	  	<label for="notePertinence">Note de pertinence</label>
		<SELECT name="notePertinence">
         		<OPTION value="">--- Note ---</OPTION>
         		<OPTION value="1">1</OPTION>
          		<OPTION value="2">2</OPTION>
          		<OPTION value="3">3</OPTION>
          		<OPTION value="4">4</OPTION>
          		<OPTION value="5">5</OPTION>
      		</SELECT> <br /> 
		<BUTTON type="submit" name="setNotePertinence" value="<%=list.getIdTech()%>+<%=list.getAut()%>">Notez !</BUTTON> <br />
	</form>
   	  <p>Variante de : <strong><%=list.getVar()%></strong></p>
   	  <%if(list.getVar()!="-")
   	  {%>
   		  <p>opinion de l'auteur : <strong><%=list.getOpinion()%></strong></p>
   	  <%}
   	   %>
   	  		
   	  	
   	  <h2>Les réponses sont :</h2>
   	    <table border="0">
   	    <tr>
            <th>Intitulé</th>
             <th>Position</th>
        </tr>
		<ol>
	    	<%
	    	for(int i=0; i<list.reponseR().size();i++)
	    	{%>
	    		<tr>
	    			<td><li><%=list.reponseR().get(i).getText() %></li></td>
	    			<td><%=list.reponseR().get(i).getPos() %></td>
	    		</tr>
	    	<%} %>
		</ol></table>
		<form method="post" action="FormulaireVariante.jsp">
		<fieldset>
			<legend>Création d'une variante</legend>
			<SELECT name="varianteType">
         		<OPTION value="">--- Variante ---</OPTION>	
         		<OPTION value="VarianteSimple">Variante Simple</OPTION>
          		<OPTION value="Amelioration">Amelioration</OPTION>
      		</SELECT>
      		
      		<!-- Envoi l'idenfiant de la variante -->
      		
			<BUTTON type="submit"name="IdQPere" value="<%=list.getIdTech()%>+<%=list.getAut()%>+<%=list.getId()%>">Poster</BUTTON> <br />
		</fieldset>
	</form>
</body>
</html>