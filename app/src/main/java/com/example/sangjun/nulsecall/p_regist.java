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

public class p_regist extends AppCompatActivity {
    Button regist;
    EditText inputid,inputpass, inputname, inputdx, inputnurse;
    private final String SERVER_ADDRESS = "http://172.30.1.26:80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   StrictMode.enableDefaults();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_regist);

        inputid=(EditText)findViewById(R.id.editText5);
        inputpass=(EditText)findViewById(R.id.editText6);
        inputname = (EditText)findViewById(R.id.editText15);
        inputdx = (EditText)findViewById(R.id.editText17);
        inputnurse = (EditText)findViewById(R.id.editText16);


        regist =(Button)findViewById(R.id.button10);


        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputname.getText().toString().equals("")||inputid.getText().toString().equals("")||inputpass.getText().toString().equals("")||
                        inputdx.getText().toString().equals("")||inputnurse.getText().toString().equals("")){
                    Toast.makeText(p_regist.this, "입력오류입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("이러면","안되는데");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String id = inputid.getText().toString();
                        String password = inputpass.getText().toString();
                        String name = inputname.getText().toString();
                        String dx = inputdx.getText().toString();
                        String nurseName = inputnurse.getText().toString();

                        String role = "2";
                        Log.d("이러면", "안되는데22");
                        try {
                            Log.d("이러면", "안되는데233");

                            URL url = new URL(SERVER_ADDRESS + "/patientInfo.php?"
                                    + "id=" + URLEncoder.encode(id, "UTF-8")
                                    + "&password=" + URLEncoder.encode(password, "UTF-8")
                                    + "&name=" + URLEncoder.encode(name, "UTF-8")
                                    + "&dx=" + URLEncoder.encode(dx, "UTF-8")
                                    + "&nurseName=" + URLEncoder.encode(nurseName, "UTF-8")
                                    + "&role=" + URLEncoder.encode(role, "UTF-8"));
/*
                            URL url = new URL(SERVER_ADDRESS + "/nurseInfo.php?"
                                    + "id="+ URLEncoder.encode(id,"UTF-8")
                                    + "&password="+ URLEncoder.encode(password,"UTF-8")
                                    + "&name="+ URLEncoder.encode(name,"UTF-8")
                                    + "&role="+ URLEncoder.encode(role,"UTF-8"));
*/

                            Log.d("결과2", nurseName);
                            url.openStream();
                            String result = getXmlData("patientinsertresult.xml", "result");

                            if (result.equals("1")) {
                                Toast.makeText(p_regist.this, "가입완료! 로그인하세요",
                                        Toast.LENGTH_SHORT).show();

                                inputid.setText("");
                                inputpass.setText("");
                                inputname.setText("");
                                inputdx.setText("");
                                inputnurse.setText("");

                            } else
                                Toast.makeText(p_regist.this, "회원 가입 실패(ID 중복)",
                                        Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(p_regist.this, login.class));
                            finish();
                        } catch (Exception e) {
                            Log.e("에러가낫음", e.toString());
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
