package com.example.android.blood02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Needy extends AppCompatActivity {
    Spinner spinner6, spinner5;
    ArrayAdapter<String> adapter6, adapter5;
    public static String bloodGroup1;
   public static String add1;
    EditText name1;
    EditText age;
   public static String name0,age0;
    Button search;
//    public static DatabaseReference databaseReference5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);
        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");

//        databaseReference5= FirebaseDatabase.getInstance().getReference("Needy");

        spinner5 = (Spinner) findViewById(R.id.spin5);
        adapter5 = new ArrayAdapter<String>(Needy.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        spinner6 = (Spinner) findViewById(R.id.spin6);
        adapter6 = new ArrayAdapter<String>(Needy.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names2));
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodGroup1 = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                add1 = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        name1=(EditText)findViewById(R.id.Name9);
         age=(EditText)findViewById(R.id.age9);
          search=(Button)findViewById(R.id.search);



//        Toast.makeText(Needy.this,name0,Toast.LENGTH_SHORT).show();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name0=name1.getText().toString();
                age0=age.getText().toString();

                if((!TextUtils.isEmpty(name0))&&(!TextUtils.isEmpty(age0))&&(add1!=null)&&(bloodGroup1!=null)){


                name0=name1.getText().toString();
                age0=age.getText().toString();


                Intent q=new Intent(Needy.this,DonerList.class);
                startActivity(q);}
                else {

                    Toast.makeText(Needy.this,"Enter all the details",Toast.LENGTH_SHORT).show();


                }
//                Toast.makeText(Needy.this,name0,Toast.LENGTH_SHORT).show();
            }
        });

//        SharedPreferences sharedPreferences2=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("mobileno",phNO);
//        editor.apply();
//
//        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("mobileno",phN\O);
//        editor.apply();




    }
}
