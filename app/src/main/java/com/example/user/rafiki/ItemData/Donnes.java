package com.example.user.rafiki.ItemData;

public class Donnes {

    private String coeur;
    private String poumon;
    private String tempirateur;

    public Donnes(String coeur, String poumon, String tempirateur) {
        this.coeur = coeur;
        this.poumon = poumon;
        this.tempirateur = tempirateur;
    }

    public String getCoeur() {
        return coeur;
    }

    public void setCoeur(String coeur) {
        this.coeur = coeur;
    }

    public String getPoumon() {
        return poumon;
    }

    public void setPoumon(String poumon) {
        this.poumon = poumon;
    }

    public String getTempirateur() {
        return tempirateur;
    }

    public void setTempirateur(String tempirateur) {
        this.tempirateur = tempirateur;
    }
}
