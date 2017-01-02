<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
   	  <p>Enonce : <strong><%=list.getEnonce()%></strong></p>
   	  <p>Langue de la question : <strong><%=list.getLangue()%></strong></p>
   	  <p>La competence de la question : <strong><%=list.getCompetence()%></strong></p>
   	  <p>L'auteur de la question : <strong><%=list.getAut()%></strong></p>
   	  <p>L'identifiant de la question : <strong><%=list.getId()%></strong></p>
   	  <p>Variante de : <strong><%=list.getVar()%></strong></p>
   	  <%if(list.getVar()!="-")
   	  {%>
   		  <p>opinion de l'auteur : <strong><%=list.getOpinion()%></strong></p>
   	  <%}
   	   %>
   	  		
   	  	
   	  <h2>Les réponses sont :</h2>
		<ol>
	    	<%
	    	for(int i=0; i<list.reponseR().size();i++)
	    	{%>
	    		<li><%=list.reponseR().get(i).getText() %></li>
	    	<%} %>
		</ol>
		<form method="post" action="FormulaireVariante.jsp">
		<fieldset>
			<legend>Création d'une variante</legend>
			<SELECT name="varianteType">
         		<OPTION value="">--- Variante ---</OPTION>	
         		<OPTION value="VarianteSimple">Variante Simple</OPTION>
          		<OPTION value="Amelioration">Amelioration</OPTION>
      		</SELECT>
			<BUTTON type="submit"name="IdQPere" value="<%=list.getIdTech()%>+<%=list.getAut()%>">Poster</BUTTON> <br />
		</fieldset>
	</form>
</body>
</html>