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

@WebServlet("/FormReponse")
public class CreationVarianteEtAfficheDetailForm extends HttpServlet 
{
	@Inject
	QuestionManager insert;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//variable session pour identifier la variante
		HttpSession session = req.getSession();
		String idFather = (String)session.getAttribute("idPere");
        
        //variable Question standard
        String name = req.getParameter("auteur");
        int id = Integer.parseInt( req.getParameter("id"));
        String statement = req.getParameter("variante");
        String language = req.getParameter("langueN");
        String skill = req.getParameter("competenceN");
        String level = req.getParameter("niveau");
        
        //variable Opinion
        String opinion = req.getParameter("opinionN");
        //Question insert = new Question(idPere);
        CreateDisplayQuestionForm.listQ.add(insert.createQuestionV(name, language, skill, statement, idFather, id, opinion, level));
        
        req.setAttribute("listQuestionR", CreateDisplayQuestionForm.listQ);
        session.invalidate();
		req.setAttribute("listeSujet", SubjectForm.subjectQ);
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String idRecherche = req.getParameter("getDetail");
		//Recupere les ids pour une question
		req.setAttribute("Question",insert.returnQuestion(idRecherche));
		this.getServletContext().getRequestDispatcher("/afficheQuestionD.jsp").forward(req, resp);
	}
	
	
}
