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

    CircleImageView profile_img;
    EditText NomUtilisateur,Sang,Poid,Taille,Num_secrt,Adresse,Code_post,Ville;
    String email,poid,taille,num_secrt,adresse,code_post,ville;
    SharedPreferences pref,pref2;
    MySQLiteOpenHelper helper;
    UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_medicale);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 2);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);
        pref2 = getApplicationContext().getSharedPreferences("Fiche_Medicale", MODE_PRIVATE);
        email = pref.getString("Email", "");

        profile_img = findViewById(R.id.profile_image);
        NomUtilisateur=findViewById(R.id.nom);
        Sang=findViewById(R.id.txt_sang);
        Poid=findViewById(R.id.poid);
        Taille=findViewById(R.id.taille);
        Num_secrt=findViewById(R.id.num_scret);
        Adresse=findViewById(R.id.adresse);
        Code_post=findViewById(R.id.code_p);
        Ville=findViewById(R.id.ville);

        NomUtilisateur.setText(ds.getNom(email));
        if (ds.getImg(email)!=null) {
            Uri uri = Uri.parse(ds.getImg(email));
            profile_img.setImageURI(uri);
        }
        restoredvalue();
    }
    public void restoredvalue() {
        String restoredsang = pref2.getString("G_Sang", null);
        if (restoredsang!=null){
            Sang.setText(restoredsang);
        }
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
            profile_img.setImageURI(uri);
            ds.addimg(String.valueOf(uri), email);
        }
    }


    public void groupe_sang(View view) {
        Intent ite = new Intent(this, Groupe_SangActivity.class);
        startActivity(ite);
    }

    public void seuils_biometrique(View view) {
        Intent ite = new Intent(this, SeuilBiometriques.class);
        startActivity(ite);
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

    public void enregestrer(View view) {
        poid = Poid.getText().toString().trim();
        taille = Taille.getText().toString().trim();
        num_secrt = Num_secrt.getText().toString().trim();
        adresse = Adresse.getText().toString().trim();
        code_post = Code_post.getText().toString().trim();
        ville = Ville.getText().toString().trim();
        if (valider()) {
            String Sang = pref2.getString("G_Sang", "");
            Toast.makeText(this, Sang, Toast.LENGTH_SHORT).show();
            Intent ite = new Intent(this, MenuActivity.class);
            startActivity(ite);
        }

    }
    private boolean valider() {

        boolean valide = true;
        if (poid.isEmpty()) {
            Poid.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (taille.isEmpty()) {
            Taille.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (adresse.isEmpty()) {
            Adresse.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (code_post.isEmpty()) {
            Code_post.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (ville.isEmpty()) {
            Ville.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        return valide;
    }
}