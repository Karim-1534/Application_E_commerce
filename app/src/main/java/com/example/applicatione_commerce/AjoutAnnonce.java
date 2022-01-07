package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class AjoutAnnonce extends Activity {
    private TextView imgPhoto;
    private Button btnPhoto;
    private Button btn_valider ;
    private List<String> list = new ArrayList<String>();
    private Spinner spinner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_annonce);
        initActivity();
    }




    private void initActivity(){

       spinner = (Spinner) findViewById(R.id.categorie);
        initSpinnerCatergorie();

        btn_valider = (Button) findViewById(R.id.btn_ajout_annonce);
        createOnClickValidationAjoutButton();

        imgPhoto = (TextView)findViewById(R.id.image);
        btnPhoto = (Button) findViewById(R.id.btn_import_image);
        createOnClickPhotoButton();
    }


    /*
    *événement boutton ajout validation d'ajout
    *
    * */

    private void createOnClickValidationAjoutButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.conf_ajout_annonce);
                Intent intent = new Intent(AjoutAnnonce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    /*
     *événement boutton importe annonce
     * */
    private void createOnClickPhotoButton(){
        btnPhoto.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                //accès à la galerie
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,66);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //vérification si l'image est récupérer
        if(requestCode == 66 && resultCode == RESULT_OK){
            //accès à l'image à partir de data
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            //curseur d'accès au chemin de l'image
            Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            //position sur la ligne (normalement une seule)
            cursor.moveToFirst();
            //récupration chemin précis de l'image
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String imgPath = cursor.getString(columnIndex);
            cursor.close();
            //récupération image
            //Bitmap image = BitmapFactory.decodeFile((imgPath));
            //afficher
            imgPhoto.setText(imgPath);
            Toast.makeText(this, "Image bien sélectionné", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Aucune image sélectionné", Toast.LENGTH_LONG).show();
        }
    }

@Override
public void onResume() {

    super.onResume();
    initActivity();
}
    /*
    * initialisation du spinner catégorie
    * */
    private void initSpinnerCatergorie(){
        list.clear();
        list.add("------");
        list.add("Animaux de compagnie");
        list.add("Mode et beauté");
        list.add("Maison et bricolage");
        list.add("Appareils & Electronique");
        list.add("Musique, vidéo et jeux");
        list.add("Livres et lecture");
        list.add("Jouets, enfants et bébé");
        list.add("Auto et Moto");
        list.add("Bureau et Professionnel");
        list.add("Sport et boutique de fans");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);

    }


}
