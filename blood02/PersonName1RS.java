package com.example.android.blood02;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by Souvik on 20-08-2017.
 */

public class PersonName1RS extends ArrayAdapter<Person> {
    private Activity context;
    String Add,Name,Blood,Dob,Phno,Id;
    public static DatabaseReference databaseReference12;
    private List<Person> persons;
    public static DatabaseReference databaseReference13;


    public PersonName1RS (Activity context,List<Person> persons){
        super(context,R.layout.list_item2,persons);
        this.context=context;
        this.persons=persons;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        final View listItemView = layoutInflater.inflate(R.layout.list_item2,null,true);

        SharedPreferences sharedPreferences=context.getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");

        final TextView kName=(TextView)listItemView.findViewById(R.id.kName);
        final TextView kAdd=(TextView)listItemView.findViewById(R.id.kAdd);
        final TextView kBlood=(TextView)listItemView.findViewById(R.id.kBlood);
        final TextView kDob=(TextView)listItemView.findViewById(R.id.kDob);
        final TextView kId =(TextView)listItemView.findViewById(R.id.kId);
        final   TextView kPhno=(TextView)listItemView.findViewById(R.id.kPhno);
        final TextView kvisible=(TextView)listItemView.findViewById(R.id.kvisible);
//        final TextView hey=(TextView)listItemView.findViewById(R.id.hey);


//        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//        final Boolean approve=false;


//        SharedPreferences sharedPreferences=context.getSharedPreferences("UserId", Context.MODE_PRIVATE);
//        final String id2=sharedPreferences.getString("userId","");


        final Person person=persons.get(position);
        kName.setText(person.getName());
        kAdd.setText(person.getAddress());
        kBlood.setText(person.getBloodgrp());
        kDob.setText(person.getDob());

        if(person.getApproval()==true){
        kPhno.setText(person.getPhNO());
        }

         kId.setText(person.getPersonid());



        return listItemView;
    }



}
