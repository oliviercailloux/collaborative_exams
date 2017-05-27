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
    <script>
	function modify_value()
	{
    	var hidden_field = document.getElementById('test2');
   	 	hidden_field.value = document.getElementById("questionnaryR").value;
    	document.getElementById("FormAnswer").Submit();
	}</script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jspdf.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
	<script>
	function print_page()
	{
		
		var specialElementHandlers = { 
			    '#editor': function (element, renderer) { 
			        return true; 
			    } 
			};
		;
		var doc = new jsPDF();
		alert("<%=(String) request.getAttribute("questionnaryR")%> au format PDF !");
	    doc.fromHTML($('#content').html(), 15, 15, { 
	        'width': 190, 
	            'elementHandlers': specialElementHandlers 
	    }); 
	    
	    doc.save('sample-page.pdf'); 
	}
</script>
  </head>
<html>
<body>

 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>
<%
	List <String> listQuestionnary = (List<String>) request.getAttribute("listQuestionnary");
	
%>
  <div class="container">
   <h3>Choisissez un Questionnaire</h3>
   <div class ="row mt">
   <form action="displayQuestionnary" method="get">
     Merci de choisir un questionnaire :
      <SELECT name="questionnaryR" class="selectpicker">
         <OPTION value="<%= (String) request.getAttribute("questionnaryR")%>"><%= (String) request.getAttribute("questionnaryR")%></OPTION>
         <%
         
         for(String subjectQuestionnary : listQuestionnary ){
         %>
          <OPTION value="<%=subjectQuestionnary.toString() %>"><%=subjectQuestionnary.toString() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit" class="btn btn-success">Chercher</BUTTON>
   </form>
   </div>
  <% 
  if (request.getAttribute("listQuestionR")== null) {}
   	  /***************
   	  		DEBUT TEST AFFICHAGE QUESTION
   	  							***************/
   	  //Test si la liste des questions existent					
   	  else if ( request.getAttribute("listQuestionR") != null)
   	  {
   	  	List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
   	  	List <Answer> listA;
   
   	  	//Test si la liste est vide == des questions créées ou pas
   	  	if (list.size() > 0) 
   	  	{ 
   	  		%>
   	  	  <input type="submit"value="Questionnaire format PDF"  class="btn btn-success" onclick="print_page()"/>
   	  		<div class ="row mt">
				<input type="hidden" name=sujetNom id="test2" value="">
				<div id="content">
				<h3>L'ensemble des questions du questionnaire : <%=(String) request.getAttribute("questionnaireR")%></h3> 
   	  		 <form action="affichageQuestionnaire" method="get">
   	  		<% for(Question question : list)
         	{ 
   	  			listA = question.answerA();
   	  			String type = "radio";
   	  			if(listA.size() > 2  ) type = "checkbox";
   	  			else if (listA.size() == 1 ) type = "input"; %>	
	           	<h3><%=question.getStatement()%></h3>
	          <%  if(request.getAttribute("result") != null)
        		{	%>
        		<label> La(es) bonne(s) réponse(s) à la question : </label>
        		<br>
        		<ul class="list-group">
        		<% 
        		}
	             for(Answer answers : listA)
	            { 
	            if(request.getAttribute("result") != null)
	          		{	 
	            	%>
	            		<li class="list-group-item list-group-item-<%=answers.getPos().equals("Vrai")?"success":"danger"%>" role="alert"><%=answers.getText() %></li>
	          		<%
	          		}
	            else { %>
	            	<div class="form-group">  
	            		<input type="<%=type %>" name="<%=question.getIdTech()%>" <%= (listA.size()== 1)?"":"value=\""+answers.getText()+"\""%>>  <%= (listA.size()== 1)?"":answers.getText()%>
	            		<br>
	            	</div>
	            	
	            <% }
	            }
	             if(request.getAttribute("result") != null)
	             	{ %>
	             	</ul>
         			<%
         			} 
	          }
  			%>	  
	         <input type="hidden" name="Questionnaire_envoi" value="totoro"> 
	         <input type="hidden" name="questionnaireR" value="<%= (String) request.getAttribute("questionnaireR")%>">       
	         <BUTTON type="submit" class="btn btn-success">Envoyer Questionnaire</BUTTON>
		    </form>	    
	     <% if((Integer) request.getAttribute("mark") != null)
	     		{
	     		%>
	           <h3>Score =   <%=request.getAttribute("mark")%>/<%=request.getAttribute("totalMark")%></h3>
	  		 <% } 
	     } 
	     
   	  } %>
	            </div>
	            </div>
    </div> 
</body>
</html>