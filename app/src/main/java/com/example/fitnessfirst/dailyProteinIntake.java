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
import android.widget.Spinner;

public class dailyProteinIntake extends AppCompatActivity {

Spinner sp2;
    float weight=0;
    EditText ed3;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_protein_intake);
        getSupportActionBar().hide();
        sp2 = (Spinner) findViewById(R.id.spinner_weight);
        String[] weight_units = getResources().getStringArray(R.array.weightunits);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weight_units);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Kg")){
                    //weight=0;
                    ed3=(EditText) findViewById(R.id.editText_height);
                    String xyz=ed3.getText().toString();
                    Log.d("jee", "onItemSelected: "+xyz);
                    weight=returntokg(xyz);
                }
                if (parent.getItemAtPosition(position).equals("pounds")){
                    // weight=0;
                    ed3=(EditText) findViewById(R.id.editText_height);
                    String xyz=ed3.getText().toString();
                    weight=poundstokg(xyz);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculate=(Button)findViewById(R.id.calculatedailyprotein);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed3=(EditText) findViewById(R.id.editText_height);
                String xyz=ed3.getText().toString();
                float shift=protein(String.valueOf(weight));
                int send=(int) shift;
                String move=String.valueOf(send);
                // Log.d("calcs", "findradiobutton: "+shift);
                Intent intent=new Intent(dailyProteinIntake.this, proteinresult.class);
                intent.putExtra("key",move);
                startActivity(intent);

            }
        });
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
    public float protein(String s){
        float x=Float.parseFloat(s);
        float z= (float) (x*1.9);
        return z;
    }
}