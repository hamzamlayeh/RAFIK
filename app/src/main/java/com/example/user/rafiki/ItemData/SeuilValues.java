package com.example.user.rafiki.ItemData;

public class SeuilValues {

    private int _id;
    private String FCmarche_M;
    private String FCmarche_X;
    private String FCcourse_M;
    private String FCcourse_X;
    private String FCactivite_M;
    private String FCactivite_X;
    private String FCsommeil_M;
    private String FCsommeil_X;

    private String FRmarche_M;
    private String FRmarche_X;
    private String FRcourse_M;
    private String FRcourse_X;
    private String FRactivite_M;
    private String FRactivite_X;
    private String FRsommeil_M;
    private String FRsommeil_X;

    private String Tmarche_M;
    private String Tmarche_X;
    private String Tcourse_M;
    private String Tcourse_X;
    private String Tactivite_M;
    private String Tactivite_X;
    private String Tsommeil_M;
    private String Tsommeil_X;

    public SeuilValues() {
    }

    public SeuilValues(String FCmarche_X, String FCcourse_X, String FCactivite_X, String FCsommeil_X) {
        this.FCmarche_M = "60";
        this.FCmarche_X = FCmarche_X;
        this.FCcourse_M = "60";
        this.FCcourse_X = FCcourse_X;
        this.FCactivite_M = "60";
        this.FCactivite_X = FCactivite_X;
        this.FCsommeil_M = "60";
        this.FCsommeil_X = FCsommeil_X;

        this.FRmarche_M="12";
        this.FRmarche_X="20";
        this.FRcourse_M="12";
        this.FRcourse_X="50";
        this.FRactivite_M="12";
        this.FRactivite_X="20";
        this.FRsommeil_M="8";
        this.FRsommeil_X="20";

        this.Tmarche_M="20";
        this.Tmarche_X="42";
        this.Tcourse_M="20";
        this.Tcourse_X="42";
        this.Tactivite_M="20";
        this.Tactivite_X="42";
        this.Tsommeil_M="20";
        this.Tsommeil_X="42";
    }

    public SeuilValues(String FCmarche_M, String FCmarche_X, String FCcourse_M, String FCcourse_X, String FCactivite_M, String FCactivite_X, String FCsommeil_M, String FCsommeil_X, String FRmarche_M, String FRmarche_X, String FRcourse_M, String FRcourse_X, String FRactivite_M, String FRactivite_X, String FRsommeil_M, String FRsommeil_X, String tmarche_M, String tmarche_X, String tcourse_M, String tcourse_X, String tactivite_M, String tactivite_X, String tsommeil_M, String tsommeil_X) {
        this.FCmarche_M = FCmarche_M;
        this.FCmarche_X = FCmarche_X;
        this.FCcourse_M = FCcourse_M;
        this.FCcourse_X = FCcourse_X;
        this.FCactivite_M = FCactivite_M;
        this.FCactivite_X = FCactivite_X;
        this.FCsommeil_M = FCsommeil_M;
        this.FCsommeil_X = FCsommeil_X;
        this.FRmarche_M = FRmarche_M;
        this.FRmarche_X = FRmarche_X;
        this.FRcourse_M = FRcourse_M;
        this.FRcourse_X = FRcourse_X;
        this.FRactivite_M = FRactivite_M;
        this.FRactivite_X = FRactivite_X;
        this.FRsommeil_M = FRsommeil_M;
        this.FRsommeil_X = FRsommeil_X;
        Tmarche_M = tmarche_M;
        Tmarche_X = tmarche_X;
        Tcourse_M = tcourse_M;
        Tcourse_X = tcourse_X;
        Tactivite_M = tactivite_M;
        Tactivite_X = tactivite_X;
        Tsommeil_M = tsommeil_M;
        Tsommeil_X = tsommeil_X;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFCmarche_M() {
        return FCmarche_M;
    }

    public void setFCmarche_M(String FCmarche_M) {
        this.FCmarche_M = FCmarche_M;
    }

    public String getFCmarche_X() {
        return FCmarche_X;
    }

    public void setFCmarche_X(String FCmarche_X) {
        this.FCmarche_X = FCmarche_X;
    }

    public String getFCcourse_M() {
        return FCcourse_M;
    }

    public void setFCcourse_M(String FCcourse_M) {
        this.FCcourse_M = FCcourse_M;
    }

    public String getFCcourse_X() {
        return FCcourse_X;
    }

    public void setFCcourse_X(String FCcourse_X) {
        this.FCcourse_X = FCcourse_X;
    }

    public String getFCactivite_M() {
        return FCactivite_M;
    }

    public void setFCactivite_M(String FCactivite_M) {
        this.FCactivite_M = FCactivite_M;
    }

    public String getFCactivite_X() {
        return FCactivite_X;
    }

    public void setFCactivite_X(String FCactivite_X) {
        this.FCactivite_X = FCactivite_X;
    }

    public String getFCsommeil_M() {
        return FCsommeil_M;
    }

    public void setFCsommeil_M(String FCsommeil_M) {
        this.FCsommeil_M = FCsommeil_M;
    }

    public String getFCsommeil_X() {
        return FCsommeil_X;
    }

    public void setFCsommeil_X(String FCsommeil_X) {
        this.FCsommeil_X = FCsommeil_X;
    }

    public String getFRmarche_M() {
        return FRmarche_M;
    }

    public void setFRmarche_M(String FRmarche_M) {
        this.FRmarche_M = FRmarche_M;
    }

    public String getFRmarche_X() {
        return FRmarche_X;
    }

    public void setFRmarche_X(String FRmarche_X) {
        this.FRmarche_X = FRmarche_X;
    }

    public String getFRcourse_M() {
        return FRcourse_M;
    }

    public void setFRcourse_M(String FRcourse_M) {
        this.FRcourse_M = FRcourse_M;
    }

    public String getFRcourse_X() {
        return FRcourse_X;
    }

    public void setFRcourse_X(String FRcourse_X) {
        this.FRcourse_X = FRcourse_X;
    }

    public String getFRactivite_M() {
        return FRactivite_M;
    }

    public void setFRactivite_M(String FRactivite_M) {
        this.FRactivite_M = FRactivite_M;
    }

    public String getFRactivite_X() {
        return FRactivite_X;
    }

    public void setFRactivite_X(String FRactivite_X) {
        this.FRactivite_X = FRactivite_X;
    }

    public String getFRsommeil_M() {
        return FRsommeil_M;
    }

    public void setFRsommeil_M(String FRsommeil_M) {
        this.FRsommeil_M = FRsommeil_M;
    }

    public String getFRsommeil_X() {
        return FRsommeil_X;
    }

    public void setFRsommeil_X(String FRsommeil_X) {
        this.FRsommeil_X = FRsommeil_X;
    }

    public String getTmarche_M() {
        return Tmarche_M;
    }

    public void setTmarche_M(String tmarche_M) {
        Tmarche_M = tmarche_M;
    }

    public String getTmarche_X() {
        return Tmarche_X;
    }

    public void setTmarche_X(String tmarche_X) {
        Tmarche_X = tmarche_X;
    }

    public String getTcourse_M() {
        return Tcourse_M;
    }

    public void setTcourse_M(String tcourse_M) {
        Tcourse_M = tcourse_M;
    }

    public String getTcourse_X() {
        return Tcourse_X;
    }

    public void setTcourse_X(String tcourse_X) {
        Tcourse_X = tcourse_X;
    }

    public String getTactivite_M() {
        return Tactivite_M;
    }

    public void setTactivite_M(String tactivite_M) {
        Tactivite_M = tactivite_M;
    }

    public String getTactivite_X() {
        return Tactivite_X;
    }

    public void setTactivite_X(String tactivite_X) {
        Tactivite_X = tactivite_X;
    }

    public String getTsommeil_M() {
        return Tsommeil_M;
    }

    public void setTsommeil_M(String tsommeil_M) {
        Tsommeil_M = tsommeil_M;
    }

    public String getTsommeil_X() {
        return Tsommeil_X;
    }

    public void setTsommeil_X(String tsommeil_X) {
        Tsommeil_X = tsommeil_X;
    }
}

