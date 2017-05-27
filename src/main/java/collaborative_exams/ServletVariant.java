package collaborative_exams;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/FormVariant")
public class ServletVariant  extends HttpServlet
{
	@Inject
	QuestionManager insert;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Recupere les identifiants pour une question
		String searchId = req.getParameter("IdQParent");
		String variantType = req.getParameter("variantType");
				//Recherche et Recupere une question
		req.setAttribute("questionSearch",insert.getQuestion(searchId));
		req.setAttribute("varType",variantType);
		req.setAttribute("idParent",searchId);
		this.getServletContext().getRequestDispatcher("/FormVariant.jsp").forward(req, resp);
	}
}
