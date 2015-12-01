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
    Intent a;
    private ListView m_ListView;
    private ArrayAdapter<String>  m_Adapter;
    String name, dx, nurseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        a = getIntent();

        name = a.getExtras().getString("name");
        dx = a.getExtras().getString("dx");
        nurseName = a.getExtras().getString("nurseName");



        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_textview);
        m_ListView = (ListView) findViewById(R.id.listView);
        // Xml에서 추가한 ListView 연결

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(onClickListItem);


        // ListView에 아이템 추가
        m_Adapter.add("1ward");
        m_Adapter.add("2ward");
        m_Adapter.add("3ward");
        m_Adapter.add("4ward");
        m_Adapter.add("5ward");
        m_Adapter.add("6ward");
        m_Adapter.add("8ward");
        m_Adapter.add("9ward");
        m_Adapter.add("10ward");
        m_Adapter.add("11ward");
        m_Adapter.add("12ward");
        m_Adapter.add("13ward");
        m_Adapter.add("14ward");
        m_Adapter.add("15ward");
        m_Adapter.add("16ward");
        m_Adapter.add("17ward");
    }

    // 아이템 터치 이벤트

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            TextView select_item = (TextView)view;
            Intent intent = new Intent(listview.this,p_page.class);
            intent.putExtra("name", name);
            intent.putExtra("dx", dx);
            intent.putExtra("nurseName", nurseName);

            intent.putExtra("location",String.valueOf(select_item.getText()));
            startActivity(intent);
        }
    };
}

