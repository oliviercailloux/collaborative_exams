package paquet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

@WebServlet("/connect")
public class SignIn extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String resultat="";
		String login = req.getParameter("login");
		String pass = req.getParameter("pass");
		
		if(login.equals("marmoh")){
			if(pass.equals("marmoh")) this.getServletContext().getRequestDispatcher("/ok.jsp").forward(req, resp);
			else resultat = "Mot de passe incorrect";
		}
		else resultat = "Identifiant incorrect";
		req.setAttribute("resultat", resultat);
		
		this.getServletContext().getRequestDispatcher("/connect.jsp").forward(req, resp);

	}
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().println("Marimoh");
		this.getServletContext().getRequestDispatcher("/ok.jsp").forward(req, resp);
	}
	

}
