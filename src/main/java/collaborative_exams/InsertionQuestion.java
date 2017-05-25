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
public class InsertionQuestion extends HttpServlet 
{
	@Inject
    GestionQuestion question;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException
	{
		String nom = request.getParameter("auteur");
		int identifiant = Integer.parseInt( request.getParameter("id"));
		String enonce = request.getParameter("question");
		String langueI = request.getParameter("langueN");
		String competenceI = request.getParameter("competenceN");
		String niveau = request.getParameter("niveau");	
		String nb = ""+Integer.parseInt( request.getParameter("nbRep"));
        question.ouvertureQuestion(nom, langueI, competenceI, enonce, identifiant,niveau);
        String temp =  ""+question.getQuestion().getIdTech();
        System.out.println("isisisisisi"+ question.getQuestion().getIdTech());
        System.out.println("isisisisisi"+ question.getQuestion().getIdt());
        request.setAttribute("id", temp);
        request.setAttribute("nbRep", nb);
        request.setAttribute("aut", nom);
        this.getServletContext().getRequestDispatcher("/reponse.jsp").forward(request, resp);
	}

	
}
