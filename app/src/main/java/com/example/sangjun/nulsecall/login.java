package com.example.sangjun.nulsecall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {
    Button p_regist;
    Button n_regist;
    Button p_page;
    Button n_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        p_regist = (Button)findViewById(R.id.button2);
        n_regist = (Button)findViewById(R.id.button3);
        p_page = (Button)findViewById(R.id.button4);
        n_page = (Button)findViewById(R.id.button7);

        p_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p_regist_intent= new Intent(login.this, p_regist.class);
                startActivity(p_regist_intent);
            }
        });
        n_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n_regist_intent= new Intent(login.this, n_regist.class);
                startActivity(n_regist_intent);
            }
        });
        p_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p_page_intent= new Intent(login.this, p_page.class);
                startActivity(p_page_intent);
            }
        });
        n_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n_page_intent= new Intent(login.this, n_page.class);
                startActivity(n_page_intent);
            }
        });

    }
}
