package com.example.user.rafiki;

/**
 * Created by ASUS on 20/03/2018.
 */

public class clients {

    private int _id;
    private String nom;
    private String prenom;
    private String age;
    private String payer;
    private String mobile;
    private String sexe;
    private String email;
    private String password;

    public clients() {
    }

    public clients(String nom, String prenom, String age, String payer, String mobile, String sexe, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.payer = payer;
        this.mobile = mobile;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
