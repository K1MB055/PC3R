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
		String sql = "Select * from user where email= ? and mdp= ? ";
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
	
	public static Utilisateur getProfil(String email) throws ClassNotFoundException, SQLException {
		int id = 0;
		String nom = null;
		String prenom = null;
		String mdp = null;
		Connection cn = null;
		Statement st = null;
		String sql = "Select * from user where email= '" + email + "'";
		cn = ConnectionLV.getConnection();
		st = cn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			 id = rs.getInt("id");
			 nom = rs.getString("nom");
			 prenom = rs.getString("prenom");
			 mdp = "******";
		}
		return new Utilisateur (id,nom,prenom,email,mdp);
	}
	
	public static boolean modifierProfil(int id) throws ClassNotFoundException, SQLException {
		return true;
	}

	public static boolean verifierEmail(String email) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		String sql = "Select * from user where email= ? ";
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
