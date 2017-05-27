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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    
    <script>
	function modify_value()
	{
    	var hidden_field = document.getElementById('test2');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("testform").Submit();
	}
	</script>
	<script>
	function modify_valueLast()
	{
    	var hidden_field = document.getElementById('test4');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("testform").Submit();
	}</script>
	<script>
	function modify_valueP()
	{
    	var hidden_field = document.getElementById('test1');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("formC").Submit();
	}</script>
	<script>
	function modify_valuePbis()
	{
    	var hidden_field = document.getElementById('test3');
   	 	hidden_field.value = document.getElementById("nameQuestionnary").value;
    	document.getElementById("formC").Submit();
	}</script>
  </head>
<html>
<body>
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>
<%
	String[] skill = Data.findSkills();
	List <String> listSubject = (List<String>) request.getAttribute("listSubject");
	String nameQuestionnary ="";
	if(request.getAttribute("questionnaryVal")!=null)
	{
		nameQuestionnary =(String) request.getAttribute("questionnaryVal");
	}
%>
   <label for="nameQuestionnary">Donner un nom à votre sujet</label> 
   <input type="text" id="nameQuestionnary" name="nameQuestionnary" value="<%=nameQuestionnary%>" size="70"/><br />
   
   <h3>Choisissez une compétence</h3>
   <form id="formC" action="QuestionnaryForm" method="get">
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
      <input type="hidden" name=test1 id="test1" value="">
		<input type="submit" onclick ="modify_valueP()"></input>
   </form>
   
   <h3>Choisissez un sujet</h3>
   <form id="formC" action="QuestionnaryForm" method="get">
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
      <input type="hidden" name=test3 id="test3" value="">
	  <input type="submit" onclick ="modify_valuePbis()"></input>
   </form>
	<form name="testform" id="testform" action="QuestionnaryForm" method="Post" >
		<input type="submit"value="Enregistrer questionnaire" onclick="modify_value()"/>
		<input type="hidden" name=questionnaryName id="test2" value="">
		<input type="submit"value="Enregistrer et continuer la selection" onclick="modify_valueLast()"/>
		<input type="hidden" name=questionnaryNameBis id="test4" value="">
   <% 
   	  /***************
   	  		TEST display QUESTION
   	  							***************/
   	  //Test if question list exist					
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
        List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
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
	      			<h3>Les questions liées au sujet <%= request.getAttribute("subjectNameR") %> </h3><%
	      		}
   	  		}
   	  		else
   	  		{
   	  			%><h3>L'ensemble des questions :</h3><%	
   	  		} %>
   	  		<h4>L'ensemble des questions :</h4>
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
	     				</tr>
	      			</thead>
	     			<tbody>
	 				<%
					for(Question question : list)
					{
		  				System.out.println("--------------" + question.getLanguage());
	
					%>
						<tr>
							<td><input type="checkbox" name="questionSelect" value="<%=question.getIdTech()%>"><%=question.getStatement()%></td>
						 	<td><%=question.getLanguage()%></td>
							<td><%=question.getSkill()%></td>
							<td><%=question.getLevel()%></td>
						 	<td><%=question.getAut()%></td>
						 	<td><%=question.getId()%></td>
						 	<td><%=question.getRelevanceMark()%></td>
						 	
					<%
					}
		}
	 			%>
				</tbody>
			</table>
		</form>
    <%}%>
</body>
</html>