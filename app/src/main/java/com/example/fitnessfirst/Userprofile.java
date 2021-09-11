package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class Userprofile extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    TextView name,phno;
    StorageReference storageReference;
    FirebaseAuth auth;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        getSupportActionBar().hide();
      //  imageView.setImageURI(null);
        //recyclerView=(RecyclerView)findViewById(R.id.recyclerView_image);
        imageView=(ImageView)findViewById(R.id.imageView_userdp);

        name=(TextView)findViewById(R.id.textView_userprofilename);
        phno=(TextView)findViewById(R.id.textView_userphnumber);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_profile:

                        return true;
                    case R.id.nav_chat:
                        startActivity(new Intent(getApplicationContext(),chatslist.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Users users1=snapshot1.getValue(Users.class);
                    if (users1.getUid().equals(FirebaseAuth.getInstance().getUid())){
                       // imageView.setImageURI(Uri.parse(users1.getProfileimage()));
                        //Glide.with(getApplicationContext()).load(users1.getProfileimage());
                        name.setText(users1.getName());
                        phno.setText(users1.getPhonenumber());
                        String imageuri=users1.getProfileimage();
                        Log.d("rt", "onDataChange: "+imageuri);
//                        Uri uri=Uri.parse(imageuri);
//
//
//                        imageView.setImageURI(uri);
                        Picasso.get().load(imageuri).into(imageView);


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}