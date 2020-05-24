package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class TraitementRencontre {
	
	/**
	 * Ajoute une rencontre à la base
	 * @return False si erreur
	 */
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
	
	/**
	 * Supprime une rencontre de la base
	 * @return False si erreur
	 */
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
	
	/**
	 * Modifie une rencontre dans la base
	 * @return False si erreur
	 */
	public static boolean modifierRencontre(Rencontre r) throws ClassNotFoundException, SQLException 
	{
		Connection cn = null;
		PreparedStatement st = null;
		
		String sql = "UPDATE rencontre SET status = ?, scoreHomeTeam = ?, scoreAwayTeam = ?"
				+ " WHERE competition = ? and tour = ? and date = ?"
				+ " and homeTeam = ? and awayTeam = ?";
		
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		
		st.setString(1, r.getStatus());
		st.setInt(2, r.getScoreHomeTeam());
		st.setInt(3, r.getScoreAwayTeam());
		st.setString(4, r.getCompetition());
		st.setString(5, r.getTour());
		st.setDate(6, r.getDate());
		st.setString(7, r.getHomeTeam());
		st.setString(8, r.getAwayTeam());
		
		int count = st.executeUpdate();
		return (count > 0);
	}
	
	/**
	 * Recherche toutes les rencontres d'une compétition
	 * @return la liste des compétitions
	 */
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
		// executer la requÃªte
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			rencontre = new Rencontre(rs.getInt("id"),rs.getString("competition"),rs.getString("tour"),
					rs.getDate("date"),rs.getString("status"),rs.getString("homeTeam"),rs.getString("awayTeam")
					,rs.getInt("scoreHomeTeam"),rs.getInt("scoreAwayTeam"));
			rencontres.add(rencontre);
		}
		
		return rencontres;
	}
	
	/**
	 * Détermine si une rencontre est déjà dans la bas où non
	 * @return True si elle est déjà dans la base, False sinon
	 */
	public static EtatRencontre isRencontreAlreadyIn(Rencontre rencontre) 
			throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement st = null;
		
		String sql = "SELECT * FROM rencontre where competition = ? and tour = ? and date = ?"
				+ " and homeTeam = ? and awayTeam = ?";
		
		cn = ConnectionLV.getConnection();
		st = cn.prepareStatement(sql);
		
		st.setString(1, rencontre.getCompetition());
		st.setString(2, rencontre.getTour());
		st.setDate(3, rencontre.getDate());
		st.setString(4, rencontre.getHomeTeam());
		st.setString(5, rencontre.getAwayTeam());
		
		
		Rencontre rencontreFromTable;
		int compteur = 0;
		// executer la requÃªte
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			rencontreFromTable = new Rencontre(rs.getInt("id"),rs.getString("competition"),rs.getString("tour"),
					rs.getDate("date"),rs.getString("status"),rs.getString("homeTeam"),rs.getString("awayTeam")
					,rs.getInt("scoreHomeTeam"),rs.getInt("scoreAwayTeam"));
			
			if(rencontreFromTable.equals(rencontre)) {
				compteur += 1;
				if(needUpdate(rencontreFromTable, rencontre))
					return EtatRencontre.NeedUpdate;
			}
				
					
		}
		
		if (compteur == 1) return EtatRencontre.AlreadyIn;
		
		return EtatRencontre.Missing;
		
	}
	
	/**
	 * Détermine si une rencontre a besoin d'une mise à jour
	 * Dans le cas où son status a changé ou si elle est en cours
	 * @return True si une mise à jour est nécessaire
	 */
	public static boolean needUpdate(Rencontre oldRencontre, Rencontre newRencontre) {
		
		if (newRencontre.getStatus().equals("IN_PLAY") || newRencontre.getStatus().equals("PAUSED"))
			return true;
		
		return !newRencontre.getStatus().equals(oldRencontre.getStatus());
		
	}
	
}
