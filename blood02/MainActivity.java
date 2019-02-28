package com.example.android.blood02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
      FirebaseAuth auth3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=(Button)findViewById(R.id.btn1);
        Button btn2=(Button)findViewById(R.id.btn2);
        auth3=FirebaseAuth.getInstance();
        if (auth3.getCurrentUser() != null) {

            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();

        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AuthPhno.class);
                startActivity(i);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent q=new Intent(MainActivity.this,Login.class);
                startActivity(q);
            }
        });
    }
}
