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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Souvik on 06-08-2017.
 */

public class PersonName1RR extends ArrayAdapter<Person> {
    private Activity context;
    String Add,Name,Blood,Dob,Phno,Id;
    public static DatabaseReference databaseReference15;
    private List<Person> persons;



    public PersonName1RR (Activity context,List<Person> persons){
        super(context,R.layout.list_item1,persons);
        this.context=context;
        this.persons=persons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        final View listItemView = layoutInflater.inflate(R.layout.list_item1,null,true);

        SharedPreferences sharedPreferences=context.getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");

        final TextView gName=(TextView)listItemView.findViewById(R.id.gName);
        final TextView gAdd=(TextView)listItemView.findViewById(R.id.gAdd);
        final TextView gBlood=(TextView)listItemView.findViewById(R.id.gBlood);
        final TextView gDob=(TextView)listItemView.findViewById(R.id.gDob);
      final TextView textViewId =(TextView)listItemView.findViewById(R.id.gId);
        final   TextView gPhno=(TextView)listItemView.findViewById(R.id.gPhno);
        final TextView gvisible=(TextView)listItemView.findViewById(R.id.gvisible);
//        final TextView hey=(TextView)listItemView.findViewById(R.id.hey);
        final Button  grequestbtn=(Button)listItemView.findViewById(R.id.grequestbtn);


//        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//        final Boolean approve=false;


//        SharedPreferences sharedPreferences=context.getSharedPreferences("UserId", Context.MODE_PRIVATE);
//        final String id2=sharedPreferences.getString("userId","");


        final Person person=persons.get(position);
        gName.setText(person.getName());
        gAdd.setText(person.getAddress());
        gBlood.setText(person.getBloodgrp());
        gDob.setText(person.getDob());
        gPhno.setText(person.getPhNO());
     textViewId.setText(person.getPersonid());
        databaseReference15= FirebaseDatabase.getInstance().getReference("Request");

        grequestbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Map<String, Object> userTimeUpdates1 = new HashMap<String, Object>();
                userTimeUpdates1.put("approval",true);
                databaseReference15.child(person.getPersonid()).updateChildren(userTimeUpdates1);
                grequestbtn.setVisibility(View.GONE);

            }
        });
        return listItemView;
    }

}
