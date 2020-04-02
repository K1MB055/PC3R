package modele;

import java.util.ArrayList;

public class Utilisateur {
	
	int id;
	String nom;
	String prenom;
	String email;
	String mdp;
	ArrayList<Commentaire> commentaires;

	public Utilisateur (){
	}
	
	public Utilisateur(int id, String nom, String prenom, String email, String mdp,
			ArrayList<Commentaire> commentaires) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.commentaires = commentaires;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public ArrayList<Commentaire> getCommentaires() {
		return commentaires;
	}
	
	public void setCommentaires(ArrayList<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
}
