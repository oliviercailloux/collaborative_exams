import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormReponse")
public class FormReponse extends HttpServlet 
{

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String auteur = req.getParameter("auteur");
        int idQuestion = Integer.parseInt( req.getParameter("idQuestion"));
        String textReponse = req.getParameter("textReponse");
        Reponse reponse = new Reponse(idQuestion,auteur, textReponse);
        req.setAttribute("idQuestion",reponse.getIdQuestion());
        req.setAttribute("auteur",reponse.getAut());
        req.setAttribute("textReponse",reponse.getText());

        this.getServletContext().getRequestDispatcher("/reponse.jsp").forward(req, resp);;
	}
	
}
