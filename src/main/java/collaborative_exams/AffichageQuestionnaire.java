package collaborative_exams;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/affichageQuestionnaire")
public class AffichageQuestionnaire extends HttpServlet 
{
    @Inject
    GestionQuestion testQuestion;
    @Inject
    GestionQuestionnaire questionnaireT;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

    	
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		req.setAttribute("listeQuestionnaire",questionnaireT.getNomQuestionnaires());
		
		if(req.getParameter("questionnaireR")!=null && req.getParameter("questionnaireR").isEmpty()!=true)
		{
			String questionnaireRechercher = req.getParameter("questionnaireR");
			req.setAttribute("listQuestionR", questionnaireT.getQuestionsQuestionnaire(questionnaireRechercher));
			req.setAttribute("questionnaireR", questionnaireRechercher);
			if(req.getParameter("Questionnaire_envoi")!=null && !req.getParameter("Questionnaire_envoi").isEmpty())
			{
				int score = 0;
				int scoreTotal = 0;
				for(Question q : questionnaireT.getQuestionsQuestionnaire(questionnaireRechercher))
				{	
					for(Reponse r: q.reponseR())
					{	
						for(int i=0;i<req.getParameterValues(Integer.toString(q.getIdTech())).length;i++)
						{
							if(req.getParameterValues(Integer.toString(q.getIdTech()))[i].equals(r.getText()) && r.getPos().equals("Vrai"))
							{
								req.getParameterValues(Integer.toString(q.getIdTech()))[i] =  "";
								score = score + 1;
								break;
							}
						}
						
						if(r.getPos().equals("Vrai")) scoreTotal ++;
					}
				}
				req.setAttribute("score", score);
				req.setAttribute("scoreTotal", scoreTotal);
				score=0;
				scoreTotal = 0;
				req.setAttribute("result", "result");
				
			}
	        
		}
		else 
		{
			req.setAttribute("questionnaireR", "--- Questionnaires ---");
		}
		this.getServletContext().getRequestDispatcher("/affichageQuestionnaire.jsp").forward(req, resp);
		
	}
}