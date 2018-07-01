package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fiche_MedicaleActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    CircleImageView profile_img;
    EditText NomUtilisateur;
    SharedPreferences pref;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    String email;
    Bitmap decodestrim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_medicale);

        profile_img = (CircleImageView) findViewById(R.id.profile_image);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 2);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);

        NomUtilisateur=findViewById(R.id.nom);

        email = pref.getString("Email", "");

        NomUtilisateur.setText(ds.getNom(email));

    }

    public void OpenGallerie(View view) {

        Intent intent =new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(intent,100);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==RESULT_OK)
        {

            Uri uri = data.getData();
            Toast.makeText(this, ""+uri, Toast.LENGTH_SHORT).show();
            profile_img.setImageURI(uri);
        }
    }


    public void groupe_sang(View view) {
//        Intent ite = new Intent(this, Groupe_SangActivity.class);
//        startActivity(ite);
    }

    public void seuils_biometrique(View view) {
//        Intent ite = new Intent(this, SeuilBiometriques.class);
//        startActivity(ite);
    }

    public void antecedents(View view) {
        Intent ite = new Intent(this, AntecedentsActivity.class);
        startActivity(ite);
    }

    public void allergies(View view) {
//        Intent ite = new Intent(this, AllergiesActivity.class);
//        startActivity(ite);
    }

    public void maladies(View view) {
//        Intent ite = new Intent(this, MaladiActivity.class);
//        startActivity(ite);
    }

    public void medicament(View view) {
//        Intent ite = new Intent(this, MedicamentsActivity.class);
//        startActivity(ite);
    }

    public void pass(View view) {
//        Intent ite = new Intent(this, ContactsActivity.class);
//        startActivity(ite);
    }
}