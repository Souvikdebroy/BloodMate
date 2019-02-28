package com.example.android.blood02;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.blood02.Needy.bloodGroup1;

public class DonerList extends AppCompatActivity {
    List<Person> personList;
    public ListView listView;
   public  static String bldgroup;

    String data,id_,name,add,bloodGroup,DOB,phNO;
    public static DatabaseReference databaseReference,databaseReference6,databaseReference7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner_list);

        listView=(ListView)findViewById(R.id.list_view);
        personList=new ArrayList<>();

        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");



        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference6= FirebaseDatabase.getInstance().getReference();
//        databaseReference7= FirebaseDatabase.getInstance().getReference();


//        Query myTopPostsQuery = databaseReference.child("Accounts").orderByChild("phNO").equalTo(id2);
//        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    final Person person2=postSnapshot.getValue(Person.class);
//                       bldgroup=  person2.getBloodgrp();
////                    Toast.makeText(DonerList.this, "one", Toast.LENGTH_SHORT).show();
////                    Log.d("DonerList","heya");
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        Query query3=databaseReference6.child("Donors").orderByChild("bloodgrp").equalTo(bloodGroup1);
        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

//                    Toast.makeText(DonerList.this, "two", Toast.LENGTH_SHORT).show();
                    final Person person3=postSnapshot.getValue(Person.class);
                    personList.add(person3);
                    PersonName personName=new PersonName(DonerList.this,personList);
                    listView.setAdapter(personName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
