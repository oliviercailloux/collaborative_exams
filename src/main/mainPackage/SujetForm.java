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

public class SujetForm extends HttpServlet 
{
	@Inject
	GestionQuestion questionSearch;
	@Inject
	GestionSujet sujetTemp;
    public static List <Sujet> sujetQ = new ArrayList <>();
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
    	//permet de récupérer la liste des questions sélectionnées
    	String[] values = req.getParameterValues("questionSelect");
    	
    	//Recupération nom Sujet et création sujet
    	String nomS = req.getParameter("sujetNom");
    	sujetTemp.createSujet(nomS);
    	sujetTemp.ouvertureSujet();
    	//navigue et récupère les questions sélectionées.
    	for(int i=0; i<values.length;i++)
    	{
    		String idTemp = values[i];
    		sujetTemp.addQuestion(idTemp);
    	}
    	//sujetQ.add(sujetTemp);
    	sujetTemp.commitSujet();
        req.setAttribute("listQuestionR", questionSearch.retourneToutesQuestions());
		req.setAttribute("listeSujet",sujetTemp.getNomSujets());
		this.getServletContext().getRequestDispatcher("/afficheQuestionJPA.jsp").forward(req, resp);
	}
    
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String competenceRechercher = req.getParameter("competenceR");
		String sujetTest = req.getParameter("test1");
		req.setAttribute("listQuestionR", questionSearch.retourneListQuestionComp(competenceRechercher));
		req.setAttribute("sujetTest", sujetTest);
        this.getServletContext().getRequestDispatcher("/creationSujet.jsp").forward(req, resp);
	}
}
