package QuestionV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Pform")
public class Pform extends HttpServlet 
{
    public static List <Question> listeQ = new ArrayList <Question>();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String nom = req.getParameter("auteur");
        int identifiant = Integer.parseInt( req.getParameter("id"));
        String enonce = req.getParameter("question");
        String langueI = req.getParameter("langueN");
        String competenceI = req.getParameter("competenceN");
        Question insert = new Question();
        insert.nomAuteur =nom;
        insert.langueQ= langueI;
        insert.competenceQ= competenceI;
        insert.enonceQ =enonce;
        insert.idQ = identifiant;
        System.out.println(nom +"   " + identifiant + insert);
        listeQ.add(insert);
        req.setAttribute("listQuestion", listeQ);
        
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);;
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
