package com.example.android.blood02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.android.blood02.AuthPhno.phNO;

public class Login extends AppCompatActivity {
  FirebaseAuth auth2;
    String email2,password2;
    DatabaseReference databaseReference011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth2=FirebaseAuth.getInstance();

        databaseReference011= FirebaseDatabase.getInstance().getReference();
        if (auth2.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity2.class));
            finish();
        }

        final EditText email1=(EditText)findViewById(R.id.email1);
        final EditText password1=(EditText)findViewById(R.id.password1);
        Button login=(Button)findViewById(R.id.login1);


        Toast.makeText(Login.this,email2,Toast.LENGTH_SHORT).show();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hey","wrong");
                email2= email1.getText().toString();
                password2=password1.getText().toString();

                SharedPreferences sharedPreferences=getSharedPreferences("Email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("email",email2);
                editor.apply();

                auth2.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this,"Invalid",Toast.LENGTH_SHORT).show();
                        } else {

                            Query query9=databaseReference011.child("Accounts").orderByChild("email").equalTo(email2);
                            query9.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        final Person person011 = postSnapshot.getValue(Person.class);

                                        phNO=person011.getPhNO();
                                    }}

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            Intent q = new Intent(Login.this, MainActivity2.class);
                            startActivity(q);
                            finish();

                        }
                    }
                });
            }
        });

    }
}
