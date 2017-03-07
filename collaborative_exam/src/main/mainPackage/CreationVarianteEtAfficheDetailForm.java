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
	GestionQuestion insert;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//variable session pour identifier la variante
		HttpSession session = req.getSession();
		String idPere = (String)session.getAttribute("idPere");
        
        //variable Question standard
        String nom = req.getParameter("auteur");
        int identifiant = Integer.parseInt( req.getParameter("id"));
        String enonce = req.getParameter("variante");
        String langueI = req.getParameter("langueN");
        String competenceI = req.getParameter("competenceN");
        String niveau = req.getParameter("niveau");
        
        //variable Opinion
        String opinionI = req.getParameter("opinionN");
        //Question insert = new Question(idPere);
        CreationEtAffichageQuestionForm.listeQ.add(insert.createQuestionV(nom, langueI, competenceI, enonce, idPere, identifiant, opinionI, niveau));
        
        req.setAttribute("listQuestionR", CreationEtAffichageQuestionForm.listeQ);
        session.invalidate();
		req.setAttribute("listeSujet", SujetForm.sujetQ);
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String identifiantRecherche = req.getParameter("getDetail");
		//Recupere les identifiants pour une question
		req.setAttribute("Question",insert.retourneQuestion(identifiantRecherche));
		this.getServletContext().getRequestDispatcher("/afficheQuestionD.jsp").forward(req, resp);
	}
	
	
}
