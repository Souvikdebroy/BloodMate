package com.example.android.blood02;

/**
 * Created by Souvik on 19-08-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.android.blood02.Needy.add1;
import static com.example.android.blood02.Needy.age0;
import static com.example.android.blood02.Needy.name0;
import static com.example.android.blood02.AuthPhno.phNO;

/**
 * Created by Souvik on 06-08-2017.
 */

public class PersonName extends ArrayAdapter<Person> {
    private Activity context;
   public static String Add,Name,Blood,Dob,Phno,id,Idd;
    public static DatabaseReference databaseReference8,databaseReference;
    private List<Person> persons;


    public PersonName (Activity context,List<Person> persons){
        super(context,R.layout.list_item,persons);
        this.context=context;
        this.persons=persons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        final View listItemView = layoutInflater.inflate(R.layout.list_item,null,true);

        SharedPreferences sharedPreferences=context.getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");

        final TextView textViewName=(TextView)listItemView.findViewById(R.id.lgName);
        final TextView textViewAdd=(TextView)listItemView.findViewById(R.id.lgAdd);
        final TextView textViewBlood=(TextView)listItemView.findViewById(R.id.lgBlood);
        final TextView textViewDob=(TextView)listItemView.findViewById(R.id.lgDob);
//        final TextView textViewId =(TextView)listItemView.findViewById(R.id.lgId);
        final   TextView textViewPhno=(TextView)listItemView.findViewById(R.id.lgPhno);
        final TextView textViewVisible=(TextView)listItemView.findViewById(R.id.visible);
//        final TextView hey=(TextView)listItemView.findViewById(R.id.hey);
        final Button  requestbtn=(Button)listItemView.findViewById(R.id.requestbtn);


        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        final Boolean approve=false;



        final Person person=persons.get(position);
        textViewName.setText(person.getName());
        textViewAdd.setText(person.getAddress());
        textViewBlood.setText(person.getBloodgrp());
        textViewDob.setText(person.getDob());
        textViewPhno.setText(person.getPhNO());
//        textViewId.setText(person.getPersonid());


        requestbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                databaseReference8= FirebaseDatabase.getInstance().getReference("Request");
                 id=databaseReference8.push().getKey();
                Person person1=new Person(id,person.getName(),person.getAddress(),person.getBloodgrp(),person.getDob(),person.getPhNO(),phNO,currentDateTimeString,approve);
                databaseReference8.child(id).setValue(person1);

                databaseReference= FirebaseDatabase.getInstance().getReference("");
                Map<String, Object> userTimeUpdates = new HashMap<String, Object>();
                userTimeUpdates.put("needyName", name0);
                userTimeUpdates.put("needyDob",age0);
                userTimeUpdates.put("needyAddress",add1);


                databaseReference8.child(id).updateChildren(userTimeUpdates);

//                Query query02=databaseReference.child("Needy").orderByChild("phNO").equalTo(id2);
//                query02.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//
//                            Person person2=postSnapshot.getValue(Person.class);
//                              Blood=person2.getBloodgrp();
//                           Add= person2.getAddress();
//                            Name=person2.getName();
//                            Phno=person2.getPhNO();
//                            Dob=person2.getDob();
//                           Idd= person2.getPersonid();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });



                Toast.makeText(context, "Request Send", Toast.LENGTH_SHORT).show();
                textViewVisible.setVisibility(View.VISIBLE);
                 requestbtn.setVisibility(View.GONE);

            }
        });
        return listItemView;
    }

}
