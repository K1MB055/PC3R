package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.TraitementUtilisateur;
import modele.Utilisateur;

/**
 * Servlet implementation class utilisateur_servlet
 */
@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setMdp(request.getParameter("mdp"));
		
		try {
			boolean x = TraitementUtilisateur.AjouterUtilisateur(utilisateur);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				out.println("<html> <body> <h1> Inscription réussie </h1> </body> </html>");
			} else {
				out.println("<html> <body> <h1> Inscription échoué </h1> </body> </html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		try {
			boolean x = TraitementUtilisateur.SupprimerUtilisateur(email);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				out.println("<html> <body> <h1> utilisateur supprimé avec succés </h1> </body> </html>");
			} else {
				out.println("<html> <body> <h1> échec de la suppression  </h1> </body> </html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
