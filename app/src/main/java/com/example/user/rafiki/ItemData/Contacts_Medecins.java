package com.example.user.rafiki.ItemData;

public class Contacts_Medecins {

    private int _id;
    private String nom;
    private String prenom;
    private String mobile;
    private String code;
    private String email;
    private String hopital;

    public Contacts_Medecins(String nom, String prenom, String mobile, String code, String email, String hopital) {
        this.nom = nom;
        this.prenom = prenom;
        this.mobile = mobile;
        this.code = code;
        this.email = email;
        this.hopital = hopital;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHopital() {
        return hopital;
    }

    public void setHopital(String hopital) {
        this.hopital = hopital;
    }
}
