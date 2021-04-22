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

public class RestaurantActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_restaurant);

        name = (EditText)findViewById(R.id.restaurantName);
        description = (EditText)findViewById(R.id.restaurantDescription);
        location= (EditText)findViewById(R.id.restaurantLocationdetails);
        phn = (EditText)findViewById(R.id.restaurantPhonenumber);
        addbtn = (Button)findViewById(R.id.restaurantaddBtn);
        back =  (TextView) findViewById(R.id.restaurantbackTV);
        image =(ImageView)findViewById(R.id.restaurantImage);
        storageReference = FirebaseStorage.getInstance().getReference("Restaurant");
        dbreff= FirebaseDatabase.getInstance().getReference().child("Restaurant");
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
                Intent adminDashboardIntent = new Intent(RestaurantActivity.this, AdminDashboardActivity.class);
                startActivity(adminDashboardIntent);

            }
        });
    }
    protected  Boolean validateData(){
        Boolean isALLOK = true;
        String resname = name.getText().toString();
        String resdescription = description.getText().toString();
        String reslocation = location.getText().toString();
        String resphone = phn.getText().toString();
        if(resname.isEmpty()  && resdescription.isEmpty() && reslocation.isEmpty() && resphone.isEmpty()){
            name.setError("Please fill this area");
            description.setError("Please fill this area");
            location.setError("Please fill this area");
            phn.setError("Please fill this area");

            Toast.makeText(RestaurantActivity.this, "All fields must not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(resname.isEmpty()){
            name.setError("Please fill this area");
            return false;
        }
        else if(resdescription.isEmpty()){
            description.setError("Please fill this area");
            return false;
        } else if(reslocation.isEmpty()){
            location.setError("Please fill this area");
            return false;
        } else if(resphone.isEmpty()){
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
            StorageReference imgRef = storageReference.child("Restaurants/Restaurant"+ System.currentTimeMillis()+"."+ GetFileExtension(imageUri));

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

                                    String resname = name.getText().toString();
                                    String resdescription = description.getText().toString();
                                    String reslocation = location.getText().toString();
                                    String resphone = phn.getText().toString();
                                    RestaurantData resdata = new RestaurantData(resname,resdescription,reslocation,resphone, url);
                                    String id = dbreff.push().getKey();
                                    dbreff.child(id).setValue(resdata);
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