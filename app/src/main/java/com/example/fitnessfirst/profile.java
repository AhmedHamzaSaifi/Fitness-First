package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profile extends AppCompatActivity {
ImageView profileimage;
EditText name;
Button setupprofile;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseStorage storage;
Uri selectedimage;
   // String email=getIntent().getStringExtra("email");
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data !=null){
            if (data.getData() !=null){
                profileimage.setImageURI(data.getData());
                selectedimage=data.getData();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileimage=(ImageView)findViewById(R.id.imageView_profile);
        name=(EditText)findViewById(R.id.editText_profilename);
        String username=name.getText().toString().trim();
        setupprofile=(Button)findViewById(R.id.button_setupprofile);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference();

        storage=FirebaseStorage.getInstance();
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });

        setupprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (username.isEmpty()){
                    name.setError("Please type a name");
                //return;
                }*/

                if (selectedimage !=null){
                    StorageReference reference=storage.getReference().child("Profiles").child(auth.getUid());
                    reference.putFile(selectedimage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                            String imageUrl=uri.toString();
                                            String UID=auth.getUid();
                                            //String phone=auth.getCurrentUser().getPhoneNumber();
                                        String phone=getIntent().getStringExtra("PhoneNumber");
                                            String profilename=name.getText().toString();
                                            Users user=new Users(UID,profilename,phone,imageUrl);
                                            database.getReference().child("Users").child(auth.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Intent intent=new Intent(profile.this,MainActivity.class);

                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                    }
                                });
                            }
                        }
                    });
                }else {
                    String UID=auth.getUid();
//                    String phone=auth.getCurrentUser().getPhoneNumber();
                    String profilename=name.getText().toString();
                    Users user=new Users(UID,profilename,"No Image Selected");
                    database.getReference().child("Users").child(auth.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent=new Intent(profile.this,MainActivity.class);

                            startActivity(intent);
                            finish();
                        }
                    });
                }

            }
        });

    }
}