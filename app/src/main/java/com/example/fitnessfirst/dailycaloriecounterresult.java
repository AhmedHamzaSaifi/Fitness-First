package com.example.fitnessfirst;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class dailycaloriecounterresult extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailycaloriecounterresult);
        txt=(TextView) findViewById(R.id.textView_dailycaloriecounterresult);
        String s1=getIntent().getStringExtra("key");
        txt.setText(s1+" CALORIES");
        GradientDrawable magnitudeCircle = (GradientDrawable) txt.getBackground();
        int magnitudeColor = getcolor(Integer.parseInt(s1));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

    }
    private int getcolor(int mag){
        int color_id;
        if (mag<=1400){
           color_id=R.color.magnitude1;
        }else if (mag>1400&&mag<1600){
            color_id=R.color.magnitude2;
        }else if (mag>1600&&mag<1800){
            color_id=R.color.magnitude3;
        }else if (mag>1800&&mag<2000){
            color_id=R.color.magnitude4;
        }else if (mag>2000&&mag<2200){
            color_id=R.color.magnitude5;
        }else if (mag>2200&&mag<2400){
            color_id=R.color.magnitude6;
        }else if (mag>2400&&mag<2600){
            color_id=R.color.magnitude7;
        }
        else if (mag>2600&&mag<2800){
            color_id=R.color.magnitude8;
        }
        else if (mag>2800&&mag<3000){
            color_id=R.color.magnitude9;
        }else{
            color_id=R.color.magnitude10plus;
        }
         return ContextCompat.getColor(getApplicationContext(), color_id);
    }
    }
