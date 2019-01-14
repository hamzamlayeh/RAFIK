package com.example.user.rafiki.ItemData;

public class Alerts {
    private int _id;
    private String SMSNiveau2;
    private String SMSNiveau3;
    private String SMSNiveau4;

    public Alerts() {
        this.SMSNiveau2 = "SMS Niveaux 2";
        this.SMSNiveau3 = "SMS Niveaux 3";
        this.SMSNiveau4 = "SMS Niveaux 4";
    }

    public Alerts(String SMSNiveau2, String SMSNiveau3, String SMSNiveau4) {
        this.SMSNiveau2 = SMSNiveau2;
        this.SMSNiveau3 = SMSNiveau3;
        this.SMSNiveau4 = SMSNiveau4;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSMSNiveau2() {
        return SMSNiveau2;
    }

    public void setSMSNiveau2(String SMSNiveau2) {
        this.SMSNiveau2 = SMSNiveau2;
    }

    public String getSMSNiveau3() {
        return SMSNiveau3;
    }

    public void setSMSNiveau3(String SMSNiveau3) {
        this.SMSNiveau3 = SMSNiveau3;
    }

    public String getSMSNiveau4() {
        return SMSNiveau4;
    }

    public void setSMSNiveau4(String SMSNiveau4) {
        this.SMSNiveau4 = SMSNiveau4;
    }
}
