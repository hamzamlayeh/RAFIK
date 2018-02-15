package com.example.user.rafiki;

/**
 * Created by ASUS on 10/02/2018.
 */

public class DataItem {
    String id;
    int img_payer;
    String nom_payer;

    public DataItem(String id, int img_payer, String nom_payer) {
        this.id = id;
        this.img_payer = img_payer;
        this.nom_payer = nom_payer;
    }

    public int getImg_payer() {
        return img_payer;
    }

    public void setImg_payer(int img_payer) {
        this.img_payer = img_payer;
    }

    public String getNom_payer() {
        return nom_payer;
    }

    public void setNom_payer(String nom_payer) {
        this.nom_payer = nom_payer;
    }
}
