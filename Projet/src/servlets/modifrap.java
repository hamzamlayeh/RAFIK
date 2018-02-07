package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.base;
import beans.rapport;
@WebServlet("/modifrap")
public class modifrap extends HttpServlet{

	private static final long serialVersionUID = 1L;
	base b =new base();
	rapport rap = new rapport();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 String email =(String) session.getAttribute("email");
		 request.setAttribute("email", email);
		 
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp").forward( request, response );
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id= Integer.parseInt(request.getParameter("id"));
		rap.setId_rapport(id);
		rap.setNom_etudi(request.getParameter("nom"));
		rap.setPrenom_etudiant(request.getParameter("prnom"));
		rap.setTitre_rapport(request.getParameter("titre"));
		rap.setAnnee_rapport(request.getParameter("annee"));
		rap.setBranche_etudes(request.getParameter("branche"));
		rap.setMention_rapport(request.getParameter("montion"));
		rap.setDescription(request.getParameter("desc"));
		rap.setRapport(request.getParameter("file"));
			b.modiferRap(rap);
		
			response.sendRedirect( request.getContextPath()+"/parametre");
	}
}
