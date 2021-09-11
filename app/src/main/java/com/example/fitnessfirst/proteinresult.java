package com.example.fitnessfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class proteinresult extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proteinresult);
        txt=(TextView) findViewById(R.id.textView_proteinresult);
        String s1=getIntent().getStringExtra("key");
        Log.d("AAA", "onCreate: "+s1);
        txt.setText(s1+" grams");
        GradientDrawable magnitudeCircle = (GradientDrawable) txt.getBackground();
        int magnitudeColor = getcolor(Integer.parseInt(s1));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

    }
    private int getcolor(int mag){
        int color_id;
        if (mag<=80){
            color_id=R.color.magnitude1;
        }else if (mag>80&&mag<90){
            color_id=R.color.magnitude2;
        }else if (mag>90&&mag<100){
            color_id=R.color.magnitude3;
        }else if (mag>100&&mag<110){
            color_id=R.color.magnitude4;
        }else if (mag>110&&mag<120){
            color_id=R.color.magnitude5;
        }else if (mag>120&&mag<140){
            color_id=R.color.magnitude6;
        }else if (mag>140&&mag<160){
            color_id=R.color.magnitude7;
        }
        else if (mag>160&&mag<180){
            color_id=R.color.magnitude8;
        }
        else if (mag>180&&mag<200){
            color_id=R.color.magnitude9;
        }else{
            color_id=R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getApplicationContext(), color_id);
    }
}