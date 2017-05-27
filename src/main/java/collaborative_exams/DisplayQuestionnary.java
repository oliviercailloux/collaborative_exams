package collaborative_exams;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displayQuestionnary")
public class DisplayQuestionnary extends HttpServlet 
{
    @Inject
    QuestionManager questionM;
    @Inject
    QuestionnaryManager questionnaryT;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

    	
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		req.setAttribute("listQuestionnary",questionnaryT.getNameQuestionnaries());
		
		if(req.getParameter("questionnaryR")!=null && req.getParameter("questionnaryR").isEmpty()!=true)
		{
			String searchQuestionnary = req.getParameter("questionnaryR");
			req.setAttribute("listQuestionR", questionnaryT.getQuestionsQuestionnary(searchQuestionnary));
			req.setAttribute("questionnaryR", searchQuestionnary);
			if(req.getParameter("Questionnary_send")!=null && !req.getParameter("Questionnary_send").isEmpty())
			{
				int score = 0;
				int scoreTotal = 0;
				for(Question q : questionnaryT.getQuestionsQuestionnary(searchQuestionnary))
				{	
					for(Answer r: q.answerA())
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
				req.setAttribute("mark", score);
				req.setAttribute("totalMark", scoreTotal);
				score=0;
				scoreTotal = 0;
				req.setAttribute("result", "result");
				
			}
	        
		}
		else 
		{
			req.setAttribute("questionnaryR", "--- Questionnaires ---");
		}
		this.getServletContext().getRequestDispatcher("/displayQuestionnary.jsp").forward(req, resp);
		
	}
}