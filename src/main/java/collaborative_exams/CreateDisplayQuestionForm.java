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


@WebServlet("/Pform")
public class CreateDisplayQuestionForm extends HttpServlet 
{
    public static List <Question> listQ = new ArrayList <>();
    @Inject
    AnswerManager answer;
    @Inject
    QuestionManager questionM;
    @Inject
    SubjectManager subjectT;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        int nbAnswer = Integer.parseInt(req.getParameter("nb"));
        int conditionAnswer =1;  
        questionM.initiateQuestion(Integer.parseInt(req.getParameter("idQuest")));
        //create and insert answers
        while(conditionAnswer <= nbAnswer)
        {
        	String answerId = "textAnswer"+conditionAnswer;
        	String positionID = "pos"+conditionAnswer;
        	String textAnswer = req.getParameter(answerId);
        	String pos = req.getParameter(positionID);
        	answer.createAnswer(questionM.getQuestion(), textAnswer, pos);
        	try {
        		questionM.addAnswer(answer.getAnswer());
        		
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
        	conditionAnswer=conditionAnswer+1;
        }//end create and insert answers
        questionM.commitQuestionAnswer();
        
        req.setAttribute("listQuestionR", questionM.returnAllQuestions());
        req.setAttribute("listSubject", subjectT.getNameSubjects());
        this.getServletContext().getRequestDispatcher("/displayQuestionJPA.jsp").forward(req, resp);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		if(req.getParameter("skillR")!=null && req.getParameter("skillR").isEmpty()!=true)
		{
			String competenceRechercher = req.getParameter("skillR");
			req.setAttribute("listQuestionR", questionM.returnListQuestionSkill(competenceRechercher));
			req.setAttribute("skillR", competenceRechercher);
	        
		}
		else if (req.getParameter("subjectR")!=null && req.getParameter("subjectR").isEmpty()!=true)
		{
			String nom = req.getParameter("subjectR");
			req.setAttribute("listQuestionR", subjectT.getQuestionsSubject(nom));
			req.setAttribute("subjectNameR",nom);
		}
		else
		{
	        req.setAttribute("listQuestionR", questionM.returnAllQuestions());
		}
		req.setAttribute("listSubject", subjectT.getNameSubjects());
		this.getServletContext().getRequestDispatcher("/displayQuestionJPA.jsp").forward(req, resp);
		
	}
}