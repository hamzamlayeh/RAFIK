package beans;

public class rapport {
	private int id_rapport;
	private int Id_user;
	private String titre_rapport;
	private String nom_etudi;
	private String prenom_etudiant;
	private String rapport;
	private String annee_rapport;
	private String branche_etudes;
	private String mention_rapport;
	private String description;
	
		public rapport(){
			
		}
	public int getId_rapport() {
		return id_rapport;
	}
	public void setId_rapport(int id_rapport) {
		this.id_rapport = id_rapport;
	}
	public int getId_user() {
		return Id_user;
	}
	public void setId_user(int id_user) {
		Id_user = id_user;
	}
	public String getTitre_rapport() {
		return titre_rapport;
	}
	public void setTitre_rapport(String titre_rapport) {
		this.titre_rapport = titre_rapport;
	}
	public String getNom_etudi() {
		return nom_etudi;
	}
	public void setNom_etudi(String nom_etudi) {
		this.nom_etudi = nom_etudi;
	}
	public String getPrenom_etudiant() {
		return prenom_etudiant;
	}
	public void setPrenom_etudiant(String prenom_etudiant) {
		this.prenom_etudiant = prenom_etudiant;
	}
	public String getRapport() {
		return rapport;
	}
	public void setRapport(String rapport) {
		this.rapport = rapport;
	}
	public String getAnnee_rapport() {
		return annee_rapport;
	}
	public void setAnnee_rapport(String annee_rapport) {
		this.annee_rapport = annee_rapport;
	}
	public String getBranche_etudes() {
		return branche_etudes;
	}
	public void setBranche_etudes(String branche_etudes) {
		this.branche_etudes = branche_etudes;
	}
	public String getMention_rapport() {
		return mention_rapport;
	}
	public void setMention_rapport(String mention_rapport) {
		this.mention_rapport = mention_rapport;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "rapport [id_rapport=" + id_rapport + ", Id_user=" + Id_user
				+ ", titre_rapport=" + titre_rapport + ", nom_etudi="
				+ nom_etudi + ", prenom_etudiant=" + prenom_etudiant
				+ ", rapport=" + rapport + ", annee_rapport=" + annee_rapport
				+ ", branche_etudes=" + branche_etudes + ", mention_rapport="
				+ mention_rapport + ", description=" + description + "]";
	}

}
