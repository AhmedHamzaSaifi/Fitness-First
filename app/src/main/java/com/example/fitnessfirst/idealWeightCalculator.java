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
import android.widget.RadioGroup;
import android.widget.Spinner;

public class idealWeightCalculator extends AppCompatActivity {
Spinner sp1;
    float height=0;
    EditText ed1,ed2,ed3;
    Button calculate;
    RadioGroup radioGroup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight_calculator);
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
                    height=inchestocm(xy);
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
                    height=returntocm(xy);
                    Log.d("xxyx", "onItemSelected: "+height);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup1=(RadioGroup) findViewById(R.id.radiogroup_gender);
        calculate=(Button) findViewById(R.id.calculateidealweight);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("jkl", "onClick: "+a);
                int checked=radioGroup1.getCheckedRadioButtonId();
                if (checked==-1){

                }else {
                    findradiobutton(checked);
                }
            }

            private void findradiobutton(int checked) {
                switch (checked){
                    case R.id.radiobutton_male:
                       // ed1=(EditText) findViewById(R.id.editText_age);
                       // String q=ed1.getText().toString();
                        //ed2=(EditText) findViewById(R.id.editText_height);
                        //String w=ed2.getText().toString();


                        // ed3=(EditText) findViewById(R.id.editText_weight);
                        //String e=ed3.getText().toString();

                        float shift= calculatemale( String.valueOf(height));
                        // calc.calculate(e,w,q);
                        int send=(int) shift;
                        String move=String.valueOf(send);
                        // Log.d("calcs", "findradiobutton: "+shift);
                        Intent intent=new Intent(idealWeightCalculator.this, idealweightresult.class);
                        intent.putExtra("key",move);
                        startActivity(intent);
                        break;
                    case R.id.radiobutton_female:
                        //calculatefemale(e,w,q);
                       // ed1=(EditText) findViewById(R.id.editText_age);
                       // q = ed1.getText().toString();
                        //ed2=(EditText) findViewById(R.id.editText_height);
                        //w=ed2.getText().toString();


                        //ed3=(EditText) findViewById(R.id.editText_weight);
                        //e=ed3.getText().toString();
                        shift=calculatefemale( String.valueOf(height));
                        // calc.calculate(e,w,q);
                        // Log.d("calcs", "findradiobutton: "+shift);
                        send=(int) shift;
                        move=String.valueOf(send);
                        intent=new Intent(idealWeightCalculator.this, idealweightresult.class);
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
    public float calculatemale( String h)
    {
       // float x=Float.parseFloat(w);
        float y=Float.parseFloat(h);
        //float z=Float.parseFloat(ag);
        float result= (float) (50+(0.91*(y-152.4)));
        return result;
    }


    public float calculatefemale( String h)
    {
       // float x=Float.parseFloat(w);
        float y=Float.parseFloat(h);
        //float z=Float.parseFloat(ag);
        float result= (float) (45.5+(0.91*(y-152.4)));
        return result;
    }
}