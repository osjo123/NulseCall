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


import java.io.IOException;

import java.util.ArrayList;

public class n_regist extends AppCompatActivity {
    Button regist;
    EditText inputid,inputpass,inputname;
    private final String TAG = "id";
    String id, password, name, role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_regist);

        regist = (Button)findViewById(R.id.button11);
        inputid = (EditText) findViewById(R.id.editText3);
        inputpass = (EditText)findViewById(R.id.editText4);
        inputname = (EditText)findViewById(R.id.editText18);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = inputid.getText().toString();
                password = inputpass.getText().toString();
                name = inputname.getText().toString();
                role = "1";
                InsertData task1 = new InsertData();
                task1.execute(new String[]{"http://165.194.34.58:80/nurseInfo.php"});
            }

        });
    }
    private class InsertData extends AsyncTask<String,Void,Boolean> {
        ProgressDialog dialog = new ProgressDialog(n_regist.this);


        protected void OnPreExecute() {
            dialog.setMessage("Sending Data.... ");
            dialog.show();   ////우선수행
            Log.d("어디", "3");

        }
        @Override
        protected Boolean doInBackground(String... urls) {
////백그라운드 수행 주소 받아서 post방식으로 전송
            Log.d("어디","4");
            for (String url : urls) {
                Log.d(password,"password");

                try {
                    ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();

                    pairs.add(new BasicNameValuePair("id",id));
                    pairs.add(new BasicNameValuePair("password", password));
                    pairs.add(new BasicNameValuePair("name", name));
                   pairs.add(new BasicNameValuePair("role", role));
                    Log.d(id,"id");


                    ////// 입력하는 3개 요소를 namevaluepair 형식으로 만들어 전송한다.
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    post.setEntity(new UrlEncodedFormEntity(pairs));
                    HttpResponse response = client.execute(post);
                } catch ( ClientProtocolException e) {
                    Toast.makeText(n_regist.this, e.toString(), Toast.LENGTH_LONG).show();
                    Log.d( id,"id2");
                    return false;
                } catch (IOException e){
                    Log.d("어디",e.toString());
                    Log.d( id,"id42");

                    e.printStackTrace();
                    Toast.makeText(n_regist.this, e.toString(), Toast.LENGTH_LONG).show();
                    return false;
                }

            }

            return true;

        }

        protected void onPostExecute(Boolean result) {

            if(result == true) {
                Log.d("어디","5");
                Log.d("id72", id);

                Toast.makeText(n_regist.this, "Insert Success",Toast.LENGTH_LONG).show();
            }else{

                Toast.makeText(n_regist.this, "Error",Toast.LENGTH_LONG).show();

            }
            dialog.dismiss();
        }




    }
}
