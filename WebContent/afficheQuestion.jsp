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
   	  if (list.size() > 0) { %>
      <h3>Les questions liées à la competence <%= request.getAttribute("competenceR") %> </h3>
      <table border="1">
           <tr>
             <th>Enonce</th>
             <th>Langue</th>
             <th>Competence</th>
             <th>Auteur</th>
             <th>Id</th>
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
            </tr>
         <%
         }
         %>
       </table>
    <%}}%>
</body>
</html>