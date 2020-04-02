package modele;

import java.sql.Date;

public class Commentaire {
	
	int id;
	String contenu;
	Date date;
	//Rencontre rencontre;

	public Commentaire() {

	}

	public Commentaire(int id, String contenu, Date date) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
