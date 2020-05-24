package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		if(rencontre.getStatus().equals("FINISHED")) {
			st.setInt(7, rencontre.getScoreHomeTeam().intValue());
			st.setInt(8, rencontre.getScoreAwayTeam().intValue());
		} else {
			st.setInt(7, 0);
			st.setInt(8, 0);
		}
		
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
	
	public static ArrayList<Rencontre> rechercherRencontre(String competition) throws ClassNotFoundException, SQLException 
	{
		Connection cn = null;
		Statement st = null;
		String sql = "Select * from Rencontre where true";
		
		if(competition!=null)
		{
			sql = sql + " and competition='"+competition+"'";
		}
		
		ArrayList <Rencontre> rencontres = new ArrayList<Rencontre>();
		Rencontre rencontre;
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		// executer la requête
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			rencontre = new Rencontre(rs.getInt("id"),rs.getString("competition"),rs.getString("tour"),
					rs.getDate("date"),rs.getString("status"),rs.getString("homeTeam"),rs.getString("awayTeam")
					,rs.getInt("scoreHomeTeam"),rs.getInt("scoreAwayTeam"));
			rencontres.add(rencontre);
		}
		
		return rencontres;
	}
	
	public static boolean isRencontreAlreadyIn(Rencontre rencontre) 
			throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		
		String sql = "SELECT * FROM rencontre where competition = ? and tour = ? and date = ?"
				+ " and status = ? and homeTeam = ? and awayTeam = ? and scoreHomeTeam = ?"
				+ " and scoreAwayTeam = ?";
		
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		
		st.setString(1, rencontre.getCompetition());
		st.setString(2, rencontre.getTour());
		st.setDate(3, rencontre.getDate());
		st.setString(4, rencontre.getStatus());
		st.setString(5, rencontre.getHomeTeam());
		st.setString(6, rencontre.getAwayTeam());
		
		if(rencontre.getStatus().equals("FINISHED")) {
			st.setInt(7, rencontre.getScoreHomeTeam().intValue());
			st.setInt(8, rencontre.getScoreAwayTeam().intValue());
		} else {
			st.setInt(7, 0);
			st.setInt(8, 0);
		}
		
		Rencontre rencontreFromTable;
		int compteur = 0;
		// executer la requête
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			rencontreFromTable = new Rencontre(rs.getInt("id"),rs.getString("competition"),rs.getString("tour"),
					rs.getDate("date"),rs.getString("status"),rs.getString("homeTeam"),rs.getString("awayTeam")
					,rs.getInt("scoreHomeTeam"),rs.getInt("scoreAwayTeam"));
			
			if(rencontreFromTable.equals(rencontre)) 
				compteur += 1;
		}
		
		if (compteur == 1) return true;
		
		return false;
		
	}
	
}
