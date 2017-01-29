package QuestionV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//variable session pour identifier la variante
		HttpSession session = req.getSession();
		String idPere = (String)session.getAttribute("idPere");
        String VarianteType = (String)session.getAttribute("varianteType");
        
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
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String identifiantRecherche = req.getParameter("getDetail");
		Question listeQuestionA = new Question();
		
		//Recherche et Recupere une question
		listeQuestionA = Question.getQuestion(identifiantRecherche, CreationEtAffichageQuestionForm.listeQ);
		System.out.println(listeQuestionA.idTechvisible + " id pere");
		System.out.println(listeQuestionA.variante + " id var");
		req.setAttribute("Question",listeQuestionA);
		this.getServletContext().getRequestDispatcher("/afficheQuestionD.jsp").forward(req, resp);
	}
	
	
}
