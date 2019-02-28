package com.example.android.blood02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity2 extends AppCompatActivity {
   public static DatabaseReference databaseReference5,databaseReference6;
   FirebaseAuth auth4;
    public static String PHNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        databaseReference5= FirebaseDatabase.getInstance().getReference("Needy");
//        databaseReference6=FirebaseDatabase.getInstance().getReference();

        SharedPreferences sharedPreferences=getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        final String id2=sharedPreferences.getString("mobileno","");
        SharedPreferences sharedPreferences011=getSharedPreferences("Email", Context.MODE_PRIVATE);
        final String id3=sharedPreferences.getString("email","");






       Button logout=(Button)findViewById(R.id.logout);
      auth4=FirebaseAuth.getInstance();
        Button donate=(Button)findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(MainActivity2.this,Donate.class);
                startActivity(k);
            }
        });

        Button receive =(Button)findViewById(R.id.receive);
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Query query=databaseReference6.child("Accounts").orderByChild("phNO").equalTo(id2);
//                query.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                           final Person person3 =postSnapshot.getValue(Person.class);
//                            String id=databaseReference5.push().getKey();
//                            databaseReference5.child(id).setValue(person3);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

                Intent l =new Intent(MainActivity2.this,Needy.class);
                startActivity(l);


            }
        });

        Button account=(Button)findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m =new Intent(MainActivity2.this,MyAccount.class);
                startActivity(m);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth4.signOut();
                Intent t =new Intent(MainActivity2.this, MainActivity.class);
                Toast.makeText(MainActivity2.this,"thank",Toast.LENGTH_SHORT).show();

                startActivity(t);
                finish();
//                Toast.makeText(MainActivity2.this,"thank",Toast.LENGTH_SHORT).show();

// this listener will be called when there is change in firebase user session
//                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
//                    @Override
//                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                        FirebaseUser user = firebaseAuth.getCurrentUser();
//                        if (user == null) {
//                            // user auth state is changed - user is null
//                            // launch login activity
//                            Intent t =new Intent(MainActivity2.this, MainActivity.class);
//                            Toast.makeText(MainActivity2.this,"thank1",Toast.LENGTH_SHORT).show();
//
//                            startActivity(t);
//                            Toast.makeText(MainActivity2.this,"thank2",Toast.LENGTH_SHORT).show();
//
//                            finish();
//                        }
//                    }
//                };

            }
        });




    }
}
