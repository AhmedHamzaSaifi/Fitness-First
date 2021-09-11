package com.example.fitnessfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dietCharts extends AppCompatActivity {
CardView cdwl,cdwg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_charts);
        getSupportActionBar().hide();
        cdwl=(CardView)findViewById(R.id.cdwl);
        cdwg=(CardView)findViewById(R.id.cdwg);

        cdwl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietCharts.this,WeightLossCharts.class));
            }
        });

        cdwg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietCharts.this,WeightGainCharts.class));
            }
        });

    }
}