package mainPackage;
import java.io.IOException;
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

@WebServlet("/exportXML")
public class ExportXml extends HttpServlet 
{
	@Inject
	GestionSujet subject;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	String name = req.getParameter("subject");
    	Sujet s = subject.getSubjectByName(name);
    	subject.ExportJava(s);
    	
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
        this.getServletContext().getRequestDispatcher("/download_xml_ok.html");

	}
	
	
}
