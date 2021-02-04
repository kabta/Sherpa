/**package com.example.sherpaatourguide.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner1;
    private Button addbtn;
    Datas datas;
    Button upbtn;
    Button chbtn;
    ImageView image1;
    EditText name, description, maplink;
    StorageReference mStorageRef;
    DatabaseReference dbreff;
    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_data);
    mStorageRef = FirebaseStorage.getInstance().getReference("Image");
    dbreff =FirebaseDatabase.getInstance().getReference().child("Datas");
    name = findViewById(R.id.name);
    description = findViewById(R.id.placeDescription);
    maplink = findViewById(R.id.mapLink);
    image1 = findViewById(R.id.placeImage);
    addbtn = findViewById(R.id.addBtn);
    chbtn = findViewById(R.id.choosebtn);
    upbtn = findViewById(R.id.uploadBtn);
    chbtn.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Filechooser();
        }
    });
    upbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fileuploader();
        }
    });

    spinnerItems();
    buttonClick();
    }
        private String getExtension(Uri uri) {
            ContentResolver cr = getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//            return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
        }
        private void Fileuploader()
        {
            String imageid;
            imageid = System.currentTimeMillis()+"."+getExtension(imguri);
            datas.setName(name.getText().toString().trim());
            datas.setDescription(description.getText().to);
            StorageReference Ref = mStorageRef.child((imageid));
            Ref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Toast.makeText(AddDataActivity.this, "Image Uploaded sucessfully", Toast.LENGTH_LONG);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
    }

    private void spinnerItems() {
        spinner1 = (Spinner) findViewById(R.id.spinner);
        List list = new ArrayList();
        list.add("Destinations");
        list.add("Religious Places");
        list.add("Bus Stations");
        list.add("Restaurants and Hotels");
        list.add("Shopping malls");
        ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Select item " , Toast.LENGTH_LONG).show();

    }

    private void buttonClick(){
        spinner1 = (Spinner) findViewById(R.id.spinner);

        addbtn = (Button) findViewById(R.id.addBtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddDataActivity.this,AddDataActivity.class);
                intent.putExtra("data",String.valueOf(spinner1.getSelectedItem()));
                startActivity(intent);
            }

        });

        }

   
    private void Filechooser(){
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1 );
     }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode ==RESULT_OK && data!= null && data.getData()!=null){
            imguri = data.getData();
            imguri.setImageURI(imguri);
        }
        
    }


}**/
