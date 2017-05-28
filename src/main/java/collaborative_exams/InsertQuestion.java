package collaborative_exams;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FormInsert")
public class InsertQuestion extends HttpServlet 
{
	@Inject
    QuestionManager question;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException
	{
		String nom = request.getParameter("author");
		int id = Integer.parseInt( request.getParameter("id"));
		String statement = request.getParameter("question");
		String language = request.getParameter("languageN");
		String skill = request.getParameter("skillN");
		String level = request.getParameter("level");	
		String nb = ""+Integer.parseInt( request.getParameter("nbAnswer"));
        question.openQuestion(nom, language, skill, statement, id,level);
        String temp =  ""+question.getQuestion().getIdTech();
        request.setAttribute("id", temp);
        request.setAttribute("nbAnswer", nb);
        request.setAttribute("statement",statement);
        request.setAttribute("aut", nom);
        this.getServletContext().getRequestDispatcher("/answer.jsp").forward(request, resp);
	}

	
}
