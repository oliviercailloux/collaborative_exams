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
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>
<%
	String[] skill = Data.findSkills();
	List <String> listSubject = (List<String>) request.getAttribute("listSubject");
%>
   <h3>Choisissez une compétence</h3>
   <form action="Pform" method="get">
     Merci de choisir une compétence :
      <SELECT name="skillR">
         <OPTION value="">--- Competence ---</OPTION>
         <%
         for(String skillName : skill ){
         %>
          <OPTION value="<%=skillName %>"><%=skillName %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit">Chercher</BUTTON>
   </form>
   
   <h3>Choisissez un sujet</h3>
   <form action="Pform" method="get">
     Merci de choisir une compétence :
      <SELECT name="subjectR">
         <OPTION value="">--- Sujet ---</OPTION>
         <%
         for(String subjectName : listSubject ){
         %>
          <OPTION value="<%=subjectName.toString() %>"><%=subjectName.toString() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit">Chercher</BUTTON>
   </form>
   <% 
   	  /***************
   	  		DISPLAY QUESTIONS
   	  							***************/
   	  //Test if the question list exist					
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
   	  	ArrayList <Question> list = (ArrayList<Question>) request.getAttribute("listQuestionR");
   	  	//Test if the list is empty
   	  	if (list.size() > 0) 
   	  	{ 
   	  		// test if there is a filter
	   	  	if ((request.getAttribute("skillR") != null) || (request.getAttribute("subjectNameR")!=null))
	   	  	{
	   	  		//test if the call is null
	   	  		if(((request.getAttribute("skillR") != null)&&(request.getAttribute("skillR").equals(""))) || ((request.getAttribute("subjectNameR")!=null)&&(request.getAttribute("subjectNameR").equals(""))))
	   	  		{
	   	  			%><h3>L'ensemble des questions :</h3><%
	   	  		}
	   	  		//test if the filter is on a skill
	   	  		else if(((request.getAttribute("skillR") != null)&&(request.getAttribute("skillR").equals("")==false)))
	   	  		{%>
      				<h3>Les questions liées à la competence <%= request.getAttribute("skillR") %> </h3><%	
	   	  		}
	   	  		//test if the filter is on a subject
	   	  		else if(((request.getAttribute("subjectNameR")!=null)&&(request.getAttribute("subjectNameR").equals("")==false)))
	   	  		{%>
	      			<h3>Les questions liées au sujet <%= request.getAttribute("") %> </h3><%
	      		}
   	  		}
   	  		else
   	  		{
   	  			%><h3>L'ensemble des questions :</h3><%	
   	  		} %>
   	  		<a href="createSubject.jsp" class="pull-left btn btn-default">Créer un sujet</a>
   	  		<form action="FormAnswer" method="get">
   	  		
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
          	  	System.out.println("--------------" + question.getLanguage());%>
	           	<tr>
	            	<td><%=question.getStatement()%></td>
	             	<td><%=question.getLanguage()%></td>
	            	<td><%=question.getSkill()%></td>
	            	<td><%=question.getLevel()%></td>
	             	<td><%=question.getAut()%></td>
	             	<td><%=question.getId()%></td>
	             	<td><%=question.getRelevanceMark()+" ("+question.getNbVoteRelevance()+" vote(s))"%></td>
	             	<td><%=question.getSubjectName()%></td>
	             	<td><%=question.getVar()%></td>
	             	<td><BUTTON type="submit" name="getDetail" value="<%=question.getIdTech()%>">afficher détails</BUTTON></td>
	            </tr>
         	<%
         	}
         }
   	  	
         %>
       </tbody></table></form>
    <%}%>
</body>
</html>