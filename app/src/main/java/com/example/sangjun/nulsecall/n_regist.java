package com.example.sangjun.nulsecall;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class n_regist extends AppCompatActivity {
    Button regist;
    EditText inputid,inputpass,inputname;
    private final String SERVER_ADDRESS = "http://165.194.34.207:80";
    private final String TAG = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_regist);

        regist = (Button)findViewById(R.id.button11);
        inputid = (EditText) findViewById(R.id.editText3);
        inputpass = (EditText)findViewById(R.id.editText4);
        inputname = (EditText)findViewById(R.id.editText18);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputname.getText().toString().equals("")||inputid.getText().toString().equals("")||inputpass.getText().toString().equals("")){
                   Toast.makeText(n_regist.this, "입력오류입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String id = inputid.getText().toString();
                        String password = inputpass.getText().toString();
                        String name = inputname.getText().toString();
                        String role ="1";
                        try{
                            URL url = new URL(SERVER_ADDRESS + "/nurseInfo.php?"
                                    + "id="+ URLEncoder.encode(id,"UTF-8")
                                    + "&password="+ URLEncoder.encode(password,"UTF-8")
                                    + "&name="+ URLEncoder.encode(name,"UTF-8")
                                    + "&role="+ URLEncoder.encode(role,"UTF-8"));
                            url.openStream();

                            String result = getXmlData("nurseinsertresult.xml","result");

                            if(result.equals("1")){
                                Toast.makeText(n_regist.this, "가입완료! 로그인하세요",
                                Toast.LENGTH_SHORT).show();

                                inputid.setText("");
                                inputpass.setText("");
                                inputname.setText("");
                            }else
                                Toast.makeText(n_regist.this, "회원 가입 실패(ID 중복)",
                                        Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(n_regist.this,login.class));
                            finish();
                        }catch(Exception e){
                            Log.e("error",e.getMessage() );
                        }

                    }
                });
            }
        });
    }
    private String getXmlData(String filename,String str){
        String rss = SERVER_ADDRESS + "/";
        String ret = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            URL server = new URL(rss+filename);
            InputStream is = server.openStream();
            xpp.setInput(is,"UTF-8");

            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    if(xpp.getName().equals(str)){
                        ret = xpp.nextText();
                    }
                }
                eventType = xpp.next();
            }
        }catch(Exception e){
            Log.e("Error2",e.getMessage());
        }
        return ret;
    }
}
