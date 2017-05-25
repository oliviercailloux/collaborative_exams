package collaborative_exams;
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

@WebServlet("/FormTest")
public class testCor extends HttpServlet 
{
	@Inject
	GestionQuestion insert;
    @Inject
    GestionQuestionnaire questionnaireT;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("test3");
    	//permet de récupérer la liste des questions sélectionnées
    	String[] values = req.getParameterValues("listSelect");
    	//navigue et récupère les questions sélectionées.
    	for(int i=0; i<values.length;i++)
    	{
    		String idTemp = values[i];
    		//if(insert.verifyR(idTemp)==false)
    			System.out.println("t'as eu faux à la reponse " + i);
    	}
    	//sujetQ.add(sujetTemp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		List<Question> test = questionnaireT.getQuestions(2);
		req.setAttribute("listQ", test.get(0));
		req.setAttribute("listR", test.get(0).reponseR());
        this.getServletContext().getRequestDispatcher("/testCor.jsp").forward(req, resp);
		

	}
	
	
}
