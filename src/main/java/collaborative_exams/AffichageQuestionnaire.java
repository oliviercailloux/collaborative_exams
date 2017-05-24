package collaborative_exams;
import java.io.IOException;

import java.util.ArrayList;
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


@WebServlet("/affichageQuestionnaire")
public class AffichageQuestionnaire extends HttpServlet 
{
    @Inject
    GestionQuestion testQuestion;
    @Inject
    GestionQuestionnaire questionnaireT;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

    	
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		req.setAttribute("listeQuestionnaire",questionnaireT.getNomQuestionnaires());
		
		if(req.getParameter("questionnaireR")!=null && req.getParameter("questionnaireR").isEmpty()!=true)
		{
			String questionnaireRechercher = req.getParameter("questionnaireR");
			req.setAttribute("listQuestionR", questionnaireT.getQuestionsQuestionnaire(questionnaireRechercher));
			req.setAttribute("questionnaireR", questionnaireRechercher);
	        
		}
		else 
		{
			req.setAttribute("questionnaireR", "--- Questionnaires ---");
		}
		this.getServletContext().getRequestDispatcher("/affichageQuestionnaire.jsp").forward(req, resp);
		
	}
}
