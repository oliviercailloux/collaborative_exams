package paquet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuestionV1.*;

import javax.servlet.http.HttpServlet;

@WebServlet("/inscription")
public class Inscription extends HttpServlet
{
    public static List <Compte> listeC = new ArrayList <Compte>();
    @Inject
	private Compte test_inscription;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String resultat="";
		String identifiant = req.getParameter("login");
		String mdp = req.getParameter("pass");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String mail = req.getParameter("mail");
		if(!test_inscription.existeDeja(listeC, mail))
		{
			listeC.add(test_inscription.creerCompte(identifiant, mdp, prenom, prenom, mail));
			this.getServletContext().getRequestDispatcher("/connect.jsp").forward(req, resp);	
		}
		else resultat = "Ce compte existe déjà ! try again";
			req.setAttribute("resultat", resultat);
		
		this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(req, resp);

	}
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().println("Marimoh");
		this.getServletContext().getRequestDispatcher("/ok.jsp").forward(req, resp);
	}
	

}
