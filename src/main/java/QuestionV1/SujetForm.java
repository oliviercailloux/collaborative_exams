package QuestionV1;

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
    public static List <Sujet> sujetQ = new ArrayList <Sujet>();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	String[] values = req.getParameterValues("questionSelect");
    	String nomS = req.getParameter("test1");
    	System.out.println("---------------  Sujet2 " +nomS);
    	Sujet sujetTemp =new Sujet(nomS);
    	System.out.println("---------------  Sujet " +sujetTemp.nomSujet);
    	for(int i=0; i<values.length;i++)
    	{
    		Question listeQuestionA = new Question();
    		listeQuestionA = Question.getQuestion(values[i], Pform.listeQ);
    		sujetTemp.insertSujet(listeQuestionA);
    	}
    	sujetQ.add(sujetTemp);
		req.setAttribute("listeSujet",sujetQ);
		this.getServletContext().getRequestDispatcher("/afficheQuestion.jsp").forward(req, resp);;
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String sujetTest = req.getParameter("test1");
		System.out.println("---------------  Sujet2 " +sujetTest);
		
		String competenceRechercher = req.getParameter("competenceR");
		List <Question> listeRechercher = new ArrayList <Question>();
		listeRechercher = Question.trouveQuestionParMatiere(competenceRechercher, Pform.listeQ);
		req.setAttribute("listQuestionR", listeRechercher);
		req.setAttribute("sujetTest", sujetTest);
        this.getServletContext().getRequestDispatcher("/creationSujet.jsp").forward(req, resp);;
	}
}
