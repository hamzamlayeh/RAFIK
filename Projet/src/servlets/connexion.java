package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.base;

@WebServlet("/connexion")
public class connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public connexion() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		base b=new base();
		String email= request.getParameter("email");
		String pass= request.getParameter("pass");
		
		boolean rs =b.virfConex(pass,email);
		if (rs==false)
		{
			String err="Votre Email ou mot de passe incorecte!";
			request.setAttribute("msg", err);
			response.sendRedirect( request.getContextPath()+"/connexion");
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect( request.getContextPath()+"/index");
		}
			
			
	}

}
