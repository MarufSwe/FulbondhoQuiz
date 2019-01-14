package com.example.khanmaruf.fulbondho;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LoginActivity extends AppCompatActivity {

    private Button login_submit;

    // private SharedPreferences mPerformences;
    private EditText et_student_phone, et_student_password ;

    int [] currectAnswer = new int[7];
    int [] playingTime = new int[7];
    int [] whichROund = new int[7];
    int [] takenTime = new int[7];
    String [] last_play_time = new String[7];

    int b = 0 , point = 0;

    String login_student_phone, login_student_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handleSSLHandshake();

        et_student_phone = (EditText) findViewById(R.id.et_student_phone);
        et_student_password = (EditText) findViewById(R.id.et_student_password);
        login_submit = (Button) findViewById(R.id.login_submit);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, All_Round.class);
//                startActivity(intent);

                login_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(point == 0) {
                            point = 1;
                            login_student_phone = et_student_phone.getText().toString();
                            login_student_password = et_student_password.getText().toString();


                           // String myURL = "https://sererl.com/login1.php?phone=" + login_student_phone +"&password=" + login_student_password;

                            //String myURL = "http://fulbondhu.net/sheiblu/api/v1/login1.php?phone=" + login_student_phone +"&password=" + login_student_password;

                            String myURL = "https://sererl.com/api/v1/login1.php?phone=" + login_student_phone +"&password=" + login_student_password;


                            //String myURL = "https://realistic-camps.000webhostapp.com/v1/login1.php?phone=" + login_student_phone + "&password=" + login_student_password;
                           // String myURL = "https://fulbondhu.net/api/v1/login?phone=01671794064&password=123456";
                            //String myURL = "https://undrooping-till.000webhostapp.com/equipement.php";

                           // String myURL = "https://fulbondhu.net/api/v1/login?phone=" + login_student_phone + "&password=" + login_student_password;

                            Log.d("Exception", myURL);
                            Log.d("sd", "Run");

                            //myURL = "https://unheard-of-lumber.000webhostapp.com/fulbondhu/api/v1/login1.php?phone=01671794064&password=123456";

                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {

//                                JSONParrser paser = new JSONParser();
//                                JSONObject json = (JSONObject) parser.parse(response);

                                    b = response.length();
                                    Log.d("Exception length", String.valueOf(b));
                                    Log.d("Response show", String.valueOf(response));

                                    if(b == 1) {
                                        try {
                                            JSONObject jsonObject = (JSONObject) response.get(0);

                                            currectAnswer[0] = jsonObject.getInt("R1_currect_answer");
                                            playingTime[0] = jsonObject.getInt("R1_time") / 1000;

                                            currectAnswer[1] = jsonObject.getInt("R2_currect_answer");
                                            playingTime[1] = jsonObject.getInt("R2_time")/ 1000;

                                            currectAnswer[2] = jsonObject.getInt("R3_currect_answer");
                                            playingTime[2] = jsonObject.getInt("R3_time")/ 1000;

                                            currectAnswer[3] = jsonObject.getInt("R4_currect_answer");
                                            playingTime[3] = jsonObject.getInt("R4_time")/ 1000;

                                            currectAnswer[4] = jsonObject.getInt("R5_currect_answer");
                                            playingTime[4] = jsonObject.getInt("R5_time")/ 1000;

                                            currectAnswer[5] = jsonObject.getInt("R6_currect_answer");
                                            playingTime[5] = jsonObject.getInt("R6_time")/ 1000;

                                            currectAnswer[6] = jsonObject.getInt("R7_currect_answer");
                                            playingTime[6] = jsonObject.getInt("R7_time")/ 1000;
                                            updatePlayerScore();


                                            saveUserName();
//updatePlayerScore
//                                    takenTime [0] = jsonObject.getInt("R1_number_of_playing");
                                            Log.d("Exception", String.valueOf(currectAnswer[0]));
                                            Toast.makeText(getApplicationContext(), "Login Successfully ", Toast.LENGTH_SHORT).show();

                                            response = null;

                                            Intent intent = new Intent(LoginActivity.this, All_Round.class);
                                            startActivity(intent);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.d("Exception", "so");
                                            Toast.makeText(getApplication(), "Data Somethink Wrong " + e, Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Login Fail ", Toast.LENGTH_SHORT).show();
                                        Log.d("sd", response + "  " + response.toString());
                                    }
                                    b = 0;

                                    response = null;
                                    point = 0;

                                }
                            }, new Response.ErrorListener() {
                                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    VolleyLog.d("--Volley Lod--", error);
                                    Log.d("error",error.toString());
                                    int sdk = android.os.Build.VERSION.SDK_INT;

                                    point = 0;
                                }
                            });

//                         jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
//                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//                            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                    0,
//                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                            AppController.getInstance().addToRequestQueue(jsonArrayRequest);
                        }
                    }
                });
            }
        });
    }


    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {

        }
    }

    public void updatePlayerScore () {
        //Start Copy
        Log.d("myLog", "*********** Database Start *************");
        dbCopy mydb = new dbCopy(this);
        String dbPath = mydb.DB_PATH + "/fulbondhu.db";
        try {
            mydb.createDataBase();
            Log.d("myLog", "Database Create");
        } catch (IOException e) {
            Log.d("myLog", "Database Not Create");
            e.printStackTrace();
        }



        SQLiteDatabase myDatabase = null;

        Log.d("DB PAth", dbPath + "\n" + mydb.DB_PATH);

        try {
            myDatabase = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Log.d("myLog", "Database Open Problem menu");
        }


        try {

            for(int i = 1; i< 8 ; i++){

//                String sql2 = "Update android_score_table set currect_answer = "+correctAnswer+" where round = "+roundNo+" ;";
//                Log.d("New Round open ", sql4);
//                myDatabase.execSQL(sql4);

                myDatabase.execSQL("Update android_score_table set currect_answer = " + currectAnswer[i-1] + " where round = "+ i +" ;");
                myDatabase.execSQL("Update android_score_table set time = " + playingTime[i-1] + " where round = "+ i +" ;");
               // myDatabase.execSQL("Update android_score_table set number_of_play = 0 where round = "+ i +" ;");
                String a = "Update android_score_table set currect_answer = " + currectAnswer[i-1] + " where round = "+ i +" ;" ;
                Log.d("swl", String.valueOf(a));
            }

            Log.d("myLog", "raw query not error in menu");
        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }


        myDatabase.close();
    }

    public void saveUserName(){
        SharedPreferences sharedPreferences = getSharedPreferences("student id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", login_student_phone);
        editor.apply();

      //  Toast.makeText(this, " ", Toast.LENGTH_LONG).show();
    }

}
