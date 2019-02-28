package com.example.android.blood02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.example.android.blood02.AuthPhno.phNO;

public class Signup extends AppCompatActivity {
    Spinner spinner,spinner2;
    ArrayAdapter<String> adapter,adapter2;
   public static EditText etName,etNumber,etDOB,password,email;
   public static String bloodGroup,add,name,DOB,pass,em;
    Button bVerify;
    public static DatabaseReference databaseReference9,databaseReference1;
    FirebaseAuth auth1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseReference1=FirebaseDatabase.getInstance().getReference("Cities");
        databaseReference9= FirebaseDatabase.getInstance().getReference("Accounts");

       auth1=FirebaseAuth.getInstance();
        Query query5=databaseReference1;

        spinner=(Spinner)findViewById(R.id.sGroups);
        adapter=new ArrayAdapter<String>(Signup.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2=(Spinner)findViewById(R.id.sLocality);
        adapter2=new ArrayAdapter<String>(Signup.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names2));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodGroup = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                add = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        etName =(EditText)findViewById(R.id.txt1);
        etDOB =(EditText)findViewById(R.id.txt2);
        bVerify=(Button)findViewById(R.id.bVerify);
        etNumber=(EditText)findViewById(R.id.txt3);
        email=(EditText)findViewById(R.id.txt4);
        password=(EditText)findViewById(R.id.txt5);



        bVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 pass=password.getText().toString();
                 em=email.getText().toString();
                Toast.makeText(Signup.this,em,Toast.LENGTH_SHORT).show();

                        auth1.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Signup.this, "Authentication failed" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });

                addPerson();

            }
        });
    }



    private void addPerson(){
        name = etName.getText().toString();
        DOB = etDOB.getText().toString();

          setphNO();
        if((!TextUtils.isEmpty(name))&&(!TextUtils.isEmpty(DOB))&&(!TextUtils.isEmpty(phNO))&&((phNO.length()) ==10)&&(!TextUtils.isEmpty(add))&&(!TextUtils.isEmpty(bloodGroup))&&(bloodGroup!=null)&&(add!=null) ){

            String id= databaseReference9.push().getKey();
            Person person=new Person(id,name,add,bloodGroup,DOB,phNO,em);
            databaseReference9.child(id).setValue(person);

////           String id2=databaseReference1.push().getKey();
//            Person person1=new Person(id,name,add,bloodGroup,DOB,phNO);
//            databaseReference1.child(id).setValue(person1);
            Toast.makeText(Signup.this,"Addded Person",Toast.LENGTH_SHORT).show();


            Intent j=new Intent(Signup.this,MainActivity2.class);
            startActivity(j);


        }else{
            Toast.makeText(Signup.this,name,Toast.LENGTH_SHORT).show();
            Toast.makeText(Signup.this,"Enter all the details",Toast.LENGTH_SHORT).show();
//            if(phNO.length() < 10){
//                Toast.makeText(getBaseContext(), "Invalid Phone Number Entered", Toast.LENGTH_SHORT).show();
//            }
        }
    }


    void setphNO(){
        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("mobileno",phNO);
        editor.apply();
    }
}
