package com.example.sangjun.nulsecall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listview extends AppCompatActivity {

    private ListView m_ListView;
    private ArrayAdapter<String>  m_Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);



        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_textview);
        m_ListView = (ListView) findViewById(R.id.listView);
        // Xml에서 추가한 ListView 연결

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(onClickListItem);


        // ListView에 아이템 추가
        m_Adapter.add("1병동");
        m_Adapter.add("2병동");
        m_Adapter.add("3병동");
        m_Adapter.add("4병동");
        m_Adapter.add("5병동");
        m_Adapter.add("6병동");
        m_Adapter.add("7병동");
        m_Adapter.add("8병동");
        m_Adapter.add("9병동");
        m_Adapter.add("1병동");
        m_Adapter.add("2병동");
        m_Adapter.add("3병동");

    }

    // 아이템 터치 이벤트

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            TextView select_item = (TextView)view;
            Intent intent = new Intent(listview.this,p_page.class);
            intent.putExtra("NAME",String.valueOf(select_item.getText()));
            startActivity(intent);
        }
    };
}

