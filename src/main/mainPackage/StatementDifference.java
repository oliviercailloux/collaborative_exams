package mainPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Difference")
public class StatementDifference extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String idSearch = req.getParameter("param1");
		String author = req.getParameter("param2");
		String temp = idSearch+ "+"+ author;
		Question listQuestionA = new Question();
		Question quest2 = new Question();
		//Recherche et Recupere une question
		listQuestionA = Question.getQuestion(temp, CreateDisplayQuestionForm.listQ);
		quest2 = Question.getQuestion(listQuestionA.getVariant(), CreateDisplayQuestionForm.listQ);
		req.setAttribute("enonceV",listQuestionA.getStatement());
		req.setAttribute("enonceP",quest2.getStatement());
		this.getServletContext().getRequestDispatcher("/afficheDiffOriginaleVariante.jsp").forward(req, resp);
	}
}
