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
 <%@page import="QuestionV1.Question" %>
 <%@page import="java.util.*"%>  
 <%
 	String enonceP = (String) request.getAttribute("enonceP");
 	String enonceV = (String) request.getAttribute("enonceV");
 %>
 
    <div>
        <label>Original:</label>
        <br />
        <textarea id="enoncePere" cols="75" readonly><%=enonceP %></textarea>
    </div>
  
    <div>
        <label>Variante:</label>
        <br />
        <textarea id="enonceVar" cols="75" readonly><%=enonceV %></textarea>
    </div>
    
    <div>
        <label>Differences:</label>
        <br />
        <div id="textareaDiff"></div>
    </div>
  
<!-- user interface ends -->
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() 
{
	var textBefore = document.getElementById("enoncePere").value;
    var textAfter = document.getElementById("enonceVar").value;
  
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
        str += '<del>' + escape(out.o[i]) + oSpace[i] + "</del>";
      }
  } 
  else 
  {
    if (out.n[0].text == null) 
    {
      for (n = 0; n < out.o.length && out.o[n].text == null; n++) 
      {
        str += '<del>' + escape(out.o[n]) + oSpace[n] + "</del>";
      }
    }

    for ( var i = 0; i < out.n.length; i++ ) 
    {
      if (out.n[i].text == null) 
      {
        str += '<ins>' + escape(out.n[i]) + nSpace[i] + "</ins>";
      } 
      else 
      {
        var pre = "";

        for (n = out.n[i].row + 1; n < out.o.length && out.o[n].text == null; n++ ) 
        {
          pre += '<del>' + escape(out.o[n]) + oSpace[n] + "</del>";
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
</body>
</html>