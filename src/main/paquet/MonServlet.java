package paquet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

@WebServlet("/marmoh")
public class MonServlet extends HttpServlet{
	@Override
	
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().println("Marimoh");
	}

}
