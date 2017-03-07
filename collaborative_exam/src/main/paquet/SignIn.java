package paquet;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainPackage.*;

@WebServlet("/connect")
public class SignIn extends HttpServlet
{
  
    @Inject
	private Compte test_connexion;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String resultat="";
		String login = req.getParameter("login");
		String pass = req.getParameter("pass");
		
		if(test_connexion.connexion(login,pass,Inscription.listeC)) 
			this.getServletContext().getRequestDispatcher("/ok.jsp").forward(req, resp);
		else resultat = "Mot de passe ou identifiant incorrect";
		req.setAttribute("resultat", resultat);
		
		this.getServletContext().getRequestDispatcher("/connect.jsp").forward(req, resp);

	}
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().println("Marimoh");
		this.getServletContext().getRequestDispatcher("/ok.jsp").forward(req, resp);
	}
	

}