package beans;

public class Utilisateur {
	private int Id_user;
	private String Nom_user;
	private String prenom_user;
	private String pass_user;
	private int type_user;
	private String email_user;
	private String image_user;
	
	 public Utilisateur() {
	
	}

	public int getId_user() {
		return Id_user;
	}
	public void setId_user(int id_user) {
		Id_user = id_user;
	}
	public String getNom_user() {
		return Nom_user;
	}
	public void setNom_user(String nom_user) {
		Nom_user = nom_user;
	}
	public String getPrenom_user() {
		return prenom_user;
	}
	public void setPrenom_user(String prenom_user) {
		this.prenom_user = prenom_user;
	}
	public String getPass_user() {
		return pass_user;
	}
	public void setPass_user(String pass_user) {
		this.pass_user = pass_user;
	}
	public int getType_user() {
		return type_user;
	}
	public void setType_user(int type_user) {
		this.type_user = type_user;
	}
	public String getEmail_user() {
		return email_user;
	}
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
	public String getImage_user() {
		return image_user;
	}
	public void setImage_user(String image_user) {
		this.image_user = image_user;
	}
	
	
}
