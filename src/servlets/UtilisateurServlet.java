package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.TraitementUtilisateur;
import modele.Utilisateur;
import modele.Utils;

/**
 * Servlet implementation class utilisateur_servlet
 */
@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hidden = request.getParameter("hidden");
		
		if (hidden.equals("authentification")) {
			String email = request.getParameter("email").toLowerCase();
			String mdp = request.getParameter("mdp");
			try {
				boolean x = TraitementUtilisateur.authentification(email, mdp);
				if (x) {
					request.getSession().invalidate();
					HttpSession session = request.getSession();
					try {
					Utilisateur utilisateur = TraitementUtilisateur.getProfil(email);
						session.setAttribute("email", email);
						session.setAttribute("nom",utilisateur.getNom());
						session.setAttribute("prenom", utilisateur.getPrenom());
						session.setAttribute("id", utilisateur.getId());
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					session.setAttribute("connected", "true");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				} else {
					response.getWriter().print("Identifiants incorrectes");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (hidden.equals("checkEmail")){
			String email = request.getParameter("email");
		    response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain"); 
			if(email.equals(request.getSession(false).getAttribute("email"))){
				response.getWriter().print("true");
			}
			else{
				try {
					boolean x = TraitementUtilisateur.verifierEmail(email);
					response.getWriter().print(String.valueOf(!x));
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					}
			}	
		}
		else if (hidden.equals("deconnexion")){
			request.getSession().invalidate();
		    response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId((int) session.getAttribute("id"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setMdp(request.getParameter("mdp"));
		try {
			boolean x = TraitementUtilisateur.modifierProfil(utilisateur);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String reponse = "";
			if(x){
					reponse = "<div class='alert alert-success alert-dismissible'>"+
							"<a href='#' class='close' data-dismiss='alert'"+
								"aria-label='close'>&times;</a> "+
								"<strong>Succés!</strong>	profil	mis a jour avec succés"+
						 "</div>";
					//mettre à jour les donnés stockés dans la session
					session.setAttribute("nom", utilisateur.getNom());
					session.setAttribute("prenom", utilisateur.getPrenom());
					session.setAttribute("email", utilisateur.getEmail());
			}
			else{
					reponse = "<div class='alert alert-danger alert-dismissible'>"+
									"<a href='#' class='close' data-dismiss='alert'"+
										"aria-label='close'>&times;</a> "+
										"<strong>Echec!</strong>	Changement non enregistrer"+
								 "</div>";
			}
			out.println(reponse);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//appeler la methode TraitementUtilisateur.modifierProfil();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String> data = Utils.getParameterMap(request);
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(data.get("nom"));
		utilisateur.setPrenom(data.get("prenom"));
		utilisateur.setEmail(data.get("email").replaceAll("%40","@").toLowerCase());
		utilisateur.setMdp(data.get("mdp"));
		System.out.println(data.toString());
		try {
			boolean x = TraitementUtilisateur.ajouterUtilisateur(utilisateur);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				out.println("<h1> Inscription réussie </h1>");
			} else {
				out.println("<h1> Inscription échoué </h1>");
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
