package com.example.user.rafiki;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 30/03/2018.
 */

public class UserDataSource {

    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private final String TABLE_NAME = "clients";

    public UserDataSource(MySQLiteOpenHelper helper) {
        this.helper = helper;
        db = this.helper.getWritableDatabase();
    }

    public long addClient(clients clt) {

        ContentValues values = new ContentValues();
        values.put("nom", clt.getNom());
        values.put("prenom", clt.getPrenom());
        values.put("age", clt.getAge());
        values.put("payer", clt.getPayer());
        values.put("mobile", clt.getMobile());
        values.put("sexe", clt.getSexe());
        values.put("email", clt.getEmail());
        values.put("password", clt.getPassword());
        long id = db.insert(TABLE_NAME, null, values);
        return id;
    }

    public void removetable() {

        db.delete(TABLE_NAME, null, null);
    }

    public void removeClientById(int id) {

        db.delete(TABLE_NAME, " _id=?", new String[]{String.valueOf(id)});
    }

    public void removeClient(clients clt) {

        removeClientById(clt.get_id());
    }

    public void updateClient(int id, clients clt) {

    }

    public List getAllClient() {

        ArrayList<clients> list = new ArrayList<clients>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"_id", "nom", "prenom", "age", "payer", "mobile", "sexe", "email", "password"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String age = cursor.getString(3);
            String payer = cursor.getString(4);
            String mobile = cursor.getString(5);
            String sexe = cursor.getString(6);
            String email = cursor.getString(7);
            String password = cursor.getString(8);

            clients clt = new clients(nom, prenom, age, payer, mobile, sexe, email, password);
            clt.set_id(id);
            list.add(clt);
            cursor.moveToNext();
        }
        return list;
    }

    public boolean verifEmail(String email) {

        Cursor cursor = db.query(TABLE_NAME,new String[]{"email"},"email=?",new String[]{email},
                null,null,null,null);
        int cursorcount=cursor.getCount();
        if(cursorcount>0) {
            return true;
        }
        return false;
    }
    public boolean verifUser(String email,String password) {

        Cursor cursor = db.query(TABLE_NAME,new String[]{"email,password"},"email=? AND password=?",new String[]{email,password},
                null,null,null,null);
        int cursorcount=cursor.getCount();
        if(cursorcount>0) {
            return true;
        }
        return false;
    }
    public String getPassword(String email) {

        Cursor cursor = db.query(TABLE_NAME,new String[]{"password"},"email=?",new String[]{email},
                null,null,null,null);
        int cursorcount=cursor.getCount();
        if(cursorcount>0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return "";
    }
    public String getNom(String email) {

        Cursor cursor = db.query(TABLE_NAME,new String[]{"nom","prenom"},"email=?",new String[]{email},
                null,null,null,null);
        int cursorcount=cursor.getCount();
        if(cursorcount>0) {
            cursor.moveToFirst();
            String nom= cursor.getString(0)+" "+ cursor.getString(1);
            return nom;
        }
        return "";
    }
}