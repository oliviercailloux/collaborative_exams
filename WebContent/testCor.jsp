<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<!-- 
		Permet de récupérer la valeur du nom sujet saisie par la personne
		Et Submit un form d'une autre façon						 			
	<script>
	function modify_value()
	{
    	var hidden_field = document.getElementById('test2');
   	 	hidden_field.value = document.getElementById("nomSujet").value;
    	document.getElementById("testform").Submit();
	}</script>-->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
		alert('test')
	    doc.fromHTML($('#content').html(), 15, 15, { 
	        'width': 190, 
	            'elementHandlers': specialElementHandlers 
	    }); 
	    
	    doc.save('sample-page.pdf'); 
	}
</script>
<title>Insert title here</title>
</head>
<body>
<%@page import="mainPackage.*" %>
	<%@page import="java.util.*"%>
<% 
System.out.println("testt salim");

	if ( request.getAttribute("listQ") != null)
	{
		System.out.println("testt salim2222");

		Question list = (Question) request.getAttribute("listQ");
		List <Reponse> listR = (List<Reponse>) request.getAttribute("listR");
		%>
			
				<input type="submit"value="Enregistrer rep" onclick="print_page()"/>
				<input type="hidden" name=sujetNom id="test2" value="">
				<div id="content">
				<h4><%=list.getAut()%></h4>
				<ol>
	 					<%
					for(Reponse rep : listR)
					{
	
					%>
						<li><%=rep.getText()%></li>
						 	
					<%
					}}
	 			%>
				</ol>
				</div>
</body>
</html>