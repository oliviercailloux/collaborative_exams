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

@WebServlet("/FormAnswer")
public class CreateVariantDisplayDetailForm extends HttpServlet 
{
	@Inject
	QuestionManager insert;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//variable session pour identifier la variante
		HttpSession session = req.getSession();
		String idParent = (String)session.getAttribute("idParent");
        
        //variable Question standard
        String name = req.getParameter("author");
        int id = Integer.parseInt( req.getParameter("id"));
        String statement = req.getParameter("variant");
        String language = req.getParameter("languageN");
        String skill = req.getParameter("skillN");
        String level = req.getParameter("level");
        
        //variable Opinion
        String opinion = req.getParameter("opinionN");
        CreateDisplayQuestionForm.listQ.add(insert.createQuestionV(name, language, skill, statement, idParent, id, opinion, level));
        
        req.setAttribute("listQuestionR", CreateDisplayQuestionForm.listQ);
        session.invalidate();
		req.setAttribute("listSubject", SubjectForm.subjectQ);
        this.getServletContext().getRequestDispatcher("/displayQuestion.jsp").forward(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String idSearch = req.getParameter("getDetail");
		//Recupere les identifiants pour une question
		req.setAttribute("Question",insert.returnQuestion(idSearch));
		this.getServletContext().getRequestDispatcher("/displayQuestionD.jsp").forward(req, resp);
	}
	
	
}
