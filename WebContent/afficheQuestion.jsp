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
  </head>
<html>
<body>
 <%@page import="QuestionV1.*" %>
 <%@page import="java.util.*"%>
<%
	String[] competence = Question.trouveCompetenceP();
	ArrayList <Sujet> listSujet = (ArrayList<Sujet>) request.getAttribute("listeSujet");
%>
   <h3>Choisissez une compétence</h3>
   <form action="Pform" method="get">
     Merci de choisir une compétence :
      <SELECT name="competenceR">
         <OPTION value="">--- Competence ---</OPTION>
         <%
         for(String competenceName : competence ){
         %>
          <OPTION value="<%=competenceName %>"><%=competenceName %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit">Chercher</BUTTON>
   </form>
   
   <h3>Choisissez un sujet</h3>
   <form action="Pform" method="get">
     Merci de choisir une compétence :
      <SELECT name="sujetR">
         <OPTION value="">--- Sujet ---</OPTION>
         <%
         for(Sujet sujetName : listSujet ){
         %>
          <OPTION value="<%=sujetName.getNomSujet() %>"><%=sujetName.getNomSujet() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit">Chercher</BUTTON>
   </form>
   <% 
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
   	  	ArrayList <Question> list = (ArrayList<Question>) request.getAttribute("listQuestionR");
   	  	if (list.size() > 0) { 
   	  	if ( request.getAttribute("competenceR") != null )
   	  	{
   	  		if(request.getAttribute("competenceR").equals(""))
   	  		{
   	  			%><h3>L'ensemble des questions :</h3><%
   	  		}
   	  		else
   	  		{%>
      			<h3>Les questions liées à la competence <%= request.getAttribute("competenceR") %> </h3><%
      		}
   	  	}
   	  	else
   	  	{
   	  		%><h3>L'ensemble des questions :</h3><%	
   	  	} %>
   	  	<a href="creationSujet.jsp" class="pull-left btn btn-default">Créer un sujet</a>
   	  	<form action="FormReponse" method="get">
   	  		
      		<table class="table" >
      		<thead>
          	 <tr>
            	<th>Enonce</th>
             	<th>Langue</th>
             	<th>Competence</th>
             	<th>Auteur</th>
             	<th>Id</th>
             	<th>Variante de</th>
             </tr>
             </thead>
             <tbody>
         <%
         	for(Question question : list){
          	  	System.out.println("--------------" + question.getLangue());

         %>
           	<tr>
            	<td><%=question.getEnonce()%></td>
             	<td><%=question.getLangue()%></td>
            	<td><%=question.getCompetence()%></td>
             	<td><%=question.getAut()%></td>
             	<td><%=question.getId()%></td>
             	<td><%=question.getVar()%></td>
             	<td><BUTTON type="submit" name="testA" value="<%=question.getIdTech()%>+<%=question.getAut()%>">afficher détails</BUTTON></td>
            </tr>
         <%
         }}
         %>
       </tbody></table></form>
    <%}%>
</body>
</html>