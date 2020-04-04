package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class TraitementUtilisateur {
	
	public static boolean ajouterUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		String sql = "INSERT INTO user values (null,?,?,?,?)";
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1, utilisateur.getNom());
		st.setString(2, utilisateur.getPrenom());
		st.setString(3, utilisateur.getEmail());
		st.setString(4, utilisateur.getMdp());
		int count = st.executeUpdate();
		return (count > 0);
	}
	
	public static boolean supprimerUtilisateur(String email) throws ClassNotFoundException, SQLException
	{
		Connection cn = null;
		Statement st = null;
		String sql = "Delete from user where email = '"+email+"'";
		cn = ConnectionLV.getConnection();
		st = cn.createStatement();
		int count = st.executeUpdate(sql);
		return (count > 0);
	}
	
	public static boolean authentification(String email, String mdp) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		String sql = "Select * from user where email= ? and password= ? ";
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1, email);
		st.setString(2, mdp);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
	    return true;
		}
		return false;
	}
	
	public static boolean getProfil(int id) throws ClassNotFoundException, SQLException {
		String sql = "Select * from user where id= " + id;
		return true;
	}
	
	public static boolean modifierProfil(int id) throws ClassNotFoundException, SQLException {
		return true;
	}
}
