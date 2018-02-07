package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.base;


@WebServlet("/modifier")
public class modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			//users = (Utilisateur)session.getAttribute("users");
			//request.setAttribute("msg", users);
			String email =(String) session.getAttribute("email");
			request.setAttribute("email", email);

			this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			base b=new base();
			//Utilisateur users = (Utilisateur)session.getAttribute("users");
			  String nom=request.getParameter("nom");
			  String prenom=request.getParameter("prenom");
			  String pass=request.getParameter("n_pass");
			 
			  String email =(String) session.getAttribute("email");
				request.setAttribute("email", email);
				System.out.println(email);
				System.out.println(nom);
				 b.modifier(nom,prenom,pass,email);
			  response.sendRedirect( request.getContextPath()+"/index");
			  /*boolean rs=b.verifemail(request.getParameter("email"));
			    if (rs==false)
			    {*/
		        	/*users.setNom_user();
		        	users.setPrenom_user(request.getParameter("prnom"));
			        users.setPass_user(request.getParameter("n_pass"));
		        	users.setEmail_user(request.getParameter("email")); 
		        	request.setAttribute("msg", users.getNom_user());*/
		         	//b.modifier(users);
		         	//response.sendRedirect( request.getContextPath()+"/index");
		     	/*}else{
		     		String err="votre emil deja utiliser";
					 request.setAttribute("msg", err);
					 this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
		     	}*/
			
			 /*String nom= request.getParameter("nom");
			String prenom= request.getParameter("prenom");
			String mail= request.getParameter("email");
			String pass= request.getParameter("pass");*/
			
		        	//this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
			
		}
	
}
