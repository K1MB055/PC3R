package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Commentaire;
import modele.TraitementCommentaire;

/**
 * Servlet implementation class CommentaireServlet
 */
@WebServlet("/CommentaireServlet")
public class CommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idRencontre = Integer.parseInt(request.getParameter("idRencontre"));
		try {
			ArrayList<Commentaire> liste = TraitementCommentaire.getListCommentaire(idRencontre);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html> <body> <h1> la liste des commentaires de la rencontre : </h1> </body> </html>");
			//affichage de la liste
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupérer l'id et le contenu d'un commantaire
		//faire appelle à TraitementCommentaire.mdofierCommentaire() avec comme parametre le nouveau commentaire
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(request.getParameter("contenu"));
		commentaire.setDate(new Date());
		try {
			boolean x = TraitementCommentaire.ajouterCommentaire(commentaire);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				out.println("<html> <body> <h1> Ajouté avec succés </h1> </body> </html>");
			} else {
				out.println("<html> <body> <h1> échec de l'ajout </h1> </body> </html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			boolean x = TraitementCommentaire.supprimerCommentaire(id);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				out.println("<html> <body> <h1> commentaire supprimé avec succés </h1> </body> </html>");
			} else {
				out.println("<html> <body> <h1> échec de la suppression  </h1> </body> </html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
