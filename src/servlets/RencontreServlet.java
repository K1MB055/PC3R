package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Rencontre;
import modele.TraitementRencontre;


/**
 * Servlet implementation class RencontreServlet
 */
@WebServlet("/RencontreServlet")
public class RencontreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RencontreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupérer les informations de la recherche 
		//récupérer la liste des rencontres résultat de l'appel à TraitementRencontre.recherche()
		//affichage du résulat 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupérer les nouvelles information de la rencontre
		//faire appelle à TraitementRencontre.mdofierRencontre() avec comme parametre les nouvelles infos
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Rencontre rencontre = new Rencontre();
		rencontre.setCompetition(request.getParameter("competition"));
		rencontre.setTour(request.getParameter("tour"));
		rencontre.setDate(null); //request.getParameter("date")
		rencontre.setStatus(request.getParameter("status"));
		rencontre.setHomeTeam(request.getParameter("homeTeam"));
		rencontre.setAwayTeam(request.getParameter("awayTeam"));
		rencontre.setScoreHomeTeam(Integer.parseInt(request.getParameter("scoreHomeTeam")));
		rencontre.setScoreAwayTeam(Integer.parseInt(request.getParameter("scoreAwayTeam")));
		try {
			boolean x = TraitementRencontre.ajouterRencontre(rencontre);
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
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			boolean x = TraitementRencontre.supprimerRencontre(id);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			if (x) {
				out.println("<html> <body> <h1> rencontre supprimé avec succés </h1> </body> </html>");
			} else {
				out.println("<html> <body> <h1> échec de la suppression  </h1> </body> </html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
