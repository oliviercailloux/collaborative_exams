<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
<html>
<body>
 <%@page import="collaborative_exams.*" %>
 <%@page import="java.util.*"%>  
 <%
 	Question list = (Question) request.getAttribute("questionv");
 	Question list2 = (Question) request.getAttribute("questionP");
 	String statementP = (String) request.getAttribute("statementP");
 	String statementV = (String) request.getAttribute("statementV");
 %>
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
 			<h2 class="text-center">Creation Variante</h2>
			<hr>
 <div class="row">
 <div class="col-md-6">
 <div class="panel panel-danger"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">Question parente </h3>
	 </div>
	 <div class="panel-body">
	 <dl class="dl-horizontal">
	  <dt>Enonce : </dt>
	  <dd><%=list.getStatement()%></dd>
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
		    <%=list.getRelevanceMark()+" ("+list.getNbVoteRelevance()+" vote(s))"%> 
	  </dd>
	</dl> 
	 </div> 
</div>
    <div>
        <label>Original:</label>
        <br />
        <blockquote> <span id="statementParentc"><%=statementP %></span></blockquote>
    </div>
</div>
 <div class="col-md-6">
 <div class="panel panel-success"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">Question variante </h3>
	 </div>
	 <div class="panel-body">
	 <dl class="dl-horizontal">
	  <dt>Enonce : </dt>
	  <dd><%=list2.getStatement()%></dd>
	  <dt>Langue : </dt>
	  <dd><%=list2.getLanguage()%></dd>
	  <dt>La compétence : </dt>
	  <dd><%=list2.getSkill()%></dd>
	  <dt>Le niveau : </dt>
	  <dd><%=list2.getLevel()%></dd>
	  <dt>Le sujet : </dt>
	  <dd><%=list.getSubjectName()%></dd>
	  <dt>L'auteur : </dt>
	  <dd><%=list2.getAut()%></dd>
	  <dt>L'identifiant : </dt>
	  <dd><%=list2.getId()%></dd>
	  <dt>Pertinence : </dt>
	  <dd>
		    <%=list2.getRelevanceMark()+" ("+list2.getNbVoteRelevance()+" vote(s))"%> 
	  </dd>
	</dl> 
	 </div> 
</div>
    <div>
        <label>Variante:</label>
        <br />
        <blockquote><span id="statementVarc"><%=statementV %></span></blockquote>
    </div>
</div>
</div>
    
    <div class="text-center">
        <label>Difference:</label>
        <br />
        <div id="textareaDiff"></div>
    </div>
