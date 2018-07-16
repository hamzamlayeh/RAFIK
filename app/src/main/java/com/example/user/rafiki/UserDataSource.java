package com.example.user.rafiki;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.rafiki.ItemData.Allergies_Item;
import com.example.user.rafiki.ItemData.Antecedents_Item;
import com.example.user.rafiki.ItemData.Fiche;
import com.example.user.rafiki.ItemData.Medicament_Item;
import com.example.user.rafiki.ItemData.clients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 30/03/2018.
 */

public class UserDataSource {

    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private final String TABLE_NAME = "clients";
    private final String TABLE_NAME2 = "ficheMedicale";
    private final String TABLE_NAME3 = "Maladis";
    private final String TABLE_NAME4 = "Antecedents";
    private final String TABLE_NAME5 = "Allergies";
    private final String TABLE_NAME6 = "Medicament";

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
        values.put("code", clt.getCode());
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

    public boolean updateClient(int id, clients clt) {
        ContentValues values = new ContentValues();
        values.put("nom", clt.getNom());
        values.put("prenom", clt.getPrenom());
        values.put("age", clt.getAge());
        values.put("payer", clt.getPayer());
        values.put("mobile", clt.getMobile());
        values.put("code", clt.getCode());
        values.put("sexe", clt.getSexe());
        values.put("email", clt.getEmail());
        values.put("password", clt.getPassword());
        return db.update(TABLE_NAME, values, "_id=" + id, null) > 0;
    }


