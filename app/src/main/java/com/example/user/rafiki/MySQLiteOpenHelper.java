package com.example.user.rafiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 30/03/2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table client
        String sql="CREATE TABLE clients (_id integer PRIMARY KEY autoincrement not null," +
                "nom text not null,prenom text not null,age text not null,payer text not null," +
                "mobile text not null,code text not null,sexe text not null,email text not null," +
                "password text not null,poid text not null ,image text )";
        //Table FicheMedicale
        String sql2="CREATE TABLE ficheMedicale (_id integer PRIMARY KEY autoincrement not null,"+
                "email text not null,taille text not null,num_scret text,adresse text not null,"+
                "code_postal text not null,ville text not null,sang text)";
        //Table Maladie
        String sql3="CREATE TABLE Maladis (_id integer PRIMARY KEY autoincrement not null,"+
                "NomMaladi text)";
        //Table Antecedents
        String sql4="CREATE TABLE Antecedents (_id integer PRIMARY KEY autoincrement not null,"+
                "acte text ,date text)";
        //Table Allergies
        String sql5="CREATE TABLE Allergies (_id integer PRIMARY KEY autoincrement not null,"+
                "id_allergie integer not null ,nom_allergie1 text,nom_allergie2 text,nom_allergie3 text)";
        //Table Medicament
        String sql6="CREATE TABLE Medicament (_id integer PRIMARY KEY autoincrement not null,"+
                "nom_medica text not null ,nb_matin text,nb_midi text,nb_soire text,date_debut text not null,"+
                "date_fin text not null,heure_matin text,heure_midi text,heure_soire text,color_lu integer not null,"+
                "color_ma integer not null,color_me integer not null,color_ju integer not null,"+
                "color_ve integer not null,color_sa integer not null,color_di integer not null)";
        //Table Contacts Parentaux
        String sql7="CREATE TABLE Contacts_Parentaux (_id integer PRIMARY KEY autoincrement not null,"+
                "nom text,prenom text,mobile text,code text,email text)";
        //Table Contacts Medecins
        String sql8="CREATE TABLE Contacts_Medecins (_id integer PRIMARY KEY autoincrement not null,"+
                "nom text,prenom text,mobile text,code text,email text,hopital text)";
        //Table Contacts Urgences
        String sql9="CREATE TABLE Contacts_Urgences (_id integer PRIMARY KEY autoincrement not null,"+
                "nom_asur text,tel_asur text,code_asur text,nom_urg text,tel_urg text,code_urg text)";
        //Table Seuils Biométrique
        String sql10="CREATE TABLE Seuils_Bio (_id integer PRIMARY KEY autoincrement not null,"+
                "FCmarche_M text,FCmarche_X text,FCcourse_M text,FCcourse_X text,FCactivite_M text," +
                "FCactivite_X text,FCsommeil_M text,FCsommeil_X text,FRmarche_M text,FRmarche_X text," +
                "FRcourse_M text,FRcourse_X text,FRactivite_M text,FRactivite_X text,FRsommeil_M text," +
                "FRsommeil_X text,Tmarche_M text,Tmarche_X text,Tcourse_M text,Tcourse_X text,Tactivite_M text," +
                "Tactivite_X text,Tsommeil_M text,Tsommeil_X text)";
        //Table Cycle
        String sql11="CREATE TABLE Cycle (_id integer PRIMARY KEY autoincrement not null,"+
                "fuldate_cycle text,date text,time text,frequenceC real,poumon real,tempirateur real," +
                "nb_pas real,calorie real,cycle integer)";
        // Parametre_Alert
        String sql12="CREATE TABLE Parametre_Alert (_id integer PRIMARY KEY autoincrement not null,"+
                "smsNiveau2 text,smsNiveau3 text,smsNiveau4 text)";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
        db.execSQL(sql7);
        db.execSQL(sql8);
        db.execSQL(sql9);
        db.execSQL(sql10);
        db.execSQL(sql11);
        db.execSQL(sql12);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String delet_sql="DROP Table IF EXISTS clients ";
        String delet_sql2="DROP Table IF EXISTS ficheMedicale ";
        String delet_sql3="DROP Table IF EXISTS Antecedents ";
        String delet_sql4="DROP Table IF EXISTS Maladis ";
        String delet_sql5="DROP Table IF EXISTS Allergies ";
        String delet_sql6="DROP Table IF EXISTS Medicament ";
        String delet_sql7="DROP Table IF EXISTS Contacts_Parentaux ";
        String delet_sql8="DROP Table IF EXISTS Contacts_Medecins ";
        String delet_sql9="DROP Table IF EXISTS Contacts_Urgences ";
        String delet_sql10="DROP Table IF EXISTS Seuils_Bio ";
        String delet_sql11="DROP Table IF EXISTS Cycle ";
       String delet_sql12="DROP Table IF EXISTS Parametre_Alert ";

        sqLiteDatabase.execSQL(delet_sql);
        sqLiteDatabase.execSQL(delet_sql2);
        sqLiteDatabase.execSQL(delet_sql3);
        sqLiteDatabase.execSQL(delet_sql4);
        sqLiteDatabase.execSQL(delet_sql5);
        sqLiteDatabase.execSQL(delet_sql6);
        sqLiteDatabase.execSQL(delet_sql7);
        sqLiteDatabase.execSQL(delet_sql8);
        sqLiteDatabase.execSQL(delet_sql9);
        sqLiteDatabase.execSQL(delet_sql10);
        sqLiteDatabase.execSQL(delet_sql11);
      sqLiteDatabase.execSQL(delet_sql12);

        onCreate(sqLiteDatabase);
    }
}
