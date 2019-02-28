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

import static com.example.android.blood02.AuthPhno.phNO;

public class RequestSend extends AppCompatActivity {

    public static DatabaseReference databaseReference12;
    List<Person> personList2;
    public ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_send);

        listView2=(ListView)findViewById(R.id.list_view2);
        personList2=new ArrayList<>();


        databaseReference12= FirebaseDatabase.getInstance().getReference();

        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");

        Query query03=databaseReference12.child("Request").orderByChild("needyNo").equalTo(phNO);
        query03.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Person person6=postSnapshot.getValue(Person.class);
                    personList2.add(person6);
                    PersonName1RS personName2=new PersonName1RS(RequestSend.this,personList2);
                    listView2.setAdapter(personName2);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
