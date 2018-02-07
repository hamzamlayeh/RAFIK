package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bdd.base;
import beans.Utilisateur;
import beans.rapport;


@WebServlet("/parametre")
public class parametre extends HttpServlet{
	private static final long serialVersionUID = 1L;
	base b=new base();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 String email =(String) session.getAttribute("email");
		 request.setAttribute("email", email);
		 
		
		List<rapport> liste=new ArrayList<rapport>();
		liste=b.list();
		//System.out.println(liste.toString());
	     request.setAttribute("list", liste);
	     
		this.getServletContext().getRequestDispatcher("/WEB-INF/parametre.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur users = (Utilisateur)session.getAttribute("users");
		
		int id_user=users.getId_user();
		rapport rap = new rapport();
		rap.setId_user(id_user);
		rap.setNom_etudi(request.getParameter("nom"));
		rap.setPrenom_etudiant(request.getParameter("prnom"));
		rap.setTitre_rapport(request.getParameter("titre"));
		rap.setBranche_etudes(request.getParameter("branche"));
		rap.setMention_rapport(request.getParameter("montion"));
		rap.setAnnee_rapport(request.getParameter("annee"));
		rap.setDescription(request.getParameter("desc"));
		rap.setRapport(request.getParameter("file"));

	     b.ajout(rap);
	     response.sendRedirect( request.getContextPath()+"/index");
		
		
		
		
	}
}
