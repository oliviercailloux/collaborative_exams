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
    QuestionManager testQuestion;
    @Inject
    SubjectManager subject;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		String name = (String)session.getAttribute("auteur");
        int id = (int) session.getAttribute("identifiant");
        String statement = (String)session.getAttribute("question");
        String language = (String)session.getAttribute("langueN");
        String skill = (String)session.getAttribute("competenceN");
        String level = (String)session.getAttribute("niveau");
        int nbAnswer = (int)session.getAttribute("nb");
        int conditionAnswer =1;  
       // Question insert = new Question();
        testQuestion.createQuestion(name, language, skill, statement, id,level);
        testQuestion.openQuestion();
        //creation et insertion des answers
        while(conditionAnswer <= nbAnswer)
        {
        	String answerId = "textReponse"+conditionAnswer;
        	String positionID = "pos"+conditionAnswer;
        	String textanswer = req.getParameter(answerId);
        	String pos = req.getParameter(positionID);
        	//answer.createanswer(textanswer, pos);
        	testQuestion.setAnswerG(answer.createAnswer(testQuestion.getQuestion(), textanswer, pos));
        	try {
        		testQuestion.addAnswer(answer.getAnswer());
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
        	conditionAnswer=conditionAnswer+1;
        }//fin creation et insertion des answers
        
        testQuestion.commitQuestion();
        
        req.setAttribute("listQuestionR", testQuestion.returnAllQuestions());
        req.setAttribute("listeSujet", subject.getSubjectName());
        session.invalidate();
        this.getServletContext().getRequestDispatcher("/afficheQuestionJPA.jsp").forward(req, resp);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
        req.setAttribute("listQuestionR", testQuestion.returnAllQuestions());
		if(req.getParameter("competenceR")!=null && req.getParameter("competenceR").isEmpty()!=true)
		{
			String searchSkill = req.getParameter("competenceR");
			req.setAttribute("listQuestionR", testQuestion.returnListQuestionSkill(searchSkill));
			req.setAttribute("competenceR", searchSkill);
	        
		}
		else if (req.getParameter("sujetR")!=null && req.getParameter("sujetR").isEmpty()!=true)
		{
			String name = req.getParameter("sujetR");
			req.setAttribute("listQuestionR", subject.getSubjectQuestions(name));
			req.setAttribute("sujetNomR",name);
		}
		req.setAttribute("listeSujet", subject.getSubjectName());
		this.getServletContext().getRequestDispatcher("/afficheQuestionJPA.jsp").forward(req, resp);
		
	}
}