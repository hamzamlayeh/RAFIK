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

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity {

    EditText NomUtilisateur;
    SharedPreferences pref;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    CircleImageView profile_img;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profile_img = (CircleImageView) findViewById(R.id.profile_image);
        NomUtilisateur=findViewById(R.id.nom);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);

        email = pref.getString("Email", "");
        NomUtilisateur.setText(ds.getNom(email));
        if (ds.getImg(email)!=null) {
            Uri uri = Uri.parse(ds.getImg(email));
            profile_img.setImageURI(uri);

        }
    }

    public void E8(View view) {
        Intent ite = new Intent(this, E8.class);
        startActivity(ite);
    }

    public void donnees_pers(View view) {
        Intent ite = new Intent(this, ModifierCompte.class);
        resetvalue();
        startActivity(ite);

    }
    public void resetvalue() {
        SharedPreferences.Editor editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
        editor.remove("Nom");
        editor.remove("Prenom");
        editor.remove("Age");
        editor.remove("Mobile");
        editor.remove("Password");
        editor.remove("Password_conf");
        editor.remove("sexe");
        editor.remove("Nom_Pays");
        editor.remove("ID_img");
        editor.remove("Id_code");
        editor.remove("Code_pays");
        editor.apply();
    }
    public void fiche(View view) {
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
    }

    public void contacts(View view) {
        Intent ite = new Intent(this, ContactsActivity.class);
        startActivity(ite);
    }

    public void alertes(View view) {
        Intent ite = new Intent(this, ParametreAlertes.class);
        startActivity(ite);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void deconnecter(View view) {
        Intent ite = new Intent(this, LoginActivity.class);
        startActivity(ite);
        this.finishAffinity();

    }

    public void modifImg(View view) {
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
}
