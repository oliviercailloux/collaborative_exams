package mainPackage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SujetForm")

public class SubjectForm extends HttpServlet 
{
	@Inject
	QuestionManager questionSearch;
	@Inject
	SubjectManager subjectTemp;
    public static List <Subject> subjectQ = new ArrayList <>();
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	//permet de récupérer la liste des questions sélectionnées
    	String[] values = req.getParameterValues("questionSelect");
    	
    	//Recupération nom Sujet et création sujet
    	String nameS = req.getParameter("sujetNom");
    	subjectTemp.createSubject(nameS);
    	subjectTemp.openSubject();
    	//navigue et récupère les questions sélectionées.
    	for(int i=0; i<values.length;i++)
    	{
    		String idTemp = values[i];
    		subjectTemp.addQuestion(idTemp);
    	}
    	//subjectQ.add(subjectTemp);
    	subjectTemp.commitSubject();
        req.setAttribute("listQuestionR", questionSearch.returnAllQuestions());
		req.setAttribute("listeSujet",subjectTemp.getSubjectName());
		this.getServletContext().getRequestDispatcher("/afficheQuestionJPA.jsp").forward(req, resp);
	}
    
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String searchSkill = req.getParameter("competenceR");
		String subjectTest = req.getParameter("test1");
		req.setAttribute("listQuestionR", questionSearch.returnListQuestionSkill(searchSkill));
		req.setAttribute("subjectTest", subjectTest);
        this.getServletContext().getRequestDispatcher("/creationSujet.jsp").forward(req, resp);
	}
}
