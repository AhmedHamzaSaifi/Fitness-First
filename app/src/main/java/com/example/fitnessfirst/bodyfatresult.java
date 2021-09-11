package com.example.fitnessfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class bodyfatresult extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyfatresult);
        txt=(TextView) findViewById(R.id.textView_bodyfatresult);
        String s1=getIntent().getStringExtra("key");
        txt.setText(s1+" %");
        GradientDrawable magnitudeCircle = (GradientDrawable) txt.getBackground();
        int magnitudeColor = getcolor(Integer.parseInt(s1));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

    }
    private int getcolor(int mag){
        int color_id;
        if (mag<=8){
            color_id=R.color.magnitude1;
        }else if (mag>8&&mag<10){
            color_id=R.color.magnitude2;
        }else if (mag>10&&mag<12){
            color_id=R.color.magnitude3;
        }else if (mag>12&&mag<15){
            color_id=R.color.magnitude4;
        }else if (mag>15&&mag<18){
            color_id=R.color.magnitude5;
        }else if (mag>18&&mag<22){
            color_id=R.color.magnitude6;
        }else if (mag>22&&mag<26){
            color_id=R.color.magnitude7;
        }
        else if (mag>26&&mag<28){
            color_id=R.color.magnitude8;
        }
        else if (mag>28&&mag<30){
            color_id=R.color.magnitude9;
        }else{
            color_id=R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getApplicationContext(), color_id);
    }
}