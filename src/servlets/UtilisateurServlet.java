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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("hidden").equals("authentification")) {
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			try {
				boolean x = TraitementUtilisateur.authentification(email, mdp);
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				if (x) {
					out.println("<html> <body> <h1> Authentification réussie </h1> </body> </html>");
				} else {
					out.println("<html> <body> <h1> Authentification échoué </h1> </body> </html>");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("hidden").equals("profil")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				Utilisateur utilisateur = TraitementUtilisateur.getProfil(id);
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<html> <body> <h1> profil :</h1>"
						+ "<h2> profil :"+utilisateur.toString()+" </h2> </body> </html>");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//récupérer les informations du profil a modifier
		//appeler la methode TraitementUtilisateur.modifierProfil();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setMdp(request.getParameter("mdp"));

		try {
			boolean x = TraitementUtilisateur.ajouterUtilisateur(utilisateur);
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");

		try {
			boolean x = TraitementUtilisateur.supprimerUtilisateur(email);
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
