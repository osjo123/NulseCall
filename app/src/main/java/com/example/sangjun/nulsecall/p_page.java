package com.example.sangjun.nulsecall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class p_page extends AppCompatActivity {


    Button listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_page);

        listview = (Button)findViewById(R.id.button6);
        Intent intent = getIntent();


        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d("여기까지","들오냐");
                Intent listview_intent = new Intent(p_page.this, listview.class);
                startActivity(listview_intent);
            }
        });

        if(intent!=null){
            TextView textView1 = (TextView)findViewById(R.id.editText10);
            textView1.setText(intent.getStringExtra("NAME"));
        }


    }
}
