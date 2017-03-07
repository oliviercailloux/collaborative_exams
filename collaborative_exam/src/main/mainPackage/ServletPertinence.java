package mainPackage;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletPertinence")
public class ServletPertinence extends HttpServlet 
{
	@Inject
	GestionQuestion insert;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String identifiantRecherche = req.getParameter("setNotePertinence");
		int note = Integer.parseInt(req.getParameter("notePertinence"));
		Question listeQuestionA = new Question();
		
		//Recherche et Recupere une question
		listeQuestionA = Question.getQuestion(identifiantRecherche, CreationEtAffichageQuestionForm.listeQ);
		System.out.println(listeQuestionA.idTechvisible + " id pere");
		System.out.println(listeQuestionA.variante + " id var");
		listeQuestionA.setNotePertinence(note);
		req.setAttribute("Question",listeQuestionA);
		this.getServletContext().getRequestDispatcher("/afficheQuestionD.jsp").forward(req, resp);
	}
	
	
}
