package com.example.user.rafiki.ItemData;

public class Fiche {

    private int _id;
    private String email;
    private String taille;
    private String num_scret;
    private String adresse;
    private String code_postal;
    private String ville;
    private String sang;

    public Fiche(String email, String taille, String num_scret, String adresse, String code_postal, String ville, String sang) {
        this.email = email;
        this.taille = taille;
        this.num_scret = num_scret;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.ville = ville;
        this.sang = sang;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getNum_scret() {
        return num_scret;
    }

    public void setNum_scret(String num_scret) {
        this.num_scret = num_scret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSang() {
        return sang;
    }

    public void setSang(String sang) {
        this.sang = sang;
    }
}