    public List getAllClient() {

        ArrayList<clients> list = new ArrayList<clients>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"_id", "nom", "prenom", "age", "payer", "mobile", "code", "sexe", "email", "password"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String age = cursor.getString(3);
            String payer = cursor.getString(4);
            String mobile = cursor.getString(5);
            String code = cursor.getString(6);
            String sexe = cursor.getString(7);
            String email = cursor.getString(8);
            String password = cursor.getString(9);

            clients clt = new clients(nom, prenom, age, payer, mobile, code, sexe, email, password);
            clt.set_id(id);
            list.add(clt);
            cursor.moveToNext();
        }
        return list;
    }

    public boolean verifEmail(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"email"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            return true;
        }
        return false;
    }

    public boolean verifUser(String email, String password) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"email,password"}, "email=? AND password=?", new String[]{email, password},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            return true;
        }
        return false;
    }

    public String getPassword(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"password"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return "";
    }

    public String getNom(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"nom", "prenom"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            String nom = cursor.getString(0) + " " + cursor.getString(1);
            return nom;
        }
        return "";
    }

    public boolean UpdateImg(String img, String email) {

        ContentValues values = new ContentValues();
        values.put("image", img);
        int i = db.update(TABLE_NAME, values, "email=?", new String[]{email});
        return i > 0;
    }

    public String getImg(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"image"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return null;
    }

    public long addFiche(Fiche fich) {

        ContentValues values = new ContentValues();
        values.put("email", fich.getEmail());
        values.put("poid", fich.getPoid());
        values.put("taille", fich.getTaille());
        values.put("num_scret", fich.getNum_scret());
        values.put("adresse", fich.getAdresse());
        values.put("code_postal", fich.getCode_postal());
        values.put("ville", fich.getVille());
        values.put("sang", fich.getSang());
        long id = db.insert(TABLE_NAME2, null, values);
        return id;
    }

    public List getFiche() {

        ArrayList<Fiche> list = new ArrayList<Fiche>();
        Cursor cursor = db.query(TABLE_NAME2, new String[]{"_id", "email", "poid", "taille", "num_scret", "adresse", "code_postal", "ville", "sang"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String email = cursor.getString(1);
            String poid = cursor.getString(2);
            String taille = cursor.getString(3);
            String num_scret = cursor.getString(4);
            String adresse = cursor.getString(5);
            String code_postal = cursor.getString(6);
            String ville = cursor.getString(7);
            String sang = cursor.getString(8);

            Fiche clt = new Fiche(email, poid, taille, num_scret, adresse, code_postal, ville, sang);
            clt.set_id(id);
            System.out.println(id);
            list.add(clt);
            cursor.moveToNext();
        }
        return list;
    }

    public long UpdateFiche(String email, Fiche fich) {

        ContentValues values = new ContentValues();
        values.put("poid", fich.getPoid());
        values.put("taille", fich.getTaille());
        values.put("num_scret", fich.getNum_scret());
        values.put("adresse", fich.getAdresse());
        values.put("code_postal", fich.getCode_postal());
        values.put("ville", fich.getVille());
        values.put("sang", fich.getSang());
        long i = db.update(TABLE_NAME2, values, "email=?", new String[]{email});
        return i;
    }

    public int getCountMaladi() {
        Cursor cursor = db.query(TABLE_NAME3, null, null, null,
                null, null, null, null);
        return cursor.getCount();
    }

    public long addmaladi(String item1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NomMaladi", item1);

        return db.insert(TABLE_NAME3, null, contentValues);
    }

    public long UpdateMaladi(String item1, int id) {

        ContentValues values = new ContentValues();
        values.put("NomMaladi", item1);
        long i = db.update(TABLE_NAME3, values, "_id= " + id, null);
        return i;
    }

    public List getListMaladi() {
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NAME3, new String[]{"NomMaladi"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {

            list.add(i, cursor.getString(0));
            i++;
            cursor.moveToNext();
        }
        return list;
    }

    //ANTECEDENTS
    public int getCountAntece() {
        Cursor cursor = db.query(TABLE_NAME4, null, null, null,
                null, null, null, null);
        return cursor.getCount();
    }

    public long addAnte(Antecedents_Item itemData) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("acte", itemData.getActe());
        contentValues.put("date", itemData.getDate());

        return db.insert(TABLE_NAME4, null, contentValues);
    }

    public long UpdateAnti(Antecedents_Item itemData, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("acte", itemData.getActe());
        contentValues.put("date", itemData.getDate());

        return (long) db.update(TABLE_NAME4, contentValues, "_id= " + id, null);
    }

    public List getListAnte() {
        ArrayList<Antecedents_Item> list = new ArrayList<Antecedents_Item>();
        Cursor cursor = db.query(TABLE_NAME4, new String[]{"acte,date"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String acte = cursor.getString(0);
            String date = cursor.getString(1);

            list.add(new Antecedents_Item(acte, date));
            cursor.moveToNext();
        }
        return list;
    }

    public long addallergie(int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_allergie", id);

        return db.insert(TABLE_NAME5, null, contentValues);
    }

    public boolean verifId_allergie(int id) {

        Cursor cursor = db.query(TABLE_NAME5, new String[]{"id_allergie"}, "id_allergie= " + id, null,
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            return true;
        }
        return false;
    }

    public long Updateallergie(String M1, String M2, String M3, int id) {

        ContentValues values = new ContentValues();
        values.put("nom_allergie1", M1);
        values.put("nom_allergie2", M2);
        values.put("nom_allergie3", M3);

        long i = db.update(TABLE_NAME5, values, "id_allergie= " + id, null);
        return i;
    }

    public List getListAllergies(int id) {
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NAME5, new String[]{"nom_allergie1,nom_allergie2,nom_allergie3"}, "id_allergie= " + id,
                null, null, null, null, null);
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {

            list.add(i, cursor.getString(0));
            list.add(i, cursor.getString(1));
            list.add(i, cursor.getString(2));
            i++;
            cursor.moveToNext();
        }
        return list;
    }

    //Medicament
    public long addMidica(Medicament_Item medica) {

        ContentValues values = new ContentValues();
        values.put("nom_medica", medica.getNom_medica());
        values.put("nb_matin", medica.getNb_matin());
        values.put("nb_midi", medica.getNb_midi());
        values.put("nb_soire", medica.getNb_soire());
        values.put("date_debut", medica.getDate_debut());
        values.put("date_fin", medica.getDate_fin());
        values.put("heure_matin", medica.getHeure_matin());
        values.put("heure_midi", medica.getHeure_midi());
        values.put("heure_soire", medica.getHeur_soire());
        values.put("color_lu", medica.getColor_lu());
        values.put("color_ma", medica.getColor_ma());
        values.put("color_me", medica.getColor_me());
        values.put("color_ju", medica.getColor_ju());
        values.put("color_ve", medica.getColor_ve());
        values.put("color_sa", medica.getColor_sa());
        values.put("color_di", medica.getColor_di());
        long id = db.insert(TABLE_NAME6, null, values);
        return id;
    }

    public List getMedicament() {

        ArrayList<Medicament_Item> list = new ArrayList<Medicament_Item>();
        Cursor cursor = db.query(TABLE_NAME6, new String[]{"_id", "nom_medica", "nb_matin", "nb_midi", "nb_soire", "date_debut", "date_fin", "heure_matin",
                        "heure_midi", "heure_soire", "color_lu", "color_ma", "color_me", "color_ju", "color_ve", "color_sa", "color_di"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String nomMedica = cursor.getString(1);
            String nb_matin = cursor.getString(2);
            String nb_midi = cursor.getString(3);
            String nb_soire = cursor.getString(4);
            String date_debut = cursor.getString(5);
            String date_fin = cursor.getString(6);
            String heure_matin = cursor.getString(7);
            String heure_midi = cursor.getString(8);
            String heure_soire = cursor.getString(9);
            int color_lu = cursor.getInt(10);
            int color_ma = cursor.getInt(11);
            int color_me = cursor.getInt(12);
            int color_ju = cursor.getInt(13);
            int color_ve = cursor.getInt(14);
            int color_sa = cursor.getInt(15);
            int color_di = cursor.getInt(16);

            Medicament_Item clt = new Medicament_Item(nomMedica, nb_matin, nb_midi, nb_soire, date_debut, date_fin, heure_matin, heure_midi
                    , heure_soire, color_lu, color_ma, color_me, color_ju, color_ve, color_sa, color_di);
            clt.set_id(id);
            list.add(clt);
            cursor.moveToNext();
        }
        return list;
    }
    public long UpdateMedicament(Medicament_Item medica, int id) {

        ContentValues values = new ContentValues();
        values.put("nom_medica", medica.getNom_medica());
        values.put("nb_matin", medica.getNb_matin());
        values.put("nb_midi", medica.getNb_midi());
        values.put("nb_soire", medica.getNb_soire());
        values.put("date_debut", medica.getDate_debut());
        values.put("date_fin", medica.getDate_fin());
        values.put("heure_matin", medica.getHeure_matin());
        values.put("heure_midi", medica.getHeure_midi());
        values.put("heure_soire", medica.getHeur_soire());
        values.put("color_lu", medica.getColor_lu());
        values.put("color_ma", medica.getColor_ma());
        values.put("color_me", medica.getColor_me());
        values.put("color_ju", medica.getColor_ju());
        values.put("color_ve", medica.getColor_ve());
        values.put("color_sa", medica.getColor_sa());
        values.put("color_di", medica.getColor_di());

        long i = db.update(TABLE_NAME6, values, "_id= " + id, null);
        return i;
    }
    public void deleteMedica(int id) {

        db.delete(TABLE_NAME6, "_id= " + id, null);
    }
}