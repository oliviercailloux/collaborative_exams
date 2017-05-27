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
   	 	hidden_field.value = document.getElementById("nomQuestionnaire").value;
    	document.getElementById("testform").Submit();
	}
	</script>
	<script>
	function modify_valueLast()
	{
    	var hidden_field = document.getElementById('test4');
   	 	hidden_field.value = document.getElementById("nomQuestionnaire").value;
    	document.getElementById("testform").Submit();
	}</script>
	<script>
	function modify_valueP()
	{
    	var hidden_field = document.getElementById('test1');
   	 	hidden_field.value = document.getElementById("nomQuestionnaire").value;
    	document.getElementById("formC").Submit();
	}</script>
	<script>
	function modify_valuePbis()
	{
    	var hidden_field = document.getElementById('test3');
   	 	hidden_field.value = document.getElementById("nomQuestionnaire").value;
    	document.getElementById("formC").Submit();
	}</script>
  </head>
<html>
<body>
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>
<%
	String[] competence = Donnees.trouveCompetenceP();
	List <String> listSujet = (List<String>) request.getAttribute("listeSujet");
	String nomQuestionnaire ="";
	if(request.getAttribute("questionnaireVal")!=null)
	{
		nomQuestionnaire =(String) request.getAttribute("questionnaireVal");
	}
%>
   <label for="nomQuestionnaire">Donner un nom à votre sujet</label> 
   <input type="text" id="nomQuestionnaire" name="nomQuestionnaire" value="<%=nomQuestionnaire%>" size="70"/><br />
   
   <h3>Choisissez une compétence</h3>
   <form id="formC" action="QuestionnaireForm" method="get">
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
      <input type="hidden" name=test1 id="test1" value="">
		<input type="submit" onclick ="modify_valueP()"></input>
   </form>
   
   <h3>Choisissez un sujet</h3>
   <form id="formC" action="QuestionnaireForm" method="get">
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
      <input type="hidden" name=test3 id="test3" value="">
	  <input type="submit" onclick ="modify_valuePbis()"></input>
   </form>
	<form name="testform" id="testform" action="QuestionnaireForm" method="Post" >
		<input type="submit"value="Enregistrer questionnaire" onclick="modify_value()"/>
		<input type="hidden" name=questionnaireNom id="test2" value="">
		<input type="submit"value="Enregistrer et continuer la selection" onclick="modify_valueLast()"/>
		<input type="hidden" name=questionnaireNomBis id="test4" value="">
   <% 
   	  /***************
   	  		DEBUT TEST AFFICHAGE QUESTION
   	  							***************/
   	  //Test si la liste des questions existent					
   	  if ( request.getAttribute("listQuestionR") != null)
   	  {
        System.out.println("avantR");
        List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
        System.out.println("apresR");
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
	      			<h3>Les questions liées au sujet <%= request.getAttribute("sujetNomR") %> </h3><%
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
		  				System.out.println("--------------" + question.getLangue());
	
					%>
						<tr>
							<td><input type="checkbox" name="questionSelect" value="<%=question.getIdTech()%>+<%=question.getAut()%>"><%=question.getEnonce()%></td>
						 	<td><%=question.getLangue()%></td>
							<td><%=question.getCompetence()%></td>
							<td><%=question.getNiveau()%></td>
						 	<td><%=question.getAut()%></td>
						 	<td><%=question.getId()%></td>
						 	<td><%=question.getNotePertinence()%></td>
						 	
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