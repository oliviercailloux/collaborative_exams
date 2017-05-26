<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
<html>
<body>
 <%@page import="mainPackage.*" %>
 <%@page import="java.util.*"%>
<%
	String[] competence = Donnees.trouveCompetenceP();
	List <String> listSujet = (List<String>) request.getAttribute("listeSujet");
	
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
         
         for(String sujetName : listSujet ){
         %>
          <OPTION value="<%=sujetName.toString() %>"><%=sujetName.toString() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit">Chercher</BUTTON>
   </form>
   <% 
   	  /***************
   	  		DEBUT TEST AFFICHAGE QUESTION
   	  							***************/
   	  //Test si la liste des questions existent					
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
   	  	List <Question> list = (List<Question>) request.getAttribute("listQuestionR");

   	  	//Test si la liste est vide == des questions créées ou pas
   	  	if (list.size() > 0) 
   	  	{ 
   	  		// test si le filtrage des question par question par compétence ou sujet est demandé
	   	  	if ((request.getAttribute("competenceR") != null) || (request.getAttribute("sujetNomR")!=null))
	   	  	{
	   	  		//test si la demande est null remplace un futur contrôle javascript
	   	  		if(((request.getAttribute("competenceR") != null)&&(request.getAttribute("competenceR").equals(""))) || ((request.getAttribute("sujetNomR")!=null)&&(request.getAttribute("sujetNomR").equals(""))))
	   	  		{
	   	  			%><h3>L'ensemble des questions :</h3><%
	   	  		}
	   	  		//test si le filtrage est sur la compétence
	   	  		else if(((request.getAttribute("competenceR") != null)&&(request.getAttribute("competenceR").equals("")==false)))
	   	  		{%>
      				<h3>Les questions liées à la competence <%= request.getAttribute("competenceR") %> </h3><%	
	   	  		}
	   	  		//test si le filtrage est sur un sujet
	   	  		else if(((request.getAttribute("sujetNomR")!=null)&&(request.getAttribute("sujetNomR").equals("")==false)))
	   	  		{%>
	      			<h3>Les questions liées au sujet <%= request.getAttribute("") %> </h3><%
	      		}
   	  		}
   	  		else
   	  		{
   	  			%><h3>L'ensemble des questions :</h3><%	
   	  		} %>
   	  		<a href="creationSujet.jsp" class="pull-left btn btn-default">Créer un sujet</a>
   	  		
   	  		<br>
   	  		<br>
   	  		
   	  		
   	  		
   	  		
 <form  method="post" action="exportXml">
     Choississez le sujet à exporter :
      <SELECT name="subject">
         <OPTION value="">--- Sujet ---</OPTION>
         <%
         for(String sujetName : listSujet ){
         %>
          <OPTION value="<%=sujetName.toString() %>"><%=sujetName.toString() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <input type="submit" value="Export"></input>
   </form>
   
   	  		
   	  		<form action="FormReponse" method="get">
   	  		
   	  		
      		<table class="table" >
      		<thead>
          	 <tr>
            	<th>Enonce</th>
             	<th>Langue</th>
             	<th>Competence</th>
             	<th>Niveau</th>
             	<th>Auteur</th>
             	<th>Id</th>
             	<th>Pertinence</th>
             	<th>Sujet</th>
             	<th>Variante de</th>
             </tr>
             </thead>
             <tbody>
         	<%
         	for(Question question : list)
         	{
          	  	System.out.println("--------------" + question.getLangue());%>
	           	<tr>
	            	<td><%=question.getEnonce()%></td>
	             	<td><%=question.getLangue()%></td>
	            	<td><%=question.getCompetence()%></td>
	            	<td><%=question.getNiveau()%></td>
	             	<td><%=question.getAut()%></td>
	             	<td><%=question.getId()%></td>
	             	<td><%=question.getNotePertinence()+" ("+question.getNbVotePertinence()+" vote(s))"%></td>
	             	<td><%=question.getNomSujet()%></td>
	             	<td><%=question.getVar()%></td>
	             	<td><BUTTON type="submit" name="getDetail" value="<%=question.getIdTech()%>+<%=question.getAut()%>+<%=question.getId()%>">afficher détails</BUTTON></td>
	            </tr>
         	<%
         	}
         }
   	  	
         %>
       </tbody></table></form>
    <%}%>
</body>
</html>