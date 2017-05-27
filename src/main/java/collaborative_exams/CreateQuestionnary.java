package collaborative_exams;
import java.io.IOException;

import java.util.ArrayList;
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


@WebServlet("/QuestionnaryForm")
public class CreateQuestionnary extends HttpServlet 
{
    public static List <Question> listQ = new ArrayList <>();
    @Inject
    QuestionManager questionSearch;
    @Inject
    QuestionnaryManager questionnaryT;
    @Inject
    SubjectManager subjectTemp;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	//permet de récupérer la liste des questions sélectionnées
    	String[] values = req.getParameterValues("questionSelect");
    	
    	//Recupération nom Sujet et création sujet
    	if(values!=null)
    	{
    		for(int i=0; i<values.length;i++)
        	{
        		String idTemp = values[i];
        		listQ.add(questionnaryT.addQuestionListTemp(idTemp));
        		
        	}
    	}
		
        String nomQ = req.getParameter("questionnaryName");
        
    	if(nomQ.isEmpty()==true)
    	{
    		System.out.println("pourqoi" + listQ);
    		req.setAttribute("questionnaryVal",req.getParameter("questionnaryNameBis"));
    		req.setAttribute("listQuestionR", questionSearch.returnDiff(listQ));
    		req.setAttribute("listSubject", subjectTemp.getNameSubjects());
    		this.getServletContext().getRequestDispatcher("/questionnary.jsp").forward(req, resp);
    	}
    	else
    	{
    		questionnaryT.createQuestionnary(nomQ);
        	questionnaryT.openQuestionnary();
        	//navigue et récupère les questions sélectionées.
        	for(int i=0; i<listQ.size();i++)
        	{
        		questionnaryT.addQuestion(listQ.get(i));
        	}
        	questionnaryT.commitQuestionnary();
        	listQ.clear();
        	req.setAttribute("listQuestionnary",questionnaryT.getNameQuestionnaries());
    		this.getServletContext().getRequestDispatcher("/displayQuestionnary.jsp").forward(req, resp);
       	}
    		
    	
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
        req.setAttribute("listQuestionR", questionSearch.returnAllQuestions());
		if(req.getParameter("skillR")!=null && req.getParameter("skillR").isEmpty()!=true)
		{
			String competenceRechercher = req.getParameter("skillR");
			req.setAttribute("listQuestionR", questionSearch.returnListQuestionSkill(competenceRechercher));
			req.setAttribute("skillR", competenceRechercher);
	        
		}
		else if (req.getParameter("subjectR")!=null && req.getParameter("subjectR").isEmpty()!=true)
		{
			String nom = req.getParameter("subjectR");
			req.setAttribute("listQuestionR", subjectTemp.getQuestionsSubject(nom));
			req.setAttribute("subjectR",nom);
		}
		req.setAttribute("listSubject", subjectTemp.getNameSubjects());
		String questionnaireVal = req.getParameter("test1");
		if(questionnaireVal == null)
			questionnaireVal = req.getParameter("test3");
		
		req.setAttribute("questionnaryVal", questionnaireVal);
		this.getServletContext().getRequestDispatcher("/questionnary.jsp").forward(req, resp);
		
	}
}