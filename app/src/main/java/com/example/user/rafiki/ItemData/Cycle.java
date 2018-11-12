package com.example.user.rafiki.ItemData;

public class Cycle {
    public int _id;
    private String fuldate_cycle;
    private String date;
    private String time;
    private double frequenceC;
    public double poumon;
    private double tempirateur;
    private double nb_pas;
    private double calorie;
    private int cycle;

    public Cycle(String fuldate_cycle, String date, String time, double frequenceC, double poumon, double tempirateur, double nb_pas, double calorie, int cycle) {
        this.fuldate_cycle = fuldate_cycle;
        this.date = date;
        this.time = time;
        this.frequenceC = frequenceC;
        this.poumon = poumon;
        this.tempirateur = tempirateur;
        this.nb_pas = nb_pas;
        this.calorie = calorie;
        this.cycle = cycle;
    }

    public Cycle(double frequenceC, double poumon, double tempirateur) {
        this.frequenceC = frequenceC;
        this.poumon = poumon;
        this.tempirateur = tempirateur;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFuldate_cycle() {
        return fuldate_cycle;
    }

    public void setFuldate_cycle(String fuldate_cycle) {
        this.fuldate_cycle = fuldate_cycle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getFrequenceC() {
        return frequenceC;
    }

    public void setFrequenceC(double frequenceC) {
        this.frequenceC = frequenceC;
    }

    public double getPoumon() {
        return poumon;
    }

    public void setPoumon(double poumon) {
        this.poumon = poumon;
    }

    public double getTempirateur() {
        return tempirateur;
    }

    public void setTempirateur(double tempirateur) {
        this.tempirateur = tempirateur;
    }

    public double getNb_pas() {
        return nb_pas;
    }

    public void setNb_pas(double nb_pas) {
        this.nb_pas = nb_pas;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}
