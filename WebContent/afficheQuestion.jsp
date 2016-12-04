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
   <h3>Choisissez une destination</h3>
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
   	  System.out.println("--------------" + list);
   	  if (list.size() > 0) { %>
      <h3>Les questions liées à la competence <%= request.getAttribute("competenceR") %> </h3>
      <table border="1">
           <tr>
             <th>Enonce</th>
             <th>Competence</th>
             <th>Auteur</th>
             <th>Id</th>
            </tr>
         <%
         for(Question hotel : list){
         %>
           <tr>
             <td><%=hotel.getEnonce()%></td>
             <td><%=hotel.getCompetence()%></td>
             <td><%=hotel.getAut()%></td>
             <td><%=hotel.getId()%></td>
            </tr>
         <%
         }
         %>
       </table>
    <%}}%>
</body>
</html>