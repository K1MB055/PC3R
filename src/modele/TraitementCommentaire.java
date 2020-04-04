package modele;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class TraitementCommentaire {
	
	public static boolean ajouterCommentaire(Commentaire comm) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		String sql = "INSERT INTO comments values (null,?,?)";
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1, comm.getContenu());
		st.setDate(2, comm.getDate());
		st.setInt(3, comm.getIdRencontre());
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
	public static ArrayList<Commentaire> getCommentaire(int idRencontre) throws ClassNotFoundException, SQLException
	{
		Connection cn = null;
		Statement st = null;
		String sql = "Select * from comments where id = " + idRencontre;
		cn = ConnectionLV.getConnection();
		st = cn.createStatement();
		//...
		return null;
	}
	
	public static boolean modifierCommentaire(int id) throws ClassNotFoundException, SQLException
	{
		return true;
	}
	
}
