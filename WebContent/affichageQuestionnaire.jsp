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
   	 	hidden_field.value = document.getElementById("questionnaireR").value;
    	document.getElementById("FormReponse").Submit();
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
		alert("<%=(String) request.getAttribute("questionnaireR")%> au format PDF !");
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

 <%@page import="mainPackage.*" %>
 <%@page import="java.util.*"%>
<%
	List <String> listQuestionnaire = (List<String>) request.getAttribute("listeQuestionnaire");
	
%>
   
   <h3>Choisissez un Questionnaire</h3>
   <form action="affichageQuestionnaire" method="get">
     Merci de choisir un questionnaire :
      <SELECT name="questionnaireR">
         <OPTION value="<%= (String) request.getAttribute("questionnaireR")%>"><%= (String) request.getAttribute("questionnaireR")%></OPTION>
         <%
         
         for(String sujetQuestionnaire : listQuestionnaire ){
         %>
          <OPTION value="<%=sujetQuestionnaire.toString() %>"><%=sujetQuestionnaire.toString() %></OPTION>
         <%
         }
         %>
      </SELECT>
      <BUTTON type="submit">Chercher</BUTTON>
   </form>
   
  <% 
  if (request.getAttribute("listQuestionR")== null) {}
   	  /***************
   	  		DEBUT TEST AFFICHAGE QUESTION
   	  							***************/
   	  //Test si la liste des questions existent					
   	  else if ( request.getAttribute("listQuestionR") != null)
   	  {
   	  	List <Question> list = (List<Question>) request.getAttribute("listQuestionR");
   	  	List <Reponse> listR;
   

   	  	//Test si la liste est vide == des questions créées ou pas
   	  	if (list.size() > 0) 
   	  	{ 
   	  		%>
   	  		<input type="submit"value="Questionnaire format PDF" onclick="print_page()"/>
				<input type="hidden" name=sujetNom id="test2" value="">
				<div id="content">
				<h3>L'ensemble des questions du questionnaire : <%=(String) request.getAttribute("questionnaireR")%></h3> 
   	  		 <form action="affichageQuestionnaire" method="get">
   	  		<% for(Question question : list)
         	{ 
   	  			listR = question.reponseR();
   	  			String type = "radio";
   	  			if(listR.size() > 2  ) type = "checkbox";
   	  			else if (listR.size() == 1 ) type = "input"; %>	
	           	<h3><%=question.getEnonce()%></h3>
	          <%  if(request.getAttribute("result") != null)
        		{	%><label> Le(s) bonne(s) réponse(s) à la question : </label><br><%}
	             for(Reponse reponses : listR)
	            { 
	            if(request.getAttribute("result") != null)
	          		{	 
	            	if(reponses.getPos().equals("Vrai")) 
	            		{%>
	          		<label><%=reponses.getText() %></label><br>
	          		<%	}
	          		}
	            else { %>
	            	<input type="<%=type %>" name="<%=question.getIdTech()%>" <%= (listR.size()== 1)?"":"value=\""+reponses.getText()+"\""%>>  <%= (listR.size()== 1)?"":reponses.getText()%><br>
	            <% }
	            } %>
         	<%
         	}
         }
   	  	
         %>	  
         <input type="hidden" name="Questionnaire_envoi" value="totoro"> 
         <input type="hidden" name="questionnaireR" value="<%= (String) request.getAttribute("questionnaireR")%>">       
         <BUTTON type="submit">Envoyer Questionnaire</BUTTON>
	     </form>
	     <% if((Integer) request.getAttribute("score") != null)
	     		{
	     		%>
	           <h3>Score =   <%=(Integer) request.getAttribute("score")%>/<%=(Integer) request.getAttribute("scoreTotal")%></h3>
	            <% } %>
	            </div>
    <%}%>
</body>
</html>