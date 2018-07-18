package com.example.user.rafiki.ItemData;

public class Contacts_Urgences {

    private int _id;
    private String nom_asur;
    private String tel_asur;
    private String code_asur;
    private String nom_urg;
    private String tel_urg;
    private String code_urg;

    public Contacts_Urgences(String nom_asur, String tel_asur, String code_asur, String nom_urg, String tel_urg, String code_urg) {
        this.nom_asur = nom_asur;
        this.tel_asur = tel_asur;
        this.code_asur = code_asur;
        this.nom_urg = nom_urg;
        this.tel_urg = tel_urg;
        this.code_urg = code_urg;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNom_asur() {
        return nom_asur;
    }

    public void setNom_asur(String nom_asur) {
        this.nom_asur = nom_asur;
    }

    public String getTel_asur() {
        return tel_asur;
    }

    public void setTel_asur(String tel_asur) {
        this.tel_asur = tel_asur;
    }

    public String getCode_asur() {
        return code_asur;
    }

    public void setCode_asur(String code_asur) {
        this.code_asur = code_asur;
    }

    public String getNom_urg() {
        return nom_urg;
    }

    public void setNom_urg(String nom_urg) {
        this.nom_urg = nom_urg;
    }

    public String getTel_urg() {
        return tel_urg;
    }

    public void setTel_urg(String tel_urg) {
        this.tel_urg = tel_urg;
    }

    public String getCode_urg() {
        return code_urg;
    }

    public void setCode_urg(String code_urg) {
        this.code_urg = code_urg;
    }
}
