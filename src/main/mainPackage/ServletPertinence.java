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
		
		//Recherche et Recupere une question
		req.setAttribute("Question",insert.retourneQuestionNote(identifiantRecherche, note));
		this.getServletContext().getRequestDispatcher("/afficheQuestionD.jsp").forward(req, resp);
	}
	
	
}
