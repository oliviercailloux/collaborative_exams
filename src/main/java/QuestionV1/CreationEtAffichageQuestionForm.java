package QuestionV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Pform")
public class CreationEtAffichageQuestionForm extends HttpServlet 
{
    public static List <Question> listeQ = new ArrayList <Question>();
    @Inject
    GestionReponse reponse;
    @Inject
    GestionQuestion testQuestion;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		String nom = (String)session.getAttribute("auteur");
        int identifiant = (int) session.getAttribute("identifiant");
        String enonce = (String)session.getAttribute("question");
        String langueI = (String)session.getAttribute("langueN");
        String competenceI = (String)session.getAttribute("competenceN");
        int nombreRep = (int)session.getAttribute("nb");
        int conditionRep =1;  
       // Question insert = new Question();
        testQuestion.createQuestion(nom, langueI, competenceI, enonce, identifiant);
        //creation et insertion des reponses
        while(conditionRep <= nombreRep)
        {
        	String reponseId = "textReponse"+conditionRep;
        	String positionID = "pos"+conditionRep;
        	String textReponse = req.getParameter(reponseId);
        	String pos = req.getParameter(positionID);
        	testQuestion.setReponseG(reponse.createReponse(identifiant, textReponse, pos));
        	conditionRep=conditionRep+1;
        }//fin creation et insertion des reponses
        /*
        insert.setAut(nom);
        insert.setLangue(langueI);
        insert.setCompetence(competenceI);
        insert.setEnonce(enonce);
        insert.setId(identifiant);*/
       // System.out.println(nom +"   " + identifiant + insert);
        listeQ.add(testQuestion.getQuestion());
        
        req.setAttribute("listQuestionR", listeQ);
        req.setAttribute("listeSujet", SujetForm.sujetQ);
        session.invalidate();
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		if(req.getParameter("competenceR")!=null)
		{
			String competenceRechercher = req.getParameter("competenceR");
			System.out.println("je rentre laaaaaaa" + competenceRechercher);
			List <Question> listeRechercher = new ArrayList <Question>();
			listeRechercher = Question.trouveQuestionParMatiere(competenceRechercher, listeQ);
			req.setAttribute("listQuestionR", listeRechercher);
			req.setAttribute("competenceR", competenceRechercher);
	        
		}
		else if (req.getParameter("sujetR")!=null)
		{
			String nom = req.getParameter("sujetR");
			
			Sujet sujetRechercher = new Sujet();
			sujetRechercher = Sujet.getSujet(nom, SujetForm.sujetQ);
			List <Question> listeRechercher = new ArrayList <Question>();
			listeRechercher = sujetRechercher.listeQuestionSujet;
			req.setAttribute("listQuestionR", listeRechercher);
			req.setAttribute("sujetNomR", nom);
		}
		req.setAttribute("listeSujet", SujetForm.sujetQ);
		this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
		
	}
}