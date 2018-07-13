package com.example.user.rafiki.ItemData;

/**
 * Created by user on 13/07/2018.
 */

public class Allergies_Item {
    private String nom_allergie1;
    private String nom_allergie2;
    private String nom_allergie3;
    private int _id ;
    private int id_allergie ;


    public String getNom_allergie1() {
        return nom_allergie1;
    }

    public void setNom_allergie1(String nom_allergie1) {
        this.nom_allergie1 = nom_allergie1;
    }

    public String getNom_allergie2() {
        return nom_allergie2;
    }

    public void setNom_allergie2(String nom_allergie2) {
        this.nom_allergie2 = nom_allergie2;
    }

    public String getNom_allergie3() {
        return nom_allergie3;
    }

    public void setNom_allergie3(String nom_allergie3) {
        this.nom_allergie3 = nom_allergie3;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId_allergie() {
        return id_allergie;
    }

    public void setId_allergie(int id_allergie) {
        this.id_allergie = id_allergie;
    }

    public Allergies_Item(String nom_allergie1, String nom_allergie2, String nom_allergie3, int _id, int id_allergie) {

        this.nom_allergie1 = nom_allergie1;
        this.nom_allergie2 = nom_allergie2;
        this.nom_allergie3 = nom_allergie3;
        this._id = _id;
        this.id_allergie = id_allergie;
    }



}
