<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
 <%@page import="QuestionV1.Question" %>
 <%@page import="java.util.*"%>
<%
  
   Question questionI = new Question();
   String[] competence = Question.trouveCompetenceP();
   String questionSelectionnee = "";
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
   <% 
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
   	  	ArrayList <Question> list = (ArrayList<Question>) request.getAttribute("listQuestionR");
   	  	if (list.size() > 0) { 
   	  	if ( request.getAttribute("competenceR") != null ){
   	  		if(request.getAttribute("competenceR").equals(""))
   	  		{
   	  			%><h3>L'ensemble des questions :</h3><%
   	  		}
   	  		else{%>
      		<h3>Les questions liées à la competence <%= request.getAttribute("competenceR") %> </h3><%}}
   	  	else
   	  	{
   	  		%><h3>L'ensemble des questions :</h3><%	
   	  	} %>
   	  	<form action="FormReponse" method="get">
      		<table border="1">
          	 <tr>
            	<th>Enonce</th>
             	<th>Langue</th>
             	<th>Competence</th>
             	<th>Auteur</th>
             	<th>Id</th>
             	<th>Variante de</th>
             </tr>
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
       </table></form>
    <%}%>
</body>
</html>