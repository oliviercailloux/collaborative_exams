package QuestionV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Pform")
public class Pform extends HttpServlet 
{
    public static List <Question> listeQ = new ArrayList <Question>();

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
        Question insert = new Question();
        while(conditionRep <= nombreRep)
        {
        	String reponseId = "textReponse"+conditionRep;
        	String textReponse = req.getParameter(reponseId);
        	Reponse reponse = new Reponse(textReponse);
        	insert.setReponse(reponse);
        	conditionRep=conditionRep+1;
        }
        insert.nomAuteur =nom;
        insert.langueQ= langueI;
        insert.competenceQ= competenceI;
        insert.enonceQ =enonce;
        insert.idQ = identifiant;
        System.out.println(nom +"   " + identifiant + insert);
        listeQ.add(insert);
        req.setAttribute("listQuestionR", listeQ);
        session.invalidate();
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String competenceRechercher = req.getParameter("competenceR");
		List <Question> listeRechercher = new ArrayList <Question>();
		listeRechercher = Question.trouveQuestionParMatiere(competenceRechercher, listeQ);
		req.setAttribute("listQuestionR", listeRechercher);
		req.setAttribute("competenceR", competenceRechercher);
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);;
	}
}
