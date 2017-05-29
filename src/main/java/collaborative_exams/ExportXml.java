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

//Servlet for export subject in XML
@WebServlet("/exportXml")
public class ExportXml extends HttpServlet 
{
	@Inject
	SubjectManager sujetT;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//Get the subject
		String name = req.getParameter("subject");
    	Subject s = sujetT.getSubjectByName(name);

    	try {
    		
    	//Construct XML document
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         Element rootElement = doc.createElement("subject");
	         Element subjectName = doc.createElement("name");
	         subjectName.appendChild(doc.createTextNode(s.getNameSubject()));
	         rootElement.appendChild(subjectName);
	         doc.appendChild(rootElement);
	         
	         
	         for(Question x : s.getQuestionsSubject() ){
	    
 				Element question = doc.createElement("question");
 				Attr attr = doc.createAttribute("id");
 		        attr.setValue(Integer.toString(x.getId()));
 		        question.setAttributeNode(attr);
 		        Attr attr4 = doc.createAttribute("author");
		        attr4.setValue(x.getAut());
		        question.setAttributeNode(attr4);
 		        Element statement = doc.createElement("statement");
 		        statement.appendChild(doc.createTextNode(x.getStatement()));
 		        question.appendChild(statement);
     			
 		       int j = 0;
    			for(Answer y : x.answerA()){
    				Element answer = doc.createElement("answer");
    				Attr attr2 = doc.createAttribute("id");
    				j++;
    		        attr2.setValue(Integer.toString(j));
    		        Attr attr3 = doc.createAttribute("type");
    		        attr3.setValue(y.getPos());
    		        answer.setAttributeNode(attr2);
    		        answer.setAttributeNode(attr3);
    		        answer.appendChild(doc.createTextNode(y.getText()));
    		        question.appendChild(answer);
    			}
     			rootElement.appendChild(question);
 			}

	         // Write content in a file
	         TransformerFactory transformerFactory =
	         TransformerFactory.newInstance();
	         Transformer transformer =
	         transformerFactory.newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         DOMSource source = new DOMSource(doc);
	         StreamResult result =
	         new StreamResult(new File("../applications/collaborative_exams/export_subject.xml"));
	         transformer.transform(source, result);
	      
	      } catch (Exception e) 
    	{
	         e.printStackTrace();
	      }
    	

        this.getServletContext().getRequestDispatcher("/export_subject.xml").forward(req, resp);

    	
	}

	
	
}