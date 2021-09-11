package com.example.fitnessfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class idealweightresult extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idealweightresult);
        txt=(TextView) findViewById(R.id.textView_idealweightresult);
        String s1=getIntent().getStringExtra("key");
        txt.setText(s1+" KG");
        GradientDrawable magnitudeCircle = (GradientDrawable) txt.getBackground();
        int magnitudeColor = getcolor(Integer.parseInt(s1));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

    }
    private int getcolor(int mag){
        int color_id;
        if (mag<=30){
            color_id=R.color.magnitude1;
        }else if (mag>30&&mag<40){
            color_id=R.color.magnitude2;
        }else if (mag>40&&mag<50){
            color_id=R.color.magnitude3;
        }else if (mag>50&&mag<60){
            color_id=R.color.magnitude4;
        }else if (mag>60&&mag<70){
            color_id=R.color.magnitude5;
        }else if (mag>70&&mag<80){
            color_id=R.color.magnitude6;
        }else if (mag>80&&mag<90){
            color_id=R.color.magnitude7;
        }
        else if (mag>90&&mag<100){
            color_id=R.color.magnitude8;
        }
        else if (mag>100&&mag<110){
            color_id=R.color.magnitude9;
        }else{
            color_id=R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getApplicationContext(), color_id);
    }
}