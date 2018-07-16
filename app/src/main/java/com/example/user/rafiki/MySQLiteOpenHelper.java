package com.example.user.rafiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 30/03/2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table client
        String sql="CREATE TABLE clients (_id integer PRIMARY KEY autoincrement not null," +
                "nom text not null,prenom text not null,age text not null,payer text not null," +
                "mobile text not null,code text not null,sexe text not null,email text not null,password text not null,image text )";
        //Table FicheMedicale
        String sql2="CREATE TABLE ficheMedicale (_id integer PRIMARY KEY autoincrement not null,"+
                "email text not null,poid text not null,taille text not null,num_scret text,adresse text not null,"+
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
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String delet_sql="DROP Table clients ";
        String delet_sql2="DROP Table ficheMedicale ";
        String delet_sql3="DROP Table Antecedents ";
        String delet_sql4="DROP Table Maladis ";
        String delet_sql5="DROP Table Allergies ";
        String delet_sql6="DROP Table Medicament ";

        sqLiteDatabase.execSQL(delet_sql);
        sqLiteDatabase.execSQL(delet_sql2);
        sqLiteDatabase.execSQL(delet_sql3);
        sqLiteDatabase.execSQL(delet_sql4);
        sqLiteDatabase.execSQL(delet_sql5);
        sqLiteDatabase.execSQL(delet_sql6);

        onCreate(sqLiteDatabase);
    }
}
