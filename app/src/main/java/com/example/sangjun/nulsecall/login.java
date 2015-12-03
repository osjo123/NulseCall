package com.example.sangjun.nulsecall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class login extends AppCompatActivity {
    Button p_regist;
    Button n_regist;
    Button p_page;
    Button n_page;
    EditText id;
    EditText password;
    public GettingPHP gPHP, gPHP2;
    TextView tv;
    int index=0;
    String inputId;
    String inputPass;
    String url;
    String nurseName;
    String patientName;
    String dx;
    String patientNurse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        p_regist = (Button)findViewById(R.id.button2);
        n_regist = (Button)findViewById(R.id.button3);
        p_page = (Button)findViewById(R.id.button4);
        n_page = (Button)findViewById(R.id.button7);

        id = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        tv = (TextView)findViewById(R.id.textView22);
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
               //Intent p_page_intent= new Intent(login.this, p_page.class);
                //startActivity(p_page_intent);
               // new ReadJSONFeed().execute("http://165.194.34.207:80/loginInfo.php");
               inputId=id.getText().toString();
               inputPass= password.getText().toString();
               url= "http://165.194.34.58:80/P_login.php";
               // loginMysql(inputId,inputPass,url);
                gPHP2 = new GettingPHP2();
                gPHP2.execute(url);
                if(index==1){
                    index=0;
                    Intent p_page_intent= new Intent(login.this, p_page.class);
                    p_page_intent.putExtra("name",patientName);
                    p_page_intent.putExtra("dx",dx);
                    p_page_intent.putExtra("nurseName",patientNurse);
                    Log.d(patientName, "환자이름");
                    Log.d(dx,"dx");
                    Log.d(patientNurse,"patientNurse");

                    startActivity(p_page_intent);
                }
            }
        });

        n_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent n_page_intent = new Intent(login.this, n_page.class);
               // startActivity(n_page_intent);
                inputId=id.getText().toString();
                inputPass= password.getText().toString();
                url= "http://165.194.34.58:80/loginInfo.php";
                // loginMysql(inputId,inputPass,url);
                gPHP = new GettingPHP();
                gPHP.execute(url);
                if(index==1){
                    index=0;
                    Intent n_page_intent= new Intent(login.this, n_page.class);
                    //Intent.putExtra("name",nurseName);
                    n_page_intent.putExtra("name",nurseName.toString());
                    Log.d(nurseName,"간호사이름");
                    startActivity(n_page_intent);
                }
            }
        });

    }

    class GettingPHP extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                if ( conn != null ) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while ( true ) {
                            String line = br.readLine();
                            if ( line == null )
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONArray jObject = new JSONArray(str);
                // results라는 key는 JSON배열로 되어있다.

                //JSONArray results = jObject.getJSONArray("results");
                String zz = "";
                //zz += "Status : " + jObject.get("status");
                //zz += "\n";
                //zz += "Number of results : " + jObject.get("num_result");
                //zz += "\n";
                //zz += "Results : \n";

                for ( int i = 0; i < jObject.length(); ++i ) {
                    JSONObject temp = jObject.getJSONObject(i);
                    if(inputId.equals(temp.get("id"))&&inputPass.equals(temp.get("password"))){
                        index = 1;
                        nurseName = temp.get("name").toString();
                    }
                  // zz += "id:" + temp.get("id");
                  // zz += "\tpass:" + temp.get("password");
                  // zz += "\tname:" + temp.get("name");
                  // zz += "\trole:" + temp.get("role");
                  //  zz += "\n\t--------------------------------------------\n";
                }
                Log.d(index+"index값","맞아라");
                Log.d(inputId,"id는무녀");
                //tv.setText(zz);
            } catch (Exception k) {
                Log.e("ddd",k.toString());
            }
        }
    }
    class GettingPHP2 extends GettingPHP {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                if ( conn != null ) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while ( true ) {
                            String line = br.readLine();
                            if ( line == null )
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONArray jObject = new JSONArray(str);
                // results라는 key는 JSON배열로 되어있다.

                //JSONArray results = jObject.getJSONArray("results");
                String zz = "";
                //zz += "Status : " + jObject.get("status");
                //zz += "\n";
                //zz += "Number of results : " + jObject.get("num_result");
                //zz += "\n";
                //zz += "Results : \n";

                for ( int i = 0; i < jObject.length(); ++i ) {
                    JSONObject temp = jObject.getJSONObject(i);
                    if(inputId.equals(temp.get("id"))&&inputPass.equals(temp.get("password"))){
                        index = 1;
                        patientName = temp.get("name").toString();
                        dx = temp.get("dx").toString();
                        patientNurse = temp.get("nurseName").toString();
                    }
                   // zz += "id:" + temp.get("id");
                   // zz += "\tpass:" + temp.get("password");
                   // zz += "\tname:" + temp.get("name");
                   // zz += "\trole:" + temp.get("role");
                    //  zz += "\n\t--------------------------------------------\n";
                }
                Log.d(index+"index값","맞아라");
                Log.d(inputId,"id는무녀");
                //tv.setText(zz);
            } catch (Exception k) {
                Log.e("ddd",k.toString());
            }
        }
    }



}
