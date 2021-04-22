package com.example.sherpaatourguide.activity.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherpaatourguide.AdminDashboardActivity;
import com.example.sherpaatourguide.BusStationData;
import com.example.sherpaatourguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ShoppingActivity extends AppCompatActivity {
    private EditText name, description, location, phn ;
    private Button addbtn;
    private TextView back;
    private ImageView image;
    private Uri imageUri;
    private DatabaseReference dbreff;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        name = (EditText)findViewById(R.id.shoppingName);
        description = (EditText)findViewById(R.id.shoppingDescription);
        location= (EditText)findViewById(R.id.shoppingLocationdetails);
        phn = (EditText)findViewById(R.id.shoppingPhonenumber);
        addbtn = (Button)findViewById(R.id.shoppingaddBtn);
        back =  (TextView) findViewById(R.id.shoppingbackTV);
        image =(ImageView)findViewById(R.id.shoppingImage);
        storageReference = FirebaseStorage.getInstance().getReference("ShoppingMalls");
        dbreff= FirebaseDatabase.getInstance().getReference().child("ShoppingMalls");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    uploadPicture();

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminDashboardIntent = new Intent(ShoppingActivity.this, AdminDashboardActivity.class);
                startActivity(adminDashboardIntent);

            }
        });
    }
    protected  Boolean validateData(){
        Boolean isALLOK = true;
        String sname = name.getText().toString();
        String sdescription = description.getText().toString();
        String slocation = location.getText().toString();
        String sphone = phn.getText().toString();
        if(sname.isEmpty()  && sdescription.isEmpty() && slocation.isEmpty() && sphone.isEmpty()){
            name.setError("Please fill this area");
            description.setError("Please fill this area");
            location.setError("Please fill this area");
            phn.setError("Please fill this area");

            Toast.makeText(ShoppingActivity.this, "All fields must not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(sname.isEmpty()){
            name.setError("Please fill this area");
            return false;
        }
        else if(sdescription.isEmpty()){
            description.setError("Please fill this area");
            return false;
        } else if(slocation.isEmpty()){
            location.setError("Please fill this area");
            return false;
        } else if(sphone.isEmpty()){
            phn.setError("Please fill this area");
            return false;
        }
        return isALLOK;
    }



    protected void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== RESULT_OK && data!=null && data.getData()!= null)
        {
            imageUri = data.getData();
            image.setImageURI(imageUri);

        }

    }
    protected String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    private void uploadPicture() {
        if(imageUri != null){
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Uploading Information...");
            pd.show();
            StorageReference imgRef = storageReference.child("ShoppingMalls/ShoppingMall"+ System.currentTimeMillis()+"."+ GetFileExtension(imageUri));

            imgRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            pd.dismiss();
                            final String[] img = new String[1];
                            imgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String    url = task.getResult().toString();
                                    String sname = name.getText().toString();
                                    String sdescription = description.getText().toString();
                                    String slocation = location.getText().toString();
                                    String sphone = phn.getText().toString();
                                    ShoppingMallsData sdata = new ShoppingMallsData(sname,sdescription,slocation,sphone, url);
                                    String id = dbreff.push().getKey();
                                    dbreff.child(id).setValue(sdata);

                                }


                                                                          });
                            Snackbar.make(findViewById(android.R.id.content), "Data Uploaded", Snackbar.LENGTH_LONG).show();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed to Upload image", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progressPercent = (100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            pd.setMessage("Progress: "+(int) progressPercent+"%");
                        }
                    });
        }





    }
}