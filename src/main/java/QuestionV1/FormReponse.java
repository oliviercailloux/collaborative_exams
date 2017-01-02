package QuestionV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FormReponse")
public class FormReponse extends HttpServlet 
{

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//variable session pour identifier la variante
		HttpSession session = req.getSession();
		String idPere = (String)session.getAttribute("idPere");
        String VarianteType = (String)session.getAttribute("varianteType");
        
        //variable Question standard
        String nom = req.getParameter("auteur");
        int identifiant = Integer.parseInt( req.getParameter("id"));
        String enonce = req.getParameter("question");
        String langueI = req.getParameter("langueN");
        String competenceI = req.getParameter("competenceN");
        
        //variable Opinion
        String opinionI = req.getParameter("opinionN");
        Question insert = new Question(idPere);
        insert.nomAuteur =nom;
        insert.langueQ= langueI;
        insert.competenceQ= competenceI;
        insert.enonceQ =enonce;
        insert.idQ = identifiant;
        insert.opinion=opinionI;
        insert.listeR=Question.retourneReponse(idPere, Pform.listeQ);
        Pform.listeQ.add(insert);
        
        req.setAttribute("listQuestionR", Pform.listeQ);
        session.invalidate();
        this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String bt = req.getParameter("testA");
		Question listeQuestionA = new Question();
		listeQuestionA = Question.getQuestion(bt, Pform.listeQ);
		req.setAttribute("Question",listeQuestionA);
		this.getServletContext().getRequestDispatcher("/afficheQuestionD.jsp").forward(req, resp);;
	}
	
	
}
