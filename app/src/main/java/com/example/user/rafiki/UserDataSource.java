package com.example.user.rafiki;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.rafiki.ItemData.Alerts;
import com.example.user.rafiki.ItemData.Antecedents_Item;
import com.example.user.rafiki.ItemData.Contacts_Medecins;
import com.example.user.rafiki.ItemData.Contacts_Parentaux;
import com.example.user.rafiki.ItemData.Contacts_Urgences;
import com.example.user.rafiki.ItemData.Cycle;
import com.example.user.rafiki.ItemData.Fiche;
import com.example.user.rafiki.ItemData.Medicament_Item;
import com.example.user.rafiki.ItemData.SeuilValues;
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
    private final String TABLE_NAME7 = "Contacts_Parentaux";
    private final String TABLE_NAME8 = "Contacts_Medecins";
    private final String TABLE_NAME9 = "Contacts_Urgences";
    private final String TABLE_NAME10 = "Seuils_Bio";
    private final String TABLE_NAME11 = "Cycle";
    private final String TABLE_NAME12 = "Parametre_Alert";

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
        values.put("poid", clt.getPoid());
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
        values.put("poid", clt.getPoid());
        return db.update(TABLE_NAME, values, "_id=" + id, null) > 0;
    }


    public List getAllClient() {

        ArrayList<clients> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"_id", "nom", "prenom", "age", "payer", "mobile", "code", "sexe", "email", "password", "poid"}, null,
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
            String poid = cursor.getString(10);

            clients clt = new clients(nom, prenom, age, payer, mobile, code, sexe, email, poid, password);
            clt.set_id(id);
            list.add(clt);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public boolean verifEmail(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"email"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        cursor.close();
        return cursorcount > 0;
    }

    public boolean verifUser(String email, String password) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"email,password"}, "email=? AND password=?", new String[]{email, password},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        cursor.close();
        return cursorcount > 0;
    }

    public String getPassword(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"password"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        cursor.close();
        return "";
    }

    public String getNom(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"nom", "prenom"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            return cursor.getString(0) + " " + cursor.getString(1);
        }
        cursor.close();
        return "";
    }

    public String getPoid(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"poid"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        cursor.close();
        return null;
    }

    public void UpdateImg(String img, String email) {

        ContentValues values = new ContentValues();
        values.put("image", img);
        db.update(TABLE_NAME, values, "email=?", new String[]{email});

    }

    public String getImg(String email) {

        Cursor cursor = db.query(TABLE_NAME, new String[]{"image"}, "email=?", new String[]{email},
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        cursor.close();
        return null;
    }

    public long addFiche(Fiche fich) {

        ContentValues values = new ContentValues();
        values.put("email", fich.getEmail());
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

        ArrayList<Fiche> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME2, new String[]{"_id", "email", "taille", "num_scret", "adresse", "code_postal", "ville", "sang"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String email = cursor.getString(1);
            String taille = cursor.getString(2);
            String num_scret = cursor.getString(3);
            String adresse = cursor.getString(4);
            String code_postal = cursor.getString(5);
            String ville = cursor.getString(6);
            String sang = cursor.getString(7);

            Fiche clt = new Fiche(email, taille, num_scret, adresse, code_postal, ville, sang);
            clt.set_id(id);
            System.out.println(id);
            list.add(clt);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public long UpdateFiche(String email, Fiche fich) {

        ContentValues values = new ContentValues();
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
        int count = cursor.getCount();
        cursor.close();
        return count;

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
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME3, new String[]{"NomMaladi"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {

            list.add(i, cursor.getString(0));
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //ANTECEDENTS
    public int getCountAntece() {
        Cursor cursor = db.query(TABLE_NAME4, null, null, null,
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
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
        ArrayList<Antecedents_Item> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME4, new String[]{"acte,date"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String acte = cursor.getString(0);
            String date = cursor.getString(1);

            list.add(new Antecedents_Item(acte, date));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void addallergie(int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_allergie", id);

        db.insert(TABLE_NAME5, null, contentValues);
    }

    public boolean verifId_allergie(int id) {

        Cursor cursor = db.query(TABLE_NAME5, new String[]{"id_allergie"}, "id_allergie= " + id, null,
                null, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            return true;
        }
        cursor.close();
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
        ArrayList<String> list = new ArrayList<>();
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
        cursor.close();
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

        ArrayList<Medicament_Item> list = new ArrayList<>();
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
        cursor.close();
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

    //Contacts Parentaux
    public int getCountParentaux() {
        Cursor cursor = db.query(TABLE_NAME7, null, null, null,
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public long addParentaux(Contacts_Parentaux itemData) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", itemData.getNom());
        contentValues.put("prenom", itemData.getPrenom());
        contentValues.put("mobile", itemData.getMobile());
        contentValues.put("code", itemData.getCode());
        contentValues.put("email", itemData.getEmail());

        return db.insert(TABLE_NAME7, null, contentValues);
    }

    public long UpdateParentaux(Contacts_Parentaux itemData, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", itemData.getNom());
        contentValues.put("prenom", itemData.getPrenom());
        contentValues.put("mobile", itemData.getMobile());
        contentValues.put("code", itemData.getCode());
        contentValues.put("email", itemData.getEmail());

        return (long) db.update(TABLE_NAME7, contentValues, "_id= " + id, null);
    }

    public List getListParentaux() {
        ArrayList<Contacts_Parentaux> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME7, new String[]{"nom,prenom,mobile,code,email"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String nom = cursor.getString(0);
            String prenom = cursor.getString(1);
            String mobile = cursor.getString(2);
            String code = cursor.getString(3);
            String email = cursor.getString(4);

            list.add(new Contacts_Parentaux(nom, prenom, mobile, code, email));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //Contacts Medecins
    public int getCountMedecins() {
        Cursor cursor = db.query(TABLE_NAME8, null, null, null,
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public long addMedecins(Contacts_Medecins itemData) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", itemData.getNom());
        contentValues.put("prenom", itemData.getPrenom());
        contentValues.put("mobile", itemData.getMobile());
        contentValues.put("code", itemData.getCode());
        contentValues.put("email", itemData.getEmail());
        contentValues.put("hopital", itemData.getHopital());

        return db.insert(TABLE_NAME8, null, contentValues);
    }

    public long UpdateMedecins(Contacts_Medecins itemData, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", itemData.getNom());
        contentValues.put("prenom", itemData.getPrenom());
        contentValues.put("mobile", itemData.getMobile());
        contentValues.put("code", itemData.getCode());
        contentValues.put("email", itemData.getEmail());
        contentValues.put("hopital", itemData.getHopital());

        long ids = db.update(TABLE_NAME8, contentValues, "_id= " + id, null);
        return ids;
    }

    public List getListMedecins() {
        ArrayList<Contacts_Medecins> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME8, new String[]{"nom,prenom,mobile,code,email,hopital"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String nom = cursor.getString(0);
            String prenom = cursor.getString(1);
            String mobile = cursor.getString(2);
            String code = cursor.getString(3);
            String email = cursor.getString(4);
            String hopital = cursor.getString(5);

            list.add(new Contacts_Medecins(nom, prenom, mobile, code, email, hopital));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //Contacts_Urgences
    public int getCountUrgences() {
        Cursor cursor = db.query(TABLE_NAME9, null, null, null,
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public long addUrgences(Contacts_Urgences itemData) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nom_asur", itemData.getNom_asur());
        contentValues.put("tel_asur", itemData.getTel_asur());
        contentValues.put("code_asur", itemData.getCode_asur());
        contentValues.put("nom_urg", itemData.getNom_urg());
        contentValues.put("tel_urg", itemData.getTel_urg());
        contentValues.put("code_urg", itemData.getCode_urg());

        return db.insert(TABLE_NAME9, null, contentValues);
    }

    public long UpdateUrgences(Contacts_Urgences itemData, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nom_asur", itemData.getNom_asur());
        contentValues.put("tel_asur", itemData.getTel_asur());
        contentValues.put("code_asur", itemData.getCode_asur());
        contentValues.put("nom_urg", itemData.getNom_urg());
        contentValues.put("tel_urg", itemData.getTel_urg());
        contentValues.put("code_urg", itemData.getCode_urg());

        return (long) db.update(TABLE_NAME9, contentValues, "_id= " + id, null);
    }

    public List getListUrgences() {
        ArrayList<Contacts_Urgences> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME9, new String[]{"nom_asur,tel_asur,code_asur,nom_urg,tel_urg,code_urg"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String nom_asur = cursor.getString(0);
            String tel_asur = cursor.getString(1);
            String code_asur = cursor.getString(2);
            String nom_urg = cursor.getString(3);
            String tel_urg = cursor.getString(4);
            String code_urg = cursor.getString(5);

            list.add(new Contacts_Urgences(nom_asur, tel_asur, code_asur, nom_urg, tel_urg, code_urg));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //Seuil biometrique
    public int getCountSeuils() {
        Cursor cursor = db.query(TABLE_NAME10, null, null, null,
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public long addSeuils(SeuilValues itemData) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("FCmarche_M", itemData.getFCmarche_M());
        contentValues.put("FCmarche_X", itemData.getFCmarche_X());
        contentValues.put("FCcourse_M", itemData.getFCcourse_M());
        contentValues.put("FCcourse_X", itemData.getFCcourse_X());
        contentValues.put("FCactivite_M", itemData.getFCactivite_M());
        contentValues.put("FCactivite_X", itemData.getFCactivite_X());
        contentValues.put("FCsommeil_M", itemData.getFCsommeil_M());
        contentValues.put("FCsommeil_X", itemData.getFCsommeil_X());
        contentValues.put("FRmarche_M", itemData.getFRmarche_M());
        contentValues.put("FRmarche_X", itemData.getFRmarche_X());
        contentValues.put("FRcourse_M", itemData.getFRcourse_M());
        contentValues.put("FRcourse_X", itemData.getFRcourse_X());
        contentValues.put("FRactivite_M", itemData.getFRactivite_M());
        contentValues.put("FRactivite_X", itemData.getFRactivite_X());
        contentValues.put("FRsommeil_M", itemData.getFRsommeil_M());
        contentValues.put("FRsommeil_X", itemData.getFRsommeil_X());
        contentValues.put("Tmarche_M", itemData.getTmarche_M());
        contentValues.put("Tmarche_X", itemData.getTmarche_X());
        contentValues.put("Tcourse_M", itemData.getTcourse_M());
        contentValues.put("Tcourse_X", itemData.getTcourse_X());
        contentValues.put("Tactivite_M", itemData.getTactivite_M());
        contentValues.put("Tactivite_X", itemData.getTactivite_X());
        contentValues.put("Tsommeil_M", itemData.getTsommeil_M());
        contentValues.put("Tsommeil_X", itemData.getTsommeil_X());

        return db.insert(TABLE_NAME10, null, contentValues);
    }

    public long UpdateSeuils(SeuilValues itemData, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("FCmarche_M", itemData.getFCmarche_M());
        contentValues.put("FCmarche_X", itemData.getFCmarche_X());
        contentValues.put("FCcourse_M", itemData.getFCcourse_M());
        contentValues.put("FCcourse_X", itemData.getFCcourse_X());
        contentValues.put("FCactivite_M", itemData.getFCactivite_M());
        contentValues.put("FCactivite_X", itemData.getFCactivite_X());
        contentValues.put("FCsommeil_M", itemData.getFCsommeil_M());
        contentValues.put("FCsommeil_X", itemData.getFCsommeil_X());
        contentValues.put("FRmarche_M", itemData.getFRmarche_M());
        contentValues.put("FRmarche_X", itemData.getFRmarche_X());
        contentValues.put("FRcourse_M", itemData.getFRcourse_M());
        contentValues.put("FRcourse_X", itemData.getFRcourse_X());
        contentValues.put("FRactivite_M", itemData.getFRactivite_M());
        contentValues.put("FRactivite_X", itemData.getFRactivite_X());
        contentValues.put("FRsommeil_M", itemData.getFRsommeil_M());
        contentValues.put("FRsommeil_X", itemData.getFRsommeil_X());
        contentValues.put("Tmarche_M", itemData.getTmarche_M());
        contentValues.put("Tmarche_X", itemData.getTmarche_X());
        contentValues.put("Tcourse_M", itemData.getTcourse_M());
        contentValues.put("Tcourse_X", itemData.getTcourse_X());
        contentValues.put("Tactivite_M", itemData.getTactivite_M());
        contentValues.put("Tactivite_X", itemData.getTactivite_X());
        contentValues.put("Tsommeil_M", itemData.getTsommeil_M());
        contentValues.put("Tsommeil_X", itemData.getTsommeil_X());

        return (long) db.update(TABLE_NAME10, contentValues, "_id= " + id, null);
    }

    public long UpdateSeuilsInsc(SeuilValues itemData, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("FCmarche_X", itemData.getFCmarche_X());
        contentValues.put("FCcourse_X", itemData.getFCcourse_X());
        contentValues.put("FCactivite_X", itemData.getFCactivite_X());
        contentValues.put("FCsommeil_X", itemData.getFCsommeil_X());

        return (long) db.update(TABLE_NAME10, contentValues, "_id= " + id, null);
    }

    public List<SeuilValues> getListSeuils() {
        ArrayList<SeuilValues> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME10, new String[]{"FCmarche_M,FCmarche_X,FCcourse_M,FCcourse_X," +
                        "FCactivite_M,FCactivite_X,FCsommeil_M,FCsommeil_X,FRmarche_M,FRmarche_X,FRcourse_M," +
                        "FRcourse_X,FRactivite_M,FRactivite_X,FRsommeil_M,FRsommeil_X,Tmarche_M,Tmarche_X,Tcourse_M," +
                        "Tcourse_X,Tactivite_M,Tactivite_X,Tsommeil_M,Tsommeil_X"}, null, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String F1 = cursor.getString(0);
            String F2 = cursor.getString(1);
            String F3 = cursor.getString(2);
            String F4 = cursor.getString(3);
            String F5 = cursor.getString(4);
            String F6 = cursor.getString(5);
            String F7 = cursor.getString(6);
            String F8 = cursor.getString(7);
            String F9 = cursor.getString(8);
            String F10 = cursor.getString(9);
            String F11 = cursor.getString(10);
            String F12 = cursor.getString(11);
            String F13 = cursor.getString(12);
            String F14 = cursor.getString(13);
            String F15 = cursor.getString(14);
            String F16 = cursor.getString(15);
            String F17 = cursor.getString(16);
            String F18 = cursor.getString(17);
            String F19 = cursor.getString(18);
            String F20 = cursor.getString(19);
            String F21 = cursor.getString(20);
            String F22 = cursor.getString(21);
            String F23 = cursor.getString(22);
            String F24 = cursor.getString(23);

            list.add(new SeuilValues(F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16, F17, F18, F19, F20, F21, F22, F23, F24));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //Cycle
    public void addCycle(Cycle cycle) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("fuldate_cycle", cycle.getFuldate_cycle());
        contentValues.put("date", cycle.getDate());
        contentValues.put("time", cycle.getTime());
        contentValues.put("frequenceC", cycle.getFrequenceC());
        contentValues.put("poumon", cycle.getPoumon());
        contentValues.put("tempirateur", cycle.getTempirateur());
        contentValues.put("nb_pas", cycle.getNb_pas());
        contentValues.put("calorie", cycle.getCalorie());
        contentValues.put("cycle", cycle.getCycle());

        db.insert(TABLE_NAME11, null, contentValues);
    }

    public void deleteCycle(String fulldate) {

        db.delete(TABLE_NAME11, "fuldate_cycle = ?", new String[]{fulldate});
    }

    public List getListCycle(String fulldate) {
        ArrayList<Cycle> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT frequenceC,poumon,tempirateur FROM Cycle WHERE fuldate_cycle = ?"
                , new String[]{fulldate});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            double coeur = cursor.getDouble(0);
            double poumon = cursor.getDouble(1);
            double tempirateur = cursor.getDouble(2);

            list.add(new Cycle(coeur, poumon, tempirateur));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int getCountCycle(String fulldate) {
        Cursor cursor = db.query(TABLE_NAME11, null, "fuldate_cycle = ?", new String[]{fulldate},
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //Parametre_Alert
    public void addAlert(Alerts alerts) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("smsNiveau2", alerts.getSMSNiveau2());
        contentValues.put("smsNiveau3", alerts.getSMSNiveau3());
        contentValues.put("smsNiveau4", alerts.getSMSNiveau4());

        db.insert(TABLE_NAME12, null, contentValues);
    }

    public List getListAlerts() {
        ArrayList<Alerts> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME12, new String[]{"smsNiveau2,smsNiveau3,smsNiveau4"}, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String alert2 = cursor.getString(0);
            String alert3 = cursor.getString(1);
            String alert4 = cursor.getString(2);

            list.add(new Alerts(alert2, alert3, alert4));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public long UpdateAlert(Alerts alerts, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("smsNiveau2", alerts.getSMSNiveau2());
        contentValues.put("smsNiveau3", alerts.getSMSNiveau3());
        contentValues.put("smsNiveau4", alerts.getSMSNiveau4());

        return (long) db.update(TABLE_NAME12, contentValues, "_id= " + id, null);
    }

    public int getCountAlerts() {
        Cursor cursor = db.query(TABLE_NAME12, null, null, null,
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}