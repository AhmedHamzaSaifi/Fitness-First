package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatslist extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseDatabase database;
DatabaseReference reference;
ArrayList<Users> users;
UsersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatslist);
        getSupportActionBar().hide();
        //reference=FirebaseDatabase.getInstance().getReference();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        users=new ArrayList<>();
        adapter=new UsersAdapter(this,users);
        recyclerView.setAdapter(adapter);
        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Users users1=snapshot1.getValue(Users.class);
                    if (!users1.getUid().equals(FirebaseAuth.getInstance().getUid()))
                    users.add(users1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_chat);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),Userprofile.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.nav_chat:

                        return true;
                }
                return false;
            }
        });

    }
}