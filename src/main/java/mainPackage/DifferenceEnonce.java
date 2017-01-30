package mainPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Difference")
public class DifferenceEnonce extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String identifiantRecherche = req.getParameter("param1");
		String auteur = req.getParameter("param2");
		String temp = identifiantRecherche+ "+"+ auteur;
		Question listeQuestionA = new Question();
		Question quest2 = new Question();
		//Recherche et Recupere une question
		listeQuestionA = Question.getQuestion(temp, CreationEtAffichageQuestionForm.listeQ);
		quest2 = Question.getQuestion(listeQuestionA.getVariante(), CreationEtAffichageQuestionForm.listeQ);
		req.setAttribute("enonceV",listeQuestionA.getEnonce());
		req.setAttribute("enonceP",quest2.getEnonce());
		this.getServletContext().getRequestDispatcher("/afficheDiffOriginaleVariante.jsp").forward(req, resp);
	}
}
