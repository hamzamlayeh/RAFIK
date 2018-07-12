package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Fiche;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fiche_MedicaleActivity extends AppCompatActivity {

    CircleImageView profile_img;
    EditText NomUtilisateur, Sang, Poid, Taille, Num_secrt, Adresse, Code_post, Ville;
    String email, poid, taille, num_secrt, adresse, code_post, ville,sang;
    SharedPreferences pref, pref2;
    SharedPreferences.Editor editor;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    Fiche fiches;
    List<Fiche> list = new ArrayList<Fiche>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_medicale);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);
        pref2 = getApplicationContext().getSharedPreferences("Fiche_Medicale", MODE_PRIVATE);
        editor = pref2.edit();
        email = pref.getString("Email", "");

        profile_img = findViewById(R.id.profile_image);
        NomUtilisateur = findViewById(R.id.nom);
        Sang = findViewById(R.id.txt_sang);
        Poid = findViewById(R.id.poid);
        Taille = findViewById(R.id.taille);
        Num_secrt = findViewById(R.id.num_scret);
        Adresse = findViewById(R.id.adresse);
        Code_post = findViewById(R.id.code_p);
        Ville = findViewById(R.id.ville);

        NomUtilisateur.setText(ds.getNom(email));
        if (ds.getImg(email) != null) {
            Uri uri = Uri.parse(ds.getImg(email));
            profile_img.setImageURI(uri);
        }
        list = ds.getFiche();
        restoredFiche();
        restoredvalue();
    }

    public void restoredFiche() {
        if (list.size() >= 1) {
            if (list.get(0).getEmail().equals(email)) {
                editor.putString("Poid", list.get(0).getPoid());
                editor.putString("Taille", list.get(0).getTaille());
                editor.putString("Num_Secrt", list.get(0).getNum_scret());
                editor.putString("Code_Postal", list.get(0).getCode_postal());
                editor.putString("Adresse", list.get(0).getAdresse());
                editor.putString("Ville", list.get(0).getVille());
                //editor.putString("G_Sang", list.get(0).getSang());
                Sang.setText(list.get(0).getSang());
                editor.apply();
            }
        }
    }

    public void restoredvalue() {
        String restoredpoid = pref2.getString("Poid", null);
        String restoredtaile = pref2.getString("Taille", null);
        String restorednumscret = pref2.getString("Num_Secrt", null);
        String restoredcodeP = pref2.getString("Code_Postal", null);
        String restoredadress = pref2.getString("Adresse", null);
        String restoredville = pref2.getString("Ville", null);
        String restoredsang = pref2.getString("G_Sang", null);

        if (restoredpoid != null) {
            Poid.setText(restoredpoid);
        }
        if (restoredtaile != null) {
            Taille.setText(restoredtaile);
        }
        if (restorednumscret != null) {
            Num_secrt.setText(restorednumscret);
        }
        if (restoredcodeP != null) {
            Code_post.setText(restoredcodeP);
        }
        if (restoredadress != null) {
            Adresse.setText(restoredadress);
        }
        if (restoredville != null) {
            Ville.setText(restoredville);
        }
        if (restoredsang != null) {
            Sang.setText(restoredsang);
        }
    }

    public void OpenGallerie(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            profile_img.setImageURI(uri);
            ds.UpdateImg(String.valueOf(uri), email);
        }
    }


    public void groupe_sang(View view) {
        remplir_champs();
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
        Intent ite = new Intent(this, AllergiesActivity.class);
        startActivity(ite);
    }

    public void maladies(View view) {
        Intent ite = new Intent(this, MaladiActivity.class);
        startActivity(ite);
    }

    public void medicament(View view) {
        Intent ite = new Intent(this, MedicamentsActivity.class);
        startActivity(ite);
    }
    public void remplir_champs() {
        editor.putString("Poid", Poid.getText().toString().trim());
        editor.putString("Taille", Taille.getText().toString().trim());
        editor.putString("Num_Secrt", Num_secrt.getText().toString().trim());
        editor.putString("Adresse", Adresse.getText().toString().trim());
        editor.putString("Code_Postal", Code_post.getText().toString().trim());
        editor.putString("Ville", Ville.getText().toString().trim());
        editor.apply();
    }
    public void enregestrer(View view) {
        poid = Poid.getText().toString().trim();
        taille = Taille.getText().toString().trim();
        num_secrt = Num_secrt.getText().toString().trim();
        adresse = Adresse.getText().toString().trim();
        code_post = Code_post.getText().toString().trim();
        ville = Ville.getText().toString().trim();
        sang = Sang.getText().toString().trim();
        if (valider()) {
            //String Sang = pref2.getString("G_Sang", "");

            fiches = new Fiche(email, poid, taille, num_secrt, adresse, code_post, ville, sang);
            if (list.size()<1){
                long ids = ds.addFiche(fiches);
                if (ids == -1) {
                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(list.size());
                    Toast.makeText(this, R.string.fiche_enregistre, Toast.LENGTH_LONG).show();
                    Intent ite = new Intent(this, MenuActivity.class);
                    startActivity(ite);
                }
            }else {
                long ids=ds.UpdateFiche(email,fiches);
                if (ids == -1) {
                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(list.size());
                    Toast.makeText(this, R.string.fiche_enregistre, Toast.LENGTH_LONG).show();
                    Intent ite = new Intent(this, MenuActivity.class);
                    startActivity(ite);
                }
            }

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