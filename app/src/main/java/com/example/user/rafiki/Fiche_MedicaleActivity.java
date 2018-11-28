package com.example.user.rafiki;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Fiche;
import com.tuyenmonkey.mkloader.MKLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fiche_MedicaleActivity extends AppCompatActivity {

    CircleImageView profile_img;
    EditText NomUtilisateur, Sang, Taille, Num_secrt, Adresse, Code_post, Ville;
    String email, taille, num_secrt, adresse, code_post, ville, sang;
    SharedPreferences pref, pref2;
    SharedPreferences.Editor editor;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    Fiche fiches;
    List<Fiche> list = new ArrayList<Fiche>();
    final static int MY_PERMISSIONS_REQUEST = 1;
    MKLoader mkLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_medicale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            checkSmsPermission();
        }
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);
        pref2 = getApplicationContext().getSharedPreferences("Fiche_Medicale", MODE_PRIVATE);
        editor = pref2.edit();
        email = pref.getString("Email", "");

        profile_img = findViewById(R.id.profile_image);
        NomUtilisateur = findViewById(R.id.nom);
        Sang = findViewById(R.id.txt_sang);
        Taille = findViewById(R.id.taille);
        Num_secrt = findViewById(R.id.num_scret);
        Adresse = findViewById(R.id.adresse);
        Code_post = findViewById(R.id.code_p);
        Ville = findViewById(R.id.ville);
        mkLoader = findViewById(R.id.alerr);

        NomUtilisateur.setText(ds.getNom(email));
        if (ds.getImg(email) != null) {
            Uri uri = Uri.parse(ds.getImg(email));
            try {
                Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap bitmap = rotationImage(thumbnail, getRealPathFromURI(uri));
                profile_img.setImageBitmap(bitmap);
//                profile_img.setImageURI(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        list = ds.getFiche();
        restoredFiche();
        restoredvalue();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, MenuActivity.class);
            startActivity(ite);
        }
        return false;
    }

    public void restoredFiche() {
        if (list.size() >= 1) {
            if (list.get(0).getEmail().equals(email)) {
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
        String restoredtaile = pref2.getString("Taille", null);
        String restorednumscret = pref2.getString("Num_Secrt", null);
        String restoredcodeP = pref2.getString("Code_Postal", null);
        String restoredadress = pref2.getString("Adresse", null);
        String restoredville = pref2.getString("Ville", null);
        String restoredsang = pref2.getString("G_Sang", null);

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

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap bitmap = rotationImage(thumbnail, getPath(uri));
                profile_img.setImageBitmap(bitmap);
                //profile_img.setImageURI(uri);
                ds.UpdateImg(String.valueOf(uri), email);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public Bitmap rotationImage(Bitmap bitmap, String imageUri) throws IOException {
        ExifInterface exifInterface = new ExifInterface(imageUri);
        int oreintation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (oreintation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);
            default:
                return bitmap;
        }
    }

    private Bitmap flip(Bitmap bitmap, boolean horizontal, boolean verticale) {
        Matrix matrix = new Matrix();
        matrix.postScale(horizontal ? -1 : 1, verticale ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        editor.putString("Taille", Taille.getText().toString().trim());
        editor.putString("Num_Secrt", Num_secrt.getText().toString().trim());
        editor.putString("Adresse", Adresse.getText().toString().trim());
        editor.putString("Code_Postal", Code_post.getText().toString().trim());
        editor.putString("Ville", Ville.getText().toString().trim());
        editor.apply();
    }

    public void enregestrer(View view) {
        taille = Taille.getText().toString().trim();
        num_secrt = Num_secrt.getText().toString().trim();
        adresse = Adresse.getText().toString().trim();
        code_post = Code_post.getText().toString().trim();
        ville = Ville.getText().toString().trim();
        sang = Sang.getText().toString().trim();
        if (valider()) {
            fiches = new Fiche(email, taille, num_secrt, adresse, code_post, ville, sang);
            if (list.size() < 1) {
                long ids = ds.addFiche(fiches);
                if (ids == -1) {
                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    mkLoader.setVisibility(View.VISIBLE);
                    Intent ite = new Intent(this, MenuActivity.class);
                    startActivity(ite);
                    Fiche_MedicaleActivity.this.finish();
                }
            } else {
                long ids = ds.UpdateFiche(email, fiches);
                if (ids == -1) {
                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    mkLoader.setVisibility(View.VISIBLE);
                    Intent ite = new Intent(this, MenuActivity.class);
                    startActivity(ite);
                    Fiche_MedicaleActivity.this.finish();
                }
            }
        }
    }

    private boolean valider() {

        boolean valide = true;
        if (taille.isEmpty()) {
            Taille.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (!taille.isEmpty()) {
            int taile = Integer.parseInt(taille);
            if (taile > 300) {
                Taille.setError(getString(R.string.max_300));
                valide = false;
            }
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);
            }
        } else {
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED) {

                    }
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST);
                }
                return;
            }
        }
    }


}