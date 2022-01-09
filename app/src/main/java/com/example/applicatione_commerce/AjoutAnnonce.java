package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.applicatione_commerce.Model.Rayon;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AjoutAnnonce extends Activity {
    private TextView imgPhoto;
    private Button btnPhoto;
    private Button btn_valider ;
    List<String> listRayon = new ArrayList<>();
    List<String> list = new ArrayList<>();
    List<List<String>> listService= new ArrayList<>();
    List<List<String>> serviceRecuperer = new ArrayList<>();
    List<String> serviceCorrespondant = new ArrayList<>();
    private Spinner spinnerRayon;
    private Spinner spinnerService;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_produit);
        initActivity();
    }




    private void initActivity(){
        spinnerRayon = (Spinner) findViewById(R.id.categorie);
        spinnerService = (Spinner) findViewById(R.id.service);
        initSpinnerRayonEtService();

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
                String str = getResources().getString(R.string.valid_ajout_annonce);
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
   }
    /*
    * initialisation du spinner catégorie
    * */
    private void initSpinnerRayonEtService(){

         db.collection("RAYON").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        if(querySnapshot.isEmpty()){
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                        }else{
                            List<Rayon> rayons = querySnapshot.toObjects(Rayon.class);
                            ArrayList<Rayon> listRayons = new ArrayList<>();
                            listRayons.addAll(rayons);
                            rayons.forEach(rayon -> {
                                        listRayon.add(rayon.getNOM());
                                        listService.add(rayon.getSERVICES());
                                       Log.d(TAG, "list rayon dans le for" + listRayon);
                                       // Log.d(TAG, "list rayon imaginaire "+ listRayons);
                                    });
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AjoutAnnonce.this,
                                R.layout.spinner_item_service_rayon,
                                listRayon);
                        spinnerRayon.setAdapter(adapter);
                        spinnerRayon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                serviceRecuperer.clear();
                                serviceCorrespondant.clear();
                                serviceRecuperer.add(listService.get(position));

                                serviceRecuperer.forEach(s -> {
                                    s.forEach(s1 -> {
                                        serviceCorrespondant.add(s1);
                                    });
                                });
                                Log.d(TAG, "Les listes de services " + serviceCorrespondant);
                                Log.d(TAG,"Les sevices correspondants sont  " +serviceRecuperer);

                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(AjoutAnnonce.this, R.layout.spinner_item_service_rayon, serviceCorrespondant);
                                spinnerService.setAdapter(adapter1);


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Log.d(TAG,"Y a pas de services selectionner ");
                            }
                        });
                        /*ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(AjoutAnnonce.this, R.layout.spinner_item, serviceRecuperer);
                        spinnerService.setAdapter(adapter1);*/

                    }
                });


    }


}
