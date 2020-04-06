package modele;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class TraitementRencontre {
	
	public static boolean ajouterRencontre(Rencontre rencontre) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		String sql = "INSERT INTO rencontre values (null,?,?,?,?,?,?,?,?)";
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		st.setString(1, rencontre.getCompetition());
		st.setString(2, rencontre.getTour());
		st.setDate(3, rencontre.getDate());
		st.setString(4, rencontre.getStatus());
		st.setString(5, rencontre.getHomeTeam());
		st.setString(6, rencontre.getAwayTeam());
		st.setInt(7, rencontre.getScoreHomeTeam());
		st.setInt(8, rencontre.getScoreAwayTeam());
		int count = st.executeUpdate();
		return (count > 0);
	}
	
	public static boolean supprimerRencontre(int id) throws ClassNotFoundException, SQLException
	{
		Connection cn = null;
		Statement st = null;
		String sql = "Delete from match where id = " + id;
		cn = ConnectionLV.getConnection();
		st = cn.createStatement();
		int count = st.executeUpdate(sql);
		return (count > 0);
	}
	
	public static boolean modifierRencontre() throws ClassNotFoundException, SQLException 
	{
		return true;
	}
	
	public static ArrayList<Rencontre> rechercherRencontre() throws ClassNotFoundException, SQLException 
	{
		return null;
	}
	
}
