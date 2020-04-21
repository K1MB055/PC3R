package modele;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class TraitementCommentaire {
	
	public static boolean ajouterCommentaire(Commentaire commentaire) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		String sql = "INSERT INTO comments values (null,?,?,?,?)";
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1, commentaire.getContenu());
		st.setDate(2, new java.sql.Date(commentaire.getDate().getTime()));
		st.setInt(3, commentaire.getIdRencontre());
		st.setInt(4, commentaire.getIdUser());
		int count = st.executeUpdate();
		return (count > 0);
	}
	
	public static boolean supprimerCommentaire(int id) throws ClassNotFoundException, SQLException
	{
		Connection cn = null;
		Statement st = null;
		String sql = "Delete from comments where id = " + id;
		cn = ConnectionLV.getConnection();
		st = cn.createStatement();
		int count = st.executeUpdate(sql);
		return (count > 0);
	}
	
	//recupérer la liste de commentaire d'une recontre
	public static ArrayList<Commentaire> getListCommentaire(int idRencontre) throws ClassNotFoundException, SQLException
	{
		int idCommentaire = 0;
		String contenu;
		Date date;
		int idUser;
		//String pseudo = null;
		ArrayList<Commentaire> list = new ArrayList<Commentaire>();
		Connection cn = null;
		Statement st = null;
		String sql = "Select * FROM comments	WHERE  idRencontre= " + idRencontre;
		cn = ConnectionLV.getConnection();
		st = cn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			idCommentaire = rs.getInt("id");
			contenu = rs.getString("contenu");
			date = rs.getDate("date");
			idUser = rs.getInt("idUser");
			list.add(new Commentaire(idCommentaire, contenu, date,idRencontre,idUser));
		}
		return list;
	}
	
	public static boolean modifierCommentaire(Commentaire commentaire) throws ClassNotFoundException, SQLException
	{
		Connection cn = null;
		PreparedStatement st = null;
		
		String sql = "UPDATE comments  SET contenu = ? WHERE id ="+commentaire.getId();
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1,commentaire.getContenu());

		int count = st.executeUpdate();
		return (count > 0);
	}
	
}
