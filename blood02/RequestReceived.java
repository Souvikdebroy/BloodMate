package com.example.android.blood02;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.blood02.AuthPhno.phNO;


public class RequestReceived extends AppCompatActivity {
   public static DatabaseReference databaseReference10,databaseReference11;
    public static String number,NAdd,NPhno,NName,NDob,NBlood,Rid;
    List<Person> personList1;

    public ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_received);

        listView1=(ListView)findViewById(R.id.list_view1);
        personList1=new ArrayList<>();

        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");

        databaseReference10=FirebaseDatabase.getInstance().getReference();
        databaseReference11=FirebaseDatabase.getInstance().getReference();



        Query query0=databaseReference10.child("Request").orderByChild("phNO").equalTo(phNO);
        query0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personList1.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Toast.makeText(RequestReceived.this,"hey", Toast.LENGTH_SHORT).show();
                    final Person person4=postSnapshot.getValue(Person.class);
                    NAdd=   person4.getNeedyAddress();
                    NDob=person4.getNeedyDob();
                    NName=person4.getNeedyName();
                    NPhno=person4.getNeedyNo();
                    NBlood=person4.getBloodgrp();
                    Rid=person4.getPersonid();
                    Person person9=new Person(Rid,NName,NAdd,NBlood,NDob,NPhno);
                    personList1.add(person9);
                    PersonName1RR personName1RR= new PersonName1RR(RequestReceived.this,personList1);
                    listView1.setAdapter(null);
                    listView1.setAdapter(personName1RR);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        Query query00=databaseReference11.child("Needy").orderByChild("phNO").equalTo(number);
//        query00.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    final Person person4=postSnapshot.getValue(Person.class);
//                    personList1.add(person4);
//                    PersonName1RR personName1RR= new PersonName1RR(RequestReceived.this,personList1);
//                    listView1.setAdapter(personName1RR);
//                    Toast.makeText(RequestReceived.this, "hey", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
