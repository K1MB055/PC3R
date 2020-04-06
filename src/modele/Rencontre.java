package modele;

import java.sql.Date;

public class Rencontre {

	int id;
	String competition;
	String tour; // journée pour le championnat et tour pour compet comme la ldc
	Date date;
	String status; // Finished etc ...
	String homeTeam;
	String awayTeam;
	int ScoreHomeTeam;
	int ScoreAwayTeam;
	// ArrayList<String> arbitres;

	public Rencontre() {

	}

	public Rencontre(int id, String competition, String tour, Date date, String status, String homeTeam,
			String awayTeam, int scoreHomeTeam, int scoreAwayTeam) {
		this.id = id;
		this.competition = competition;
		this.tour = tour;
		this.date = date;
		this.status = status;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		ScoreHomeTeam = scoreHomeTeam;
		ScoreAwayTeam = scoreAwayTeam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public String getTour() {
		return tour;
	}

	public void setTour(String tour) {
		this.tour = tour;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getScoreHomeTeam() {
		return ScoreHomeTeam;
	}

	public void setScoreHomeTeam(int scoreHomeTeam) {
		ScoreHomeTeam = scoreHomeTeam;
	}

	public int getScoreAwayTeam() {
		return ScoreAwayTeam;
	}

	public void setScoreAwayTeam(int scoreAwayTeam) {
		ScoreAwayTeam = scoreAwayTeam;
	}

	@Override
	public String toString() {
		return "Rencontre [id=" + id + ", competition=" + competition + ", tour=" + tour + ", date=" + date
				+ ", status=" + status + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", ScoreHomeTeam="
				+ ScoreHomeTeam + ", ScoreAwayTeam=" + ScoreAwayTeam + "]";
	}

}
