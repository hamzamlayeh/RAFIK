package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fiche_MedicaleActivity extends AppCompatActivity {

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
        //byte[] img=ds.getImg( email);
       // profile_img.setImageBitmap(x);
       // Toast.makeText(this, img.length+"", Toast.LENGTH_SHORT).show();
    }

    public void OpenGallerie(View view) {
        Intent itimg = new Intent(Intent.ACTION_GET_CONTENT);
        itimg.setType("image/*");
        startActivityForResult(itimg, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                 decodestrim = BitmapFactory.decodeStream(inputStream);
                profile_img.setImageBitmap(decodestrim);

            } catch (Exception ex) {
                Log.e("err", ex.getMessage());
            }
            boolean test =ds.addimg(getByte(decodestrim),email);
            Toast.makeText(this, test+"", Toast.LENGTH_SHORT).show();
        }
    }
    public byte[] getByte(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
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