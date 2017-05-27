package collaborative_exams;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletPertinence")
public class ServletRelevance extends HttpServlet 
{
	@Inject
	QuestionManager insert;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String searchId = req.getParameter("setRelevanceMark");
		int mark = Integer.parseInt(req.getParameter("relevanceMark"));
		
		//Recherche et Recupere une question
		req.setAttribute("Question",insert.returnQuestionMark(searchId, mark));
		this.getServletContext().getRequestDispatcher("/displayQuestionDetails.jsp").forward(req, resp);
	}
	
	
}
