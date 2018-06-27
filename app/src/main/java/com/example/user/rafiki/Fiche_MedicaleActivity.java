package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fiche_MedicaleActivity extends AppCompatActivity {

    CircleImageView profile_img;
    EditText NomUtilisateur;
    SharedPreferences pref;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_medicale);

        profile_img = (CircleImageView) findViewById(R.id.profile_image);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 2);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);

        NomUtilisateur = findViewById(R.id.nom);

        email = pref.getString("Email", "");

        NomUtilisateur.setText(ds.getNom(email));

        if (ds.getImg(email)!=null) {
                Uri uri = Uri.parse(ds.getImg(email));
                profile_img.setImageURI(uri);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void OpenGallerie(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            System.out.println(uri);
            Toast.makeText(this, "" + uri, Toast.LENGTH_SHORT).show();
            boolean x = ds.addimg(String.valueOf(uri), email);
            //Toast.makeText(this, x+"", Toast.LENGTH_SHORT).show();
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
//        Intent ite = new Intent(this, AntecedentsActivity.class);
//        startActivity(ite);
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