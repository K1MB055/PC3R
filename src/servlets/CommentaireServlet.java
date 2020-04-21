package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Commentaire;
import modele.TraitementCommentaire;
import modele.TraitementUtilisateur;
import modele.Utilisateur;
import modele.Utils;

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
		int idRencontre = Integer.parseInt(request.getParameter("idRencontre"));
		try {
			ArrayList<Commentaire> comments = TraitementCommentaire.getListCommentaire(idRencontre);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String reponse ="";
			for(Commentaire c : comments){
				Utilisateur utilisateur = TraitementUtilisateur.getProfil(c.getIdUser());
				reponse+="<li id='"+c.getId()+"'>"+
							"<div class='row'>"+
								"<div class='col-sm-10'><h4>"+utilisateur.getNom()+" "+utilisateur.getPrenom()+"</h4></div>";
							if(request.getSession(false).getAttribute("id").equals(c.getIdUser())){
				reponse+=		"<div class='col-sm-2 btn-group'>"+
									"<button class='btn btn-secondary btn-sm edit'> <i class='fas fa-edit'></i></button> "+
									"<button class='btn btn-danger btn-sm supp'> <i class='fas fa-trash-alt'></i></button>"+
								"</div>";
							}
				reponse+=  "</div>"+
						    "<div class='row'>"+
								"<p>"+c.getContenu()+"</p>"+
							"</div>"+
							"<div class='row'>"+
								"<span class='date sub-text'>"+c.getDate()+"</span>"+
							"</div>"+
						  "</li>";
			}
			out.println(reponse);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contenu = request.getParameter("contenu");
		int id = Integer.parseInt(request.getParameter("id"));
		Commentaire commentaire = new Commentaire();
		commentaire.setId(id);
		commentaire.setContenu(contenu);
		try {
			boolean x = TraitementCommentaire.modifierCommentaire(commentaire);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if(x){
				out.println("<p>"+contenu+"</p>");
			}
			else{
				out.println("<p>échec de la modification</p>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Map<String, String> data = Utils.getParameterMap(request);
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(data.get("contenu").replace('+',' '));
		commentaire.setDate(new Date());
		int idRencontre = Integer.parseInt(data.get("idRencontre"));
		commentaire.setIdRencontre(idRencontre);
		commentaire.setIdUser((int)session.getAttribute("id"));
		
		try {
			boolean x = TraitementCommentaire.ajouterCommentaire(commentaire);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				ArrayList<Commentaire> comments = TraitementCommentaire.getListCommentaire(idRencontre);
				String reponse ="";
				for(Commentaire c : comments){
				Utilisateur utilisateur = TraitementUtilisateur.getProfil(c.getIdUser());
				reponse+="<li id='"+c.getId()+"'>"+
							"<div class='row'>"+
								"<div class='col-sm-10'><h4>"+utilisateur.getNom()+" "+utilisateur.getPrenom()+"</h4></div>";
							if(request.getSession(false).getAttribute("id").equals(c.getIdUser())){
				reponse+=		"<div class='col-sm-2 btn-group'>"+
									"<button class='btn btn-secondary btn-sm edit'> <i class='fas fa-edit'></i></button> "+
									"<button class='btn btn-danger btn-sm supp'> <i class='fas fa-trash-alt'></i></button>"+
								"</div>";
							}
				reponse+=  "</div>"+
						    "<div class='row'>"+
								"<p>"+c.getContenu()+"</p>"+
							"</div>"+
							"<div class='row'>"+
								"<span class='date sub-text'>"+c.getDate()+"</span>"+
							"</div>"+
						  "</li>";
			}
				out.println(reponse);
			} else {
				out.println("<h2> échec de l'ajout réessayer plus tard </h2>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> data = Utils.getParameterMap(request);
		int id = Integer.parseInt(data.get("id"));

		try {
			boolean x = TraitementCommentaire.supprimerCommentaire(id);
			PrintWriter out = response.getWriter();
			out.println(String.valueOf(x));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
