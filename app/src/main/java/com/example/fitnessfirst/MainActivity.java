package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class  MainActivity extends AppCompatActivity {
    CardView cd1,cd2,cd3,cd4,cd5,cd6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        cd1=(CardView) findViewById(R.id.cardview_1);
        cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,dailyCalorieCounter.class);
                startActivity(intent);
            }
        });

        cd2=(CardView) findViewById(R.id.cardview_2);
        cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,basicMetabolicRate.class);
                startActivity(intent);
            }
        });

        cd3=(CardView) findViewById(R.id.cardview_3);
        cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,idealWeightCalculator.class);
                startActivity(intent);
            }
        });

        cd4=(CardView) findViewById(R.id.cardview_4);
        cd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,bodyFatPercentage.class);
                startActivity(intent);
            }
        });

        cd5=(CardView) findViewById(R.id.cardview_5);
        cd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,dailyProteinIntake.class);
                startActivity(intent);
            }
        });

        cd6=(CardView) findViewById(R.id.cardview_6);
        cd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,dietCharts.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        return  true;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),Userprofile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_chat:
                        startActivity(new Intent(getApplicationContext(),chatslist.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}