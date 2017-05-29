package collaborative_exams;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Difference")
public class DifferenceStatement extends HttpServlet
{
	@Inject
    QuestionManager questionM;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String idSon = req.getParameter("param1");
		String idParent = req.getParameter("param2");
		req.setAttribute("questionP", questionM.getQuestion(idSon));
		req.setAttribute("questionv", questionM.getQuestion(idParent.replace("/", "")));
		req.setAttribute("statementV",questionM.getQuestion(idSon).getStatement());
		req.setAttribute("statementP",questionM.getQuestion(idParent.replace("/", "")).getStatement());
		this.getServletContext().getRequestDispatcher("/displayDiffOriginalVariant.jsp").forward(req, resp);
	}
}
