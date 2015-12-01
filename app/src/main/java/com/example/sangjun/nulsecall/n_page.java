package com.example.sangjun.nulsecall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class n_page extends AppCompatActivity {
    String name;
    Intent i;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_page);

        Intent i = getIntent();
        name = i.getExtras().getString("name");
        Log.d(name, "이름이머예요");
    }
}