</div>  
<!-- user interface ends -->
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() 
{
	var textBefore = document.getElementById("statementParentc").textContent;
    var textAfter = document.getElementById("statementVarc").textContent;
  
    var differences = diffString(textBefore, textAfter)
  
    var differencesAsString = differences.toString();
  
    var textareaDiff = document.getElementById
    (
        "textareaDiff"
    );
    textareaDiff.innerHTML = differencesAsString;
});
function escape(s) {
    var n = s;
    n = n.replace(/&/g, "&amp;");
    n = n.replace(/</g, "&lt;");
    n = n.replace(/>/g, "&gt;");
    n = n.replace(/"/g, "&quot;");

    return n;
}

function diffString( o, n ) {
  o = o.replace(/\s+$/, '');
  n = n.replace(/\s+$/, '');
  //on recupere deux listes-map de mots
  var out = diff(o == "" ? [] : o.split(/\s+/), n == "" ? [] : n.split(/\s+/) );
  var str = "";

  var oSpace = o.match(/\s+/g);
  if (oSpace == null) {
    oSpace = ["\n"];
  } else {
    oSpace.push("\n");
  }
  var nSpace = n.match(/\s+/g);
  if (nSpace == null) 
  {
    nSpace = ["\n"];
  } 
  else 
  {
    nSpace.push("\n");
  }
  //si la liste n st vide = tous les mots de o sont supprimés
  if (out.n.length == 0) 
  {
      for (var i = 0; i < out.o.length; i++) 
      {
        str += '<del><mark class="bg-danger">' + escape(out.o[i]) + oSpace[i] + "</mark></del>";
      }
  } 
  else 
  {
    if (out.n[0].text == null) 
    {
      for (n = 0; n < out.o.length && out.o[n].text == null; n++) 
      {
        str += '<del><mark class="bg-danger">' + escape(out.o[n]) + oSpace[n] + "</mark></del>";
      }
    }

    for ( var i = 0; i < out.n.length; i++ ) 
    {
      if (out.n[i].text == null) 
      {
        str += '<ins><mark class="bg-success">' + escape(out.n[i]) + nSpace[i] + "</mark></ins>";
      } 
      else 
      {
        var pre = "";

        for (n = out.n[i].row + 1; n < out.o.length && out.o[n].text == null; n++ ) 
        {
          pre += '<del><mark class="bg-danger">' + escape(out.o[n]) + oSpace[n] + "</mark></del>";
        }
        str += " " + out.n[i].text + nSpace[i] + pre;
      }//else
    }//for
  }//else
  
  return str;
}
function diff( o, n ) {
  var ns = new Object();
  var os = new Object();
  //crée une map pour chaque mot présent
  for ( var i = 0; i < n.length; i++ ) {
    if ( ns[ n[i] ] == null )
    {
      ns[ n[i] ] = { rows: new Array(), o: null };
    }
    ns[ n[i] ].rows.push( i );
  }
  
  //crée une map
  for ( var i = 0; i < o.length; i++ ) {
    if ( os[ o[i] ] == null ){
      os[ o[i] ] = { rows: new Array(), n: null };}
    
    os[ o[i] ].rows.push( i );
  }
  //union si l'element se trouve une fois dans les deux map
  for ( var elmtPhrase in ns ) {
    if ( ns[elmtPhrase].rows.length == 1 && typeof(os[elmtPhrase]) != "undefined" && os[elmtPhrase].rows.length == 1 ) 
    {
      n[ ns[elmtPhrase].rows[0] ] = { text: n[ ns[elmtPhrase].rows[0] ], row: os[elmtPhrase].rows[0] };
      o[ os[elmtPhrase].rows[0] ] = { text: o[ os[elmtPhrase].rows[0] ], row: ns[elmtPhrase].rows[0] };
    }
  }
  //on recupere un mot qui se trouvent en double dans les phrases donc map
  //phase la plus complexe 
  //on parcours par l'avant
  for ( var i = 0; i < n.length - 1; i++ ) 
  {
    /*
    	 Si on a trouve elmt dans la boucle precedente et que son suivant n'a  pas ete trouve :
    	 	on regarde si elment+1 se trouve hors champ &&
    	 	que ce dernier n'a pas ete trouve aussi dans o &&
    	 	que les deux elmts sont egaux dans les diff map
    	 																						*/
    if ( n[i].text != null && n[i+1].text == null && n[i].row + 1 < o.length && o[ n[i].row + 1 ].text == null && 
         n[i+1] == o[ n[i].row + 1 ] ) 
    {		
      n[i+1] = { text: n[i+1], row: n[i].row + 1 };
      o[n[i].row+1] = { text: o[n[i].row+1], row: i + 1 };
    }
  }
  //meme principe que la boucle precedente mais par arriere car si premier mot est en double on ne le trouve pas au premier tour par l'avant
  //des mots en doubles peuvent etre trouve par la boucle precedente qui n'etait pas présent dans la phase d'union donc principe de double verif
  for ( var i = n.length - 1; i > 0; i-- ) 
  {
    if ( n[i].text != null && n[i-1].text == null && n[i].row > 0 && o[ n[i].row - 1 ].text == null && 
         n[i-1] == o[ n[i].row - 1 ] ) 
    {
      
      n[i-1] = { text: n[i-1], row: n[i].row - 1 };
      o[n[i].row-1] = { text: o[n[i].row-1], row: i - 1 };
    }
  }
  
  return { o: o, n: n };
}
</script>
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>