package com.example.user.rafiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 30/03/2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table clients (_id integer primary key autoincrement not null," +
                "nom text not null,prenom text not null,age text not null,payer text not null," +
                "mobile text not null,sexe text not null,email text not null,password text not null,image blob )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String delet_sql="DROP Table clients";
        sqLiteDatabase.execSQL(delet_sql);

        onCreate(sqLiteDatabase);
    }
}
