package com.example.user.rafiki.ItemData;

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
    private String code;
    private String sexe;
    private String email;
    private String password;
    private String poid;
    private byte[] image;


    public clients(String nom, String prenom, String age, String payer, String mobile,String code, String sexe, String email,String poid, String password ) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.payer = payer;
        this.mobile = mobile;
        this.code = code;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
        this.poid = poid;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPoid() {
        return poid;
    }

    public void setPoid(String poid) {
        this.poid = poid;
    }
}
