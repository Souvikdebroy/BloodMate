package com.example.android.blood02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        Button reqReceived=(Button)findViewById(R.id.reqReceived);
        reqReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(MyAccount.this,RequestReceived.class);
                startActivity(n);
            }
        });
        Button reqSend=(Button)findViewById(R.id.reqSend);
        reqSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(MyAccount.this,RequestSend.class);
                startActivity(o);
            }
        });

    }
}
