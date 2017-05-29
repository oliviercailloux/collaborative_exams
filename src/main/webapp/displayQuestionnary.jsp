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
     <div class="navbar navbar-default navbar-default-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>  Collaborative Exams</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Question <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	            	<li><a href="FormQ.jsp">Création Question</a></li>
	            	<li><a href="Pform">Liste Question</a></li>
	            </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sujet <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	            	<li><a href="createSubject.jsp">Création Sujet</a></li>
	            </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Questionnaire <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	            	<li><a href="QuestionnaryForm">Création Questionnaire</a></li>
	            	<li><a href="displayQuestionnary">Liste Questionnaire</a></li>
	            </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>
<%
	List <String> listQuestionnary = (List<String>) request.getAttribute("listQuestionnary");
	
%>
  <div class="container">
   <legend class="text-center">
		<h2>Questionnaire</h2>
		 </legend>
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
   
  
   <br>
   	<br>
  
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
   	  	int questionnumero = 0;
   
   	  	//Test si la liste est vide == des questions créées ou pas
   	  	if (list.size() > 0) 
   	  	{ 
   	  		%>
   	  		<div class="form-group">
   	  		<form  method="post" action="exportXmlQuestionnary">
   			<input type="hidden" value="<%= (String) request.getAttribute("questionnaryR")%>" name="questionnary">
		    <BUTTON type="submit" class="btn btn-warning">Export au format XML</BUTTON>
		    <input value="Export au format PDF"  class="btn btn-warning" onclick="print_page()"/>
		   </form>
		    
		   </div>
   	  	  
   	  		<div class ="row mt">
				<input type="hidden" name=sujetNom id="test2" value="">
				<div id="content">
				<h3>L'ensemble des questions du questionnaire : <%=(String) request.getAttribute("questionnaryR")%></h3> 
   	  		 <form action="displayQuestionnary" method="get">
   	  		<% for(Question question : list)
         	{ 	
   	  			listA = question.answerA();
   	  			String type = "radio";
   	  			if(listA.size() > 2  ) type = "checkbox";
   	  			else if (listA.size() == 1 ) type = "input"; %>	
   	  			<div class="panel panel-info"> 
		<div class="panel-heading"> 
		<h3 class="panel-title">Question n°<%= questionnumero%></h3>
	 </div>
	 <div class="panel-body">
	           	<h3><%=question.getStatement()%></h3>
	          <% questionnumero ++;  
	          if(request.getAttribute("result") != null)
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
	             %>
	             </div>
	             </div>
	             <%
	          }
  			%>	 
  			    
	         <input type="hidden" name="Questionnary_send" value="totoro"> 
	         <input type="hidden" name="questionnaryR" value="<%= (String) request.getAttribute("questionnaryR")%>">       
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
       <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script> 
</body>
</html>