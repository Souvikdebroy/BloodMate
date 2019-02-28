package com.example.android.blood02;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.android.blood02.AuthPhno.phNO;

public class Donate extends AppCompatActivity {
     public static String From,To;
    EditText edt;
    public static DatabaseReference databasereference3,databasereference4,databasereference5;
    Spinner spinner3,spinner4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
//       edt=(EditText)findViewById(R.id.edt);
        ArrayAdapter<String> adapter3, adapter4;


        databasereference3 = FirebaseDatabase.getInstance().getReference();
        databasereference4 = FirebaseDatabase.getInstance().getReference("Donors");
        databasereference5 = FirebaseDatabase.getInstance().getReference("Donors");

        SharedPreferences sharedPreferences = getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2 = sharedPreferences.getString("mobileno", "");

        spinner3 = (Spinner) findViewById(R.id.spinner1);
        adapter3 = new ArrayAdapter<String>(Donate.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names3));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4 = (Spinner) findViewById(R.id.spinner2);

        adapter4 = new ArrayAdapter<String>(Donate.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names4));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                From = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                To = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            Query query = databasereference3.child("Accounts").orderByChild("phNO").equalTo(phNO);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Person person1 = postSnapshot.getValue(Person.class);


                    Button btn3 = (Button) findViewById(R.id.btn3);
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if ((From!= null)&&(To!= null)){

                            String id = databasereference4.push().getKey();
                            databasereference4.child(id).setValue(person1);

                            Map<String, Object> userTimeUpdates = new HashMap<String, Object>();
                            userTimeUpdates.put("Timing", From + "to" + To + "hrs");

                            databasereference5.child(id).updateChildren(userTimeUpdates);
                            Toast.makeText(Donate.this, "Donation Registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Donate.this,"Enter all the details",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
