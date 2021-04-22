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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class BusStationActivity extends AppCompatActivity {
    private EditText bsname, bsdescription, bslocation, bsphn ;
    private Button bsaddbtn;
    private TextView busback;
    private ImageView bsimage;
    private Uri imageUri;
    private DatabaseReference dbreff;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_station);
        bsname = (EditText)findViewById(R.id.busstationName);
        bsdescription = (EditText)findViewById(R.id.busstationDescription);
        bslocation= (EditText)findViewById(R.id.busstationLocationdetails);
        bsphn = (EditText)findViewById(R.id.busstationPhonenumber);
        bsaddbtn = (Button)findViewById(R.id.busaddBtn);
        busback =  (TextView) findViewById(R.id.busbackTV);
        bsimage =(ImageView)findViewById(R.id.busstationImage);
        storageReference = FirebaseStorage.getInstance().getReference("BusStation");
        dbreff= FirebaseDatabase.getInstance().getReference().child("BusStationData");

        bsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


        dbreff = FirebaseDatabase.getInstance().getReference().child("BusStationData");

        bsaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    uploadPicture();

                }
            }
        });
        busback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminDashboardIntent = new Intent(BusStationActivity.this, AdminDashboardActivity.class);
                startActivity(adminDashboardIntent);

            }
        });

    }
    protected  Boolean validateData(){
        Boolean isALLOK = true;
        String name = bsname.getText().toString();
        String description = bsdescription.getText().toString();
        String location = bslocation.getText().toString();
        String phone = bsphn.getText().toString();
        if(name.isEmpty()  && description.isEmpty() && location.isEmpty() && phone.isEmpty()){
            bsname.setError("Please fill this area");
            bsdescription.setError("Please fill this area");
            bslocation.setError("Please fill this area");
            bsphn.setError("Please fill this area");

            Toast.makeText(BusStationActivity.this, "All fields must not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(name.isEmpty()){
            bsname.setError("Please fill this area");
            return false;
        }
        else if(description.isEmpty()){
            bsdescription.setError("Please fill this area");
            return false;
        } else if(location.isEmpty()){
            bslocation.setError("Please fill this area");
            return false;
        } else if(phone.isEmpty()){
            bsphn.setError("Please fill this area");
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
            bsimage.setImageURI(imageUri);

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
            StorageReference imgRef = storageReference.child("BusStation/BusStation"+ System.currentTimeMillis()+"."+ GetFileExtension(imageUri));

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

                               String name = bsname.getText().toString();
                               String description = bsdescription.getText().toString();
                               String location = bslocation.getText().toString();
                               String phone = bsphn.getText().toString();

                               BusStationData bsdata = new BusStationData(name,description,location,phone,url );
                               String id = dbreff.push().getKey();
                               dbreff.child(id).setValue(bsdata);

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
                    })
            .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                  task  taskif(task.isSuccessful())
//                    Uri downloadUri =task.getResult().getStorage().getDownloadUrl().toString();
                }
            })
            ;

        }





    }
}