package mainPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SujetForm")
public class SujetForm extends HttpServlet 
{
    public static List <Sujet> sujetQ = new ArrayList <>();
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	//permet de récupérer la liste des questions sélectionnées
    	String[] values = req.getParameterValues("questionSelect");
    	
    	//Recupération nom Sujet et création sujet
    	String nomS = req.getParameter("sujetNom");
    	Sujet sujetTemp =new Sujet(nomS);
    	//navigue et récupère les questions sélectionées.
    	for(int i=0; i<values.length;i++)
    	{
    		Question listeQuestionA = new Question();
    		listeQuestionA = Question.getQuestion(values[i], CreationEtAffichageQuestionForm.listeQ);
    		sujetTemp.insertSujet(listeQuestionA);
    	}
    	sujetQ.add(sujetTemp);
		req.setAttribute("listeSujet",sujetQ);
		this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);
	}
    
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String sujetTest = req.getParameter("test1");
		String competenceRechercher = req.getParameter("competenceR");
		List <Question> listeRechercher = new ArrayList <>();
		listeRechercher = Question.trouveQuestionParMatiere(competenceRechercher, CreationEtAffichageQuestionForm.listeQ);
		req.setAttribute("listQuestionR", listeRechercher);
		req.setAttribute("sujetTest", sujetTest);
        this.getServletContext().getRequestDispatcher("/creationSujet.jsp").forward(req, resp);
	}
}
