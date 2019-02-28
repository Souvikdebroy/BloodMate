package com.example.android.blood02;

public class Person {
    String personid,needyNo,name,dob,bloodgrp,address,phNO,dateTime,time,needyAddress,needyDob,needyName,email;
    Boolean approval;
    public Person(){}

    public Person(String id, String nm,String add, String blood, String DOB ,String phNO,String Email) {
        this.address = add;
        this.bloodgrp = blood;
        this.dob = DOB;
        this.name = nm;
        this.personid = id;
        this.phNO=phNO;
        this.email=Email;
    }
    public Person(String id, String nm,String add, String blood, String DOB ,String phNO) {
        this.address = add;
        this.bloodgrp = blood;
        this.dob = DOB;
        this.name = nm;
        this.personid = id;
        this.phNO=phNO;
    }

    public Person( String nm,String add, String DOB ,String id) {
        this.address = add;

        this.dob = DOB;
        this.name = nm;
        this.personid = id;

    }
    public Person( String id,String nm,String add, String blood, String DOB ,String phNO,String id3,String dateTime,Boolean approve) {
        this.address = add;
        this.bloodgrp = blood;
        this.dob = DOB;
        this.name = nm;
        this.personid=id;
        this.needyNo = id3;
        this.phNO=phNO;
        this.dateTime=dateTime;
        this.approval=approve;
    }
    public Person( String id,String nm,String add, String blood, String DOB ,String phNO,String id3,String dateTime,Boolean approve,String Nadd,String Ndob,String Nname ) {
        this.address = add;
        this.bloodgrp = blood;
        this.dob = DOB;
        this.name = nm;
        this.personid=id;
        this.needyNo = id3;
        this.phNO=phNO;
        this.dateTime=dateTime;
        this.approval=approve;
        this.needyAddress=Nadd;
        this.needyDob=Ndob;
        this.needyName=Nname;
    }
    public Person(String id,String nm,String blood,String DOB,String add){
        this.address=add;
        this.bloodgrp = blood;
        this.dob = DOB;
        this.name = nm;
        this.personid = id;

    }

    public Person(String time1){

        this.time=time1;
    }


    public String getPhNO() {
        return phNO;
    }

    public String getAddress() {
        return address;
    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public String getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public String getPersonid() {
        return personid;
    }

    public String getTime() {
        return time;
    }

    public String getNeedyNo() {
        return needyNo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Boolean getApproval() {
        return approval;
    }

    public String getNeedyAddress() {
        return needyAddress;
    }

    public String getNeedyName() {
        return needyName;
    }

    public String getNeedyDob() {
        return needyDob;
    }

    public String getEmail() {
        return email;
    }
}
