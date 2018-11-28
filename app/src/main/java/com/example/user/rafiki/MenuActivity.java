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

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity {

    EditText NomUtilisateur;
    SharedPreferences pref;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    CircleImageView profile_img;
    String email;
    final static int MY_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            checkSmsPermission();
        }
        profile_img = (CircleImageView) findViewById(R.id.profile_image);
        NomUtilisateur = findViewById(R.id.nom);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);

        email = pref.getString("Email", "");
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
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, E8.class);
            startActivity(ite);
        }
        return false;
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
        editor.remove("Poid");
        editor.remove("ID_img");
        editor.remove("Id_code");
        editor.remove("Code_pays");
        editor.apply();
    }

    public void fiche(View view) {
        resetvalueFiche();
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
    }

    public void resetvalueFiche() {
        SharedPreferences.Editor editor = getSharedPreferences("Fiche_Medicale", MODE_PRIVATE).edit();
        editor.remove("Taille");
        editor.remove("Num_Secrt");
        editor.remove("Code_Postal");
        editor.remove("Adresse");
        editor.remove("Ville");
        editor.remove("G_Sang");
        editor.apply();
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
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap bitmap = rotationImage(thumbnail, getRealPathFromURI(uri));
                profile_img.setImageBitmap(bitmap);
//                profile_img.setImageURI(uri);
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
