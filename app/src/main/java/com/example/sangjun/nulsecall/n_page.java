package com.example.sangjun.nulsecall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class n_page extends AppCompatActivity {
    String nurseName;
    Intent i;
    Button update, delete;
    String url;
    public GettingPHP gPHP;
    int index;
    String name, dx, location;
    EditText patientName, patientDx, patientNurse, patientLocation;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_page);

        Intent i = getIntent();
         nurseName = i.getExtras().getString("name");
        Log.d(nurseName, "이름이머예요");

        patientName = (EditText)findViewById(R.id.editText11);
        patientDx = (EditText)findViewById(R.id.editText12);
        patientNurse = (EditText)findViewById(R.id.editText13);
        patientLocation = (EditText)findViewById(R.id.editText14);


        update = (Button)findViewById(R.id.button12);
        delete = (Button)findViewById(R.id.button8);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url= "http://165.194.34.58:80/check_Emer.php";
                // loginMysql(inputId,inputPass,url);
                gPHP = new GettingPHP();
                gPHP.execute(url);
                /*
                if(index==1){
                    index=0;
                    Intent n_page_intent= new Intent(n_page.this, n_page.class);
                    //Intent.putExtra("name",nurseName);
                    n_page_intent.putExtra("name",nurseName.toString());
                    Log.d(nurseName,"간호사이름");
                    startActivity(n_page_intent);
                }
                */
                if(index==1) {
                    patientName.setText(name);
                    patientDx.setText(dx);
                    patientNurse.setText(nurseName);
                    patientLocation.setText(location);

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData task1 = new InsertData();
                task1.execute(new String[]{"http://165.194.34.58:80/deleteEmer.php"});
                patientName.setText("");
                patientDx.setText("");
                patientNurse.setText("");
                patientLocation.setText("");
            }
        });

    }
    private class InsertData extends AsyncTask<String, Void, Boolean> {
        ProgressDialog dialog = new ProgressDialog(n_page.this);


        protected void OnPreExecute() {
            dialog.setMessage("Sending Data.... ");
            dialog.show();   ////우선수행
            Log.d("어디", "3");

        }

        @Override
        protected Boolean doInBackground(String... urls) {
////백그라운드 수행 주소 받아서 post방식으로 전송
            Log.d("어디", "4");
            for (String url : urls) {
                //Log.d(password,"password");

                try {
                    ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();

                    pairs.add(new BasicNameValuePair("name", name));
                    pairs.add(new BasicNameValuePair("nurseName", nurseName));



                    ////// 입력하는 3개 요소를 namevaluepair 형식으로 만들어 전송한다.
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    post.setEntity(new UrlEncodedFormEntity(pairs));
                    HttpResponse response = client.execute(post);
                } catch (ClientProtocolException e) {
                    Toast.makeText(n_page.this, e.toString(), Toast.LENGTH_LONG).show();
                    //Log.d( id,"id2");
                    return false;
                } catch (IOException e) {
                    // Log.d("어디",e.toString());
                    //Log.d( id,"id42");

                    e.printStackTrace();
                    Toast.makeText(n_page.this, e.toString(), Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {

            if (result == true) {
                Log.d("어디", "5");
                //   Log.d("id72", id);

                Toast.makeText(n_page.this, "Delete Success", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(n_page.this, "Error", Toast.LENGTH_LONG).show();

            }
            dialog.dismiss();
        }
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
                    if(nurseName.equals(temp.get("nurseName"))){
                        index = 1;

                        name = temp.get("name").toString();
                        dx = temp.get("dx").toString();
                        location = temp.get("location").toString();
                    }
                    // zz += "id:" + temp.get("id");
                    // zz += "\tpass:" + temp.get("password");
                    // zz += "\tname:" + temp.get("name");
                    // zz += "\trole:" + temp.get("role");
                    //  zz += "\n\t--------------------------------------------\n";
                }
                Log.d(index+"index값","맞아라");
                //Log.d(inputId,"id는무녀");
                //tv.setText(zz);
            } catch (Exception k) {
                Log.e("ddd",k.toString());
            }
        }
    }
}
