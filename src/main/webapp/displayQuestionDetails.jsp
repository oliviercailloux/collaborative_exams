<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript"> 
function afficheForm(id) 
{ 
	if (document.getElementById(id).style.visibility == "hidden")
			document.getElementById(id).style.visibility = "visible"; 
	else	document.getElementById(id).style.visibility = "hidden"; 
} 
</script> 
  </head>
<html>
<body>
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>

   <% 
   	  Question list= new Question();
   	  if ( request.getAttribute("Question") != null)
   	  {
   	  	list = (Question) request.getAttribute("Question");
   	  }%>
   	  
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
   	  
   	  <div class="container">
<div class="row mt">
<legend class="centered">
<h2>Les informations de la question : <%=list.getStatement()%> </h2>
 </legend>
</div>
<div class="panel panel-success"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">Informations </h3>
	 </div>
	 <div class="panel-body">
	 <dl class="dl-horizontal">
	  <dt>Enonce : </dt>
	  <dd><%=list.getStatement()%><%if(list.getVar().equals("-")==false){%>  <a href="Difference?param1=<%=list.getIdTech()%>&param2=<%=list.getVar()%>/" class="btn btn-info btn-xs">Difference originale</a><%} %></dd>
	  <dt>Langue : </dt>
	  <dd><%=list.getLanguage()%></dd>
	  <dt>La compétence : </dt>
	  <dd><%=list.getSkill()%></dd>
	  <dt>Le niveau : </dt>
	  <dd><%=list.getLevel()%></dd>
	  <dt>Le sujet : </dt>
	  <dd><%=list.getSubjectName()%></dd>
	  <dt>L'auteur : </dt>
	  <dd><%=list.getAut()%></dd>
	  <dt>L'identifiant : </dt>
	  <dd><%=list.getId()%></dd>
	  <dt>Pertinence : </dt>
	  <dd>
		    <%=list.getRelevanceMark()+" ("+list.getNbVoteRelevance()+" vote(s))"%>  <button type="button" id='afficher' class="btn btn-success btn-xs" onclick="afficheForm('formPertinence');afficheForm('afficher');">Noter</button>
	  </dd>
	  <dd>
	     	  <form id="formPertinence" action="ServletPertinence" method="get" style="visibility: hidden">
   	  	<BUTTON type="submit" class="btn btn-success btn-sm" name="setRelevanceMark" value="<%=list.getIdTech()%>">Valider !</BUTTON>
		<div class="col-md-2">
		<SELECT name="relevanceMark" class="form-control col-md-1">
         		<OPTION value="">--- Note ---</OPTION>
         		<OPTION value="1">1</OPTION>
          		<OPTION value="2">2</OPTION>
          		<OPTION value="3">3</OPTION>
          		<OPTION value="4">4</OPTION>
          		<OPTION value="5">5</OPTION>
      		</SELECT>
		 </div>
	</form>
	</dd>
	</dl> 
	 </div> 
</div>
 	
<div class="panel panel-warning"> 
<div class="panel-heading"> 
		<h3 class="panel-title">Réponses </h3>
	 </div>
	 <div class="panel-body">
	 <dl class="dl-horizontal">
	 	 <dt>Type</dt>
	 <dd>Réponse</dd>
	 <hr/><%
	 for(int i=0; i<list.answerA().size();i++)
	    	{%>
	    		<dt><%=list.answerA().get(i).getPos() %></dt>
	    		<dd class="<%=list.answerA().get(i).getPos().equals("Vrai")?"bg-success":"bg-danger"%>"><%=list.answerA().get(i).getText() %></dd>
	    	<%}
	  %>
	  </dl>
	  </div>
</div>
<div class="panel panel-info"> 
<div class="panel-heading"> 
		<h3 class="panel-title">Variante </h3>
	 </div>
	 <div class="panel-body">
	 <dl class="dl-horizontal">

	  <dt>Variante de la question : </dt>
	  <dd><%=list.getVar()%></dd>
	  <dt>Opinion de l'auteur : </dt>
	  <dd><%if(list.getVar()!="-")
   	  {%>
   		<%=list.getOpinion()%>
   	  <%}
   	   %></dd>

	  </dl>
	     	   	  		<dd><form method="get" action="FormVariant">
		<fieldset>
			<legend>Création d'une variante</legend>
			<SELECT name="variantType">
         		<OPTION value="">--- Variante ---</OPTION>	
         		<OPTION value="VariantSimple">Variante Simple</OPTION>
          		<OPTION value="Improvement">Amelioration</OPTION>
      		</SELECT>
      		
      		<!-- Envoi l'idenfiant de la variante -->
      		
			<BUTTON type="submit" class="btn btn-success btn-xs" name="IdQParent" value="<%=list.getIdTech()%>">Poster</BUTTON> <br />
		</fieldset>
	</form></dd>
	  </div>
</div>
	</div>
	   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>