package collaborative_exams;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.derby.tools.sysinfo;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@WebServlet("/exportXml")
public class ExportXml extends HttpServlet 
{
	@Inject
	SubjectManager sujetT;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String name = req.getParameter("subject");
    	System.out.println(name);
    	
    	Subject s = sujetT.getSubjectByName(name);
    	System.out.println(s.getNameSubject());
    	
    	//sujetT.ExportJava(s);
    	
    	

    	try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         // root element
	         Element rootElement = doc.createElement("subject");
	         Element subjectName = doc.createElement("name");
	         subjectName.appendChild(doc.createTextNode(s.getNameSubject()));
	         rootElement.appendChild(subjectName);
	         doc.appendChild(rootElement);
	         
	         
	         for(Question x : s.getQuestionsSubject() ){
	        	System.out.println("question");
 				Element question = doc.createElement("question");
 				Attr attr = doc.createAttribute("id");
 		        attr.setValue("1");
 		        question.setAttributeNode(attr);
 		        Element statement = doc.createElement("statement");
 		        statement.appendChild(doc.createTextNode(x.getStatement()));
 		        question.appendChild(statement);
     			
     			for(Answer y : x.answerA()){
     				Element answer = doc.createElement("answer");
     				Attr attr2 = doc.createAttribute("id");
     		        attr2.setValue("1");
     		        answer.setAttributeNode(attr2);
     		        answer.appendChild(doc.createTextNode(y.getText()));
     		        question.appendChild(answer);
     			}
     			rootElement.appendChild(question);
 			}
	         
	         
	         

	        /*   supercars element
	         Element supercar = doc.createElement("supercars");
	         rootElement.appendChild(supercar);
	         // setting attribute to element
	         Attr attr = doc.createAttribute("company");
	         attr.setValue("Ferrari");
	         supercar.setAttributeNode(attr);
	         // carname element
	         Element carname = doc.createElement("carname");
	         Attr attrType = doc.createAttribute("type");
	         attrType.setValue("formula one");
	         carname.setAttributeNode(attrType);
	         carname.appendChild(doc.createTextNode("Ferrari 101"));
	         supercar.appendChild(carname);
	         Element carname1 = doc.createElement("carname");
	         Attr attrType1 = doc.createAttribute("type");
	         attrType1.setValue("sports");
	         carname1.setAttributeNode(attrType1);
	         carname1.appendChild(
	         doc.createTextNode("Ferrari 202"));
	         supercar.appendChild(carname1);*/

	         // write the content into xml file
	         TransformerFactory transformerFactory =
	         TransformerFactory.newInstance();
	         Transformer transformer =
	         transformerFactory.newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         DOMSource source = new DOMSource(doc);
	         StreamResult result =
	         new StreamResult(new File("/Users/brahimfanch/Downloads/company7.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult =
	         new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) 
    	{
	         e.printStackTrace();
	      }
    	

    	  
    	
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
        this.getServletContext().getRequestDispatcher("/ok.jsp").forward(req, resp);

	}
	
	
}