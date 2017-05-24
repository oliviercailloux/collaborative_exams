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


@WebServlet("/QuestionnaireForm")
public class CreationQuestionnaire extends HttpServlet 
{
    public static List <Question> listeQ = new ArrayList <>();
    @Inject
    GestionReponse reponse;
    @Inject
    GestionQuestion questionSearch;
    @Inject
    GestionQuestionnaire questionnaireT;
    @Inject
    GestionSujet sujetTemp;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	//permet de récupérer la liste des questions sélectionnées
    	String[] values = req.getParameterValues("questionSelect");
    	
    	//Recupération nom Sujet et création sujet
    	System.out.println(values + "tesststtete");
    	if(values!=null)
    	{
    		for(int i=0; i<values.length;i++)
        	{
        		String idTemp = values[i];
        		listeQ.add(questionnaireT.addQuestionListTemp(idTemp));
        		
        	}
    	}
		
        String nomQ = req.getParameter("questionnaireNom");
        
    	if(nomQ.isEmpty()==true)
    	{
    		System.out.println("pourqoi" + listeQ);
    		req.setAttribute("questionnaireVal",req.getParameter("questionnaireNomBis"));
    		req.setAttribute("listQuestionR", questionSearch.retourneDiff(listeQ));
    		req.setAttribute("listeSujet", sujetTemp.getNomSujets());
    		this.getServletContext().getRequestDispatcher("/questionnaire.jsp").forward(req, resp);
    	}
    	else
    	{
    		questionnaireT.createQuestionnaire(nomQ);
        	questionnaireT.ouvertureQuestionnaire();
        	//navigue et récupère les questions sélectionées.
        	for(int i=0; i<listeQ.size();i++)
        	{
        		questionnaireT.addQuestion(listeQ.get(i));
        	}
        	//sujetQ.add(sujetTemp);
        	questionnaireT.commitQuestionnaire();

        	req.setAttribute("listeQuestionnaire",questionnaireT.getNomQuestionnaires());
    		this.getServletContext().getRequestDispatcher("/affichageQuestionnaire.jsp").forward(req, resp);
       	}
    		
    	
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
        req.setAttribute("listQuestionR", questionSearch.retourneToutesQuestions());
		if(req.getParameter("competenceR")!=null && req.getParameter("competenceR").isEmpty()!=true)
		{
			String competenceRechercher = req.getParameter("competenceR");
			req.setAttribute("listQuestionR", questionSearch.retourneListQuestionComp(competenceRechercher));
			req.setAttribute("competenceR", competenceRechercher);
	        
		}
		else if (req.getParameter("sujetR")!=null && req.getParameter("sujetR").isEmpty()!=true)
		{
			String nom = req.getParameter("sujetR");
			req.setAttribute("listQuestionR", sujetTemp.getQuestionsSujet(nom));
			req.setAttribute("sujetNomR",nom);
		}
		req.setAttribute("listeSujet", sujetTemp.getNomSujets());
		String questionnaireVal = req.getParameter("test1");
		if(questionnaireVal == null)
			questionnaireVal = req.getParameter("test3");
		
		req.setAttribute("questionnaireVal", questionnaireVal);
		this.getServletContext().getRequestDispatcher("/questionnaire.jsp").forward(req, resp);
		
	}
}