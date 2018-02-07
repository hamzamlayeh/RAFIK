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

@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utilisateur user =new Utilisateur();
	rapport rapport=new rapport();
	 base b=new base();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 String email =(String) session.getAttribute("email");
		 request.setAttribute("email", email);
		 
		   user = b.recherUser(email);
		 
		   session.setAttribute("users", user);
		   List<rapport> list=new ArrayList<rapport>();
			list=b.list();
			request.setAttribute("list", list);
			//System.out.println(list.toString());
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		
		
	}
		
}
