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
import android.widget.TextView;
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

public class p_page extends AppCompatActivity {

    Intent a, b;
    String name, dx, nurseName;
    Button listview;
    EditText editName, editDx, editNurse;
    TextView editLocation;
    Button Emergency;
    String inputName, inputDx, inputNursename, inputLocation;
    //String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_page);
        a = getIntent();

        name = a.getExtras().getString("name");
        dx = a.getExtras().getString("dx");
        nurseName = a.getExtras().getString("nurseName");

        Emergency = (Button) findViewById(R.id.button5);
        listview = (Button) findViewById(R.id.button6);
        editName = (EditText) findViewById(R.id.editText7);
        editDx = (EditText) findViewById(R.id.editText8);
        editNurse = (EditText) findViewById(R.id.editText9);

        editName.setText(name);
        editDx.setText(dx);
        editNurse.setText(nurseName);
        Log.d(nurseName, "여기들어가야되");
        editLocation = (TextView) findViewById(R.id.editText10);

         b = getIntent();

        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Log.d("여기까지","들오냐");
                Intent listview_intent = new Intent(p_page.this, listview.class);
                listview_intent.putExtra("name",name);
                listview_intent.putExtra("dx", dx);
                listview_intent.putExtra("nurseName", nurseName);

                startActivity(listview_intent);
            }
        });

        if (b != null) {
           //b = getIntent();

            //TextView textView1 = (TextView)findViewById(R.id.editText10);
            editLocation.setText(b.getStringExtra("location"));
            editName.setText(b.getStringExtra("name"));
            editDx.setText(b.getStringExtra("dx"));
            editNurse.setText(b.getStringExtra("nurseName"));

            Log.d(b.getStringExtra("nurseName"),"여기언제들감");
        }

        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputName = editName.getText().toString();
                inputDx = editDx.getText().toString();
                inputNursename = editNurse.getText().toString();
                inputLocation = editLocation.getText().toString();

                InsertData task1 = new InsertData();
                task1.execute(new String[]{"http://165.194.34.58:80/EmerInfo.php"});
                // url= "http://165.194.34.207:80/EmerInfo.php";

            }
        });

    }

    private class InsertData extends AsyncTask<String, Void, Boolean> {
        ProgressDialog dialog = new ProgressDialog(p_page.this);


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

                    pairs.add(new BasicNameValuePair("name", inputName));
                    pairs.add(new BasicNameValuePair("dx", inputDx));
                    pairs.add(new BasicNameValuePair("nurseName", inputNursename));
                    //pairs.add(new BasicNameValuePair("dx", dx));
                    pairs.add(new BasicNameValuePair("location", inputLocation));
                    // pairs.add(new BasicNameValuePair("role", role));
                    //Log.d(id,"id");


                    ////// 입력하는 3개 요소를 namevaluepair 형식으로 만들어 전송한다.
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    post.setEntity(new UrlEncodedFormEntity(pairs));
                    HttpResponse response = client.execute(post);
                } catch (ClientProtocolException e) {
                    Toast.makeText(p_page.this, e.toString(), Toast.LENGTH_LONG).show();
                    //Log.d( id,"id2");
                    return false;
                } catch (IOException e) {
                    // Log.d("어디",e.toString());
                    //Log.d( id,"id42");

                    e.printStackTrace();
                    Toast.makeText(p_page.this, e.toString(), Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {

            if (result == true) {
                Log.d("어디", "5");
                //   Log.d("id72", id);

                Toast.makeText(p_page.this, "Insert Success", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(p_page.this, "Error", Toast.LENGTH_LONG).show();

            }
            dialog.dismiss();
        }
    }
}
