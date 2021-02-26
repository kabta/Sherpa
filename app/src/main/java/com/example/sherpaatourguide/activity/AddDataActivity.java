package com.example.sherpaatourguide.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.MainActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddDataActivity extends AppCompatActivity {
    private String  pdescription, pname;
    private Spinner spinner;
    private Button addbtn;
    Datas datas;

    static final int Gallerypick =1;
    ImageView inputImage;
    private Uri Imageuri;
    private String downloadimageurl;
    EditText inputName, inputDescription;
    StorageReference mImageRef;
    DatabaseReference dbreff;
    String placename, placedescription, item;
    ProgressDialog loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        mImageRef = FirebaseStorage.getInstance().getReference("Image");
        dbreff = FirebaseDatabase.getInstance().getReference().child("Datas");

        inputName = findViewById(R.id.name);
        inputDescription = findViewById(R.id.placeDescription);
        inputImage = findViewById(R.id.placeImage);
        addbtn = findViewById(R.id.addBtn);
        loadingbar = new ProgressDialog(this);
        spinner = findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose Category");
        categories.add("Destinations");
        categories.add("Religious Places");
        categories.add("Bus Stations");
        categories.add("Restaurants and Hotels");
        categories.add("Shopping malls");

        //style and populate spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories);
        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               item = spinner.getSelectedItem().toString();
              // textView.setText(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        inputImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateData();
                SaveValue(item);
            }
        });
        dbreff.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    placename = snapshot.child("PlaceName").getValue().toString();
                    placedescription = snapshot.child("PlaceDescription").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void SaveValue(String item){
        if(item =="Choose Category"){
            Toast.makeText(this,"please select category",Toast.LENGTH_SHORT).show();
        }else{
            dbreff = FirebaseDatabase.getInstance().getReference().child("All Categories").child(item);

            datas.setCategory(item);
            String id = dbreff.push().getKey();
            dbreff.child(id).setValue(datas);
            Toast.makeText(this, "Category saved", Toast.LENGTH_SHORT).show();
        }
    }
    private void OpenGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png", "image/jpg"};
        galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(galleryIntent, Gallerypick);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)

            switch (requestCode)
            {
                case Gallerypick: Imageuri=data.getData();
                    Imageuri = data.getData();
                    inputImage.setImageURI(Imageuri);
                    break;
            }

    }
    private void ValidateData(){
        pdescription = inputDescription.getText().toString();
        pname = inputName.getText().toString();
        if(Imageuri == null){
            Toast.makeText(this,"Image Required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pname)){
            Toast.makeText(this, "Please provide Name of the place",Toast.LENGTH_SHORT).show();
        }
        else{
            storeInformation();
        }
    }

    private void storeInformation(){
        loadingbar.setTitle("Adding New Place");
        loadingbar.setMessage("Please wait: Place is being added");
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.show();




        final StorageReference filepath = mImageRef.child(Imageuri.getPathSegments()+".jpg");
        final UploadTask uploadTask = filepath.putFile(Imageuri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddDataActivity.this, "Error"+message, Toast.LENGTH_SHORT).show();
                loadingbar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddDataActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task <Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw  task.getException();
                        }
                        downloadimageurl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadimageurl = task.getResult().toString();
                            Toast.makeText(AddDataActivity.this, "Place image is saved to database successfully...", Toast.LENGTH_SHORT).show();
                            SaveInfoToDatabase();
                        }

                    }
                });
            }
            private  void SaveInfoToDatabase(){
                HashMap<String,Object> placeMap = new HashMap<>();

                placeMap.put("Name",pname);
                placeMap.put("Image",downloadimageurl);
                placeMap.put("Description", pdescription);
                String pid = dbreff.push().getKey();
                dbreff.child(pid).setValue(datas);


                dbreff.child(pid).updateChildren(placeMap)
                        .addOnCompleteListener(new OnCompleteListener <Void>() {
                            @Override
                            public void onComplete(@NonNull Task <Void> task) {
                                if (task.isSuccessful())
                                {
                                    Intent intent = new Intent(AddDataActivity.this, AddDataActivity.class);
                                    startActivity(intent);
                                    loadingbar.dismiss();
                                    Toast.makeText(AddDataActivity.this, "Place is added successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    loadingbar.dismiss();
                                    String message = task.getException().toString();
                                    Toast.makeText(AddDataActivity.this, "Error"+message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}



