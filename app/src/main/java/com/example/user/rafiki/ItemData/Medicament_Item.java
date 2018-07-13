package com.example.user.rafiki.ItemData;

public class Medicament_Item {

    private int _id;
    private String nom_medica;
    private String nb_matin;
    private String nb_midi;
    private String nb_soire;
    private String date_debut;
    private String date_fin;
    private String heure_matin;
    private String heure_midi;
    private String heur_soire;
    private int color_lu;
    private int color_ma;
    private int color_me;
    private int color_ju;
    private int color_ve;
    private int color_sa;
    private int color_di;

    public Medicament_Item(String nom_medica, String nb_matin, String nb_midi, String nb_soire, String date_debut, String date_fin, String heure_matin, String heure_midi, String heur_soire, int color_lu, int color_ma, int color_me, int color_ju, int color_ve, int color_sa, int color_di) {
        this.nom_medica = nom_medica;
        this.nb_matin = nb_matin;
        this.nb_midi = nb_midi;
        this.nb_soire = nb_soire;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.heure_matin = heure_matin;
        this.heure_midi = heure_midi;
        this.heur_soire = heur_soire;
        this.color_lu = color_lu;
        this.color_ma = color_ma;
        this.color_me = color_me;
        this.color_ju = color_ju;
        this.color_ve = color_ve;
        this.color_sa = color_sa;
        this.color_di = color_di;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNom_medica() {
        return nom_medica;
    }

    public void setNom_medica(String nom_medica) {
        this.nom_medica = nom_medica;
    }

    public String getNb_matin() {
        return nb_matin;
    }

    public void setNb_matin(String nb_matin) {
        this.nb_matin = nb_matin;
    }

    public String getNb_midi() {
        return nb_midi;
    }

    public void setNb_midi(String nb_midi) {
        this.nb_midi = nb_midi;
    }

    public String getNb_soire() {
        return nb_soire;
    }

    public void setNb_soire(String nb_soire) {
        this.nb_soire = nb_soire;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getHeure_matin() {
        return heure_matin;
    }

    public void setHeure_matin(String heure_matin) {
        this.heure_matin = heure_matin;
    }

    public String getHeure_midi() {
        return heure_midi;
    }

    public void setHeure_midi(String heure_midi) {
        this.heure_midi = heure_midi;
    }

    public String getHeur_soire() {
        return heur_soire;
    }

    public void setHeur_soire(String heur_soire) {
        this.heur_soire = heur_soire;
    }

    public int getColor_lu() {
        return color_lu;
    }

    public void setColor_lu(int color_lu) {
        this.color_lu = color_lu;
    }

    public int getColor_ma() {
        return color_ma;
    }

    public void setColor_ma(int color_ma) {
        this.color_ma = color_ma;
    }

    public int getColor_me() {
        return color_me;
    }

    public void setColor_me(int color_me) {
        this.color_me = color_me;
    }

    public int getColor_ju() {
        return color_ju;
    }

    public void setColor_ju(int color_ju) {
        this.color_ju = color_ju;
    }

    public int getColor_ve() {
        return color_ve;
    }

    public void setColor_ve(int color_ve) {
        this.color_ve = color_ve;
    }

    public int getColor_sa() {
        return color_sa;
    }

    public void setColor_sa(int color_sa) {
        this.color_sa = color_sa;
    }

    public int getColor_di() {
        return color_di;
    }

    public void setColor_di(int color_di) {
        this.color_di = color_di;
    }

}
