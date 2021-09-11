package com.example.fitnessfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class bodyFatPercentage extends AppCompatActivity {


    Spinner sp1, sp2,sp3,sp4;

    EditText ed1,ed2,ed3;
    RadioGroup radioGroup1,radioGroup2;
    RadioButton gender;
    Button calculate;

    float height=0;
    float weight=0;
    float neck=0;
    float waist=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_fat_percentage);
        getSupportActionBar().hide();
        sp1 = (Spinner) findViewById(R.id.spinner_height);
        String[] height_units = getResources().getStringArray(R.array.heightunits);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, height_units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //ed2=(EditText) findViewById(R.id.editText_height);
                //String xy=ed2.getText().toString();
                Log.d("pos", "onItemSelected: "+position);
                if (parent.getItemAtPosition(position).equals("inches")){
                    //height=0;
                    //Log.d("xy", "onItemSelected: "+xy);
                    ed3=(EditText) findViewById(R.id.editText_height);
                    String xy=ed3.getText().toString();
                    height=inchestometers(xy);
                    Log.d("xxx", "onItemSelected: "+height);
                    //inchestocm(ed1.getText().toString());

                    //Toast.makeText(parent.getContext(),"hhh"+ed1.getText(),Toast.LENGTH_LONG).show();
                    //Intent intent=new Intent(dailyCalorieCounter.this,MainActivity2.class);
                    // intent.putExtra("key",valuefromspinner);
                    //startActivity(intent);

                }
                else
                if (parent.getItemAtPosition(position).equals("cm")){
                    // height=0;
                    ed3=(EditText) findViewById(R.id.editText_height);
                    String xy=ed3.getText().toString();
                    Log.d("xy", "onItemSelected: "+xy);
                    height=cmtometers(xy);
                    Log.d("xxyx", "onItemSelected: "+height);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp2 = (Spinner) findViewById(R.id.spinner_weight);
        String[] weight_units = getResources().getStringArray(R.array.weightunits);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weight_units);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //ed3=(EditText) findViewById(R.id.editText_weight);
                //String xyz=ed3.getText().toString();
                if (parent.getItemAtPosition(position).equals("Kg")){
                    //weight=0;
                    ed3=(EditText) findViewById(R.id.editText_weight);
                    String xyz=ed3.getText().toString();
                    Log.d("jee", "onItemSelected: "+xyz);
                    weight=returntokg(xyz);
                }
                if (parent.getItemAtPosition(position).equals("pounds")){
                    // weight=0;
                    ed3=(EditText) findViewById(R.id.editText_weight);
                    String xyz=ed3.getText().toString();
                    weight=poundstokg(xyz);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp3=(Spinner) findViewById(R.id.spinner_neck);
        String[] neck_units=getResources().getStringArray(R.array.neckunits);
        ArrayAdapter adapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,neck_units);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter2);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("inches")){
                    //height=0;
                    //Log.d("xy", "onItemSelected: "+xy);
                    ed3=(EditText) findViewById(R.id.editText_neck);
                    String xy=ed3.getText().toString();
                    neck=inchestocm(xy);
                    Log.d("xxx", "onItemSelected: "+height);
                    //inchestocm(ed1.getText().toString());

                    //Toast.makeText(parent.getContext(),"hhh"+ed1.getText(),Toast.LENGTH_LONG).show();
                    //Intent intent=new Intent(dailyCalorieCounter.this,MainActivity2.class);
                    // intent.putExtra("key",valuefromspinner);
                    //startActivity(intent);

                }
                else
                if (parent.getItemAtPosition(position).equals("cm")){
                    // height=0;
                    ed3=(EditText) findViewById(R.id.editText_neck);
                    String xy=ed3.getText().toString();
                    Log.d("xy", "onItemSelected: "+xy);
                    neck=returntocm(xy);
                    Log.d("xxyx", "onItemSelected: "+height);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp4=(Spinner) findViewById(R.id.spinner_waist);
        String[] waist_units=getResources().getStringArray(R.array.waistunits);
        ArrayAdapter  adapter3=new ArrayAdapter(this, android.R.layout.simple_spinner_item,waist_units);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adapter3);
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("inches")){
                    //height=0;
                    //Log.d("xy", "onItemSelected: "+xy);
                    ed3=(EditText) findViewById(R.id.editText_waist);
                    String xy=ed3.getText().toString();
                    waist=inchestocm(xy);
                    Log.d("xxx", "onItemSelected: "+height);
                    //inchestocm(ed1.getText().toString());

                    //Toast.makeText(parent.getContext(),"hhh"+ed1.getText(),Toast.LENGTH_LONG).show();
                    //Intent intent=new Intent(dailyCalorieCounter.this,MainActivity2.class);
                    // intent.putExtra("key",valuefromspinner);
                    //startActivity(intent);

                }
                else
                if (parent.getItemAtPosition(position).equals("cm")){
                    // height=0;
                    ed3=(EditText) findViewById(R.id.editText_waist);
                    String xy=ed3.getText().toString();
                    Log.d("xy", "onItemSelected: "+xy);
                    waist=returntocm(xy);
                    Log.d("xxyx", "onItemSelected: "+height);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGroup1=(RadioGroup) findViewById(R.id.radiogroup_gender);
        calculate=(Button) findViewById(R.id.calculatebodyfat);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("jkl", "onClick: "+a);
                int checked=radioGroup1.getCheckedRadioButtonId();
                if (checked==-1){

                }else {
                    findradiobutton(checked);
                }
            }

            private void findradiobutton(int checked) {
                switch (checked){
                    case R.id.radiobutton_male:
                        ed1=(EditText) findViewById(R.id.editText_age);
                        String q=ed1.getText().toString();
                        //ed2=(EditText) findViewById(R.id.editText_height);
                        //String w=ed2.getText().toString();


                        // ed3=(EditText) findViewById(R.id.editText_weight);
                        //String e=ed3.getText().toString();

                        float shift= calculatemale(String.valueOf(weight), String.valueOf(height),q);
                        // calc.calculate(e,w,q);
                        int send=(int) shift;
                        String move=String.valueOf(send);
                        // Log.d("calcs", "findradiobutton: "+shift);
                        Intent intent=new Intent(bodyFatPercentage.this, bodyfatresult.class);
                        intent.putExtra("key",move);
                        startActivity(intent);
                        break;
                    case R.id.radiobutton_female:
                        //calculatefemale(e,w,q);
                        ed1=(EditText) findViewById(R.id.editText_age);
                        q = ed1.getText().toString();
                        //ed2=(EditText) findViewById(R.id.editText_height);
                        //w=ed2.getText().toString();


                        //ed3=(EditText) findViewById(R.id.editText_weight);
                        //e=ed3.getText().toString();
                        shift=calculatefemale(String.valueOf(weight), String.valueOf(height),q);
                        // calc.calculate(e,w,q);
                        // Log.d("calcs", "findradiobutton: "+shift);
                        send=(int) shift;
                        move=String.valueOf(send);
                        intent=new Intent(bodyFatPercentage.this, bodyfatresult.class);
                        intent.putExtra("key",move);
                        startActivity(intent);

                        break;

                }
            }
        });


    }




    public float returntocm(String s)
    {
        float x=0;
        try {
            x=Float.parseFloat(s);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        return x;
    }

    public float inchestocm(String t)
    {
        float y=Float.parseFloat(t);
        float z= (float) (y*2.54);
        return z;
    }
    public float cmtometers(String m){
        float x=Float.parseFloat(m);
        float y= (float) (x*0.01);
        return y;
    }
    public float inchestometers(String m){
        float x=Float.parseFloat(m);
        float y= (float) (x*0.0254);
        return y;
    }


    public float returntokg(String x){
        float y=0;
        try {
            y=Float.parseFloat(x);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return y;
    }

    public float poundstokg(String z){
        float w=Float.parseFloat(z);
        float q= (float) (w*0.45);
        return q;
    }


    public float calculatemale(String w, String h, String ag)
    {
        float x=Float.parseFloat(w);
        float z=Float.parseFloat(ag);
        float y=Float.parseFloat(h);
        float y1=y*y;
        float y2=x/y1;
        float y3= (float) (y2*1.20);
        float y4= (float) (0.23*z);
        float y5=y3+y4;
        Log.d("WWX", "calculatemale: "+y3);
        Log.d("WWX", "calculatemale: "+y4);
        Log.d("WWX", "calculatemale: "+y5);

        float result= (float) (y5-16.2);

        Log.d("WW", "calculatemale: "+result);
        return result;
    }


    public float calculatefemale(String w, String h, String ag)
    {
        float x=Float.parseFloat(w);
        float y=Float.parseFloat(h);
        float z=Float.parseFloat(ag);
        float y1=y*y;
        float y2=x/y1;
        float y3= (float) (y2*1.20);
        float y4= (float) (0.23*z);
        float y5=y3+y4;
        float result= (float) (y5-5.4);
        return result;
    }

}