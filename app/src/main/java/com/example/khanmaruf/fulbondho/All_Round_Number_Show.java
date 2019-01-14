package com.example.khanmaruf.fulbondho;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class All_Round_Number_Show extends AppCompatActivity {

    TextView totalSevenRoundMarks;

    float allRoundTime [] = new float[7];
    int syncAnswer[] = new int[7];
    int allRoundCorrectAns [] = new int[7];
    int data [] = new int[14];
    Button btn_sync;
    int [] syncData = new int[14];
    int i = 0, j=0, d = 0;
    double total_seven_round_marks = 0;
    String login_student_phone;
    String ans1, dataForSync, t;
    String t1;


    TextView total_round1_time, total_round2_time, total_round3_time,
        total_round4_time, total_round5_time, total_round6_time, total_round7_time;


    TextView total_round1_mark, total_round2_mark, total_round3_mark, total_round4_mark,
        total_round5_mark, total_round6_mark, total_round7_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__round__number__show);

        fetchData();

        handleSSLHandshake();

        totalSevenRoundMarks = (TextView) findViewById(R.id.totalSevenRoundMarks);

        btn_sync = (Button) findViewById(R.id.btn_sync);

        total_round1_time = (TextView) findViewById(R.id.total_round1_time);
        total_round2_time = (TextView) findViewById(R.id.total_round2_time);
        total_round3_time = (TextView) findViewById(R.id.total_round3_time);
        total_round4_time = (TextView) findViewById(R.id.total_round4_time);
        total_round5_time = (TextView) findViewById(R.id.total_round5_time);
        total_round6_time = (TextView) findViewById(R.id.total_round6_time);
        total_round7_time = (TextView) findViewById(R.id.total_round7_time);

        total_round1_mark = (TextView) findViewById(R.id.total_round1_mark);
        total_round2_mark = (TextView) findViewById(R.id.total_round2_mark);
        total_round3_mark = (TextView) findViewById(R.id.total_round3_mark);
        total_round4_mark = (TextView) findViewById(R.id.total_round4_mark);
        total_round5_mark = (TextView) findViewById(R.id.total_round5_mark);
        total_round6_mark = (TextView) findViewById(R.id.total_round6_mark);
        total_round7_mark = (TextView) findViewById(R.id.total_round7_mark);


        total_round1_time.setText(String.valueOf(300 - allRoundTime[0]) + " sec");
        total_round1_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[0],allRoundTime[0])));

        total_round2_time.setText(String.valueOf(300 - allRoundTime[1]) + " sec");
        total_round2_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[1],allRoundTime[1])));

        total_round3_time.setText(String.valueOf(300 - allRoundTime[2]) + " sec");
        total_round3_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[2],allRoundTime[2])));

        total_round4_time.setText(String.valueOf(300 - allRoundTime[3]) + " sec");
        total_round4_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[3],allRoundTime[3])));

        total_round5_time.setText(String.valueOf(300 - allRoundTime[4]) + " sec");
        total_round5_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[4],allRoundTime[4])));

        total_round6_time.setText(String.valueOf(300 - allRoundTime[5]) + " sec");
        total_round6_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[5],allRoundTime[5])));

        total_round7_time.setText(String.valueOf(300 - allRoundTime[6]) + " sec");
        total_round7_mark.setText(String.valueOf(scoreCalculation(allRoundCorrectAns[6],allRoundTime[6])));

        totalSevenRoundMarks.setText(String.valueOf(String.format("৭ রাউন্ডের যোগফলঃ " + "%.02f", total_seven_round_marks)));




        //---------------------------cSyn Start-----------------------------------//

        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // GetDataForSync getDataForSync = new GetDataForSync();
                fetchDataForSync();
                dataForSync = "";
                Log.d("data", Arrays.toString(syncData));

                for (int k = 0; k < 14; k++){
                    dataForSync += syncData[k];
                    dataForSync += ",";
                }


                login_student_phone = getStudentPhoneNo();
                ans1 = String.valueOf(syncData[0]);
                t1 = String.valueOf(syncData[1]);

              //  String myURL = "http://fulbondhu.net/sheiblu/api/v1/sy1.php?phone=" + login_student_phone + "&ans1=" + ans1 + "&t1=" + t1;

                String myURL = "https://sererl.com/api/v1/sy4.php?phone=" + login_student_phone + "&data=" + dataForSync;

                Log.d("My URL", myURL);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int b = response.length();
                        Log.d("Exception length", String.valueOf(b));

                    }
                }, new Response.ErrorListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("--Volley Lod--", error);
                        Log.d("error", error.toString());
                        int sdk = android.os.Build.VERSION.SDK_INT;


                    }
                });

                AppController.getInstance().addToRequestQueue(jsonArrayRequest);

            }
        });
    }

    //-------------------------Sync finish-------------------------------------//



    public String getStudentPhoneNo(){
        SharedPreferences sharedPreferences = getSharedPreferences("student id", getApplication().MODE_PRIVATE);

        String phone = sharedPreferences.getString("phone","");
       //  Toast.makeText(this, " : "+ phone, Toast.LENGTH_LONG).show();
        return  phone;
    }



    public void fetchData () {
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

// String dbPath = "/data/data/com.example.diu.listviewwithimage/databases/";

        SQLiteDatabase myDatabase = null;

        Log.d("DB PAth", dbPath + "\n" + mydb.DB_PATH);

        try {
            myDatabase = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Log.d("myLog", "Database Open Problem menu");
        }


        String sql = "SELECT * FROM android_score_table;";
        //Log.d("sql ", sql);


        Cursor c = null;// d = null;

        try {
            c = myDatabase.rawQuery(sql, null);
            Log.d("myLog", "raw query not error in menu");
        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }

        int i = 0;
        String a;

        //Time now = new Time('');

        Log.d("time", String.valueOf((int)(System.currentTimeMillis())) );


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            allRoundCorrectAns[i] = c.getInt(c.getColumnIndex("currect_answer"));
            allRoundTime[i] = c.getInt(c.getColumnIndex("time"));
            syncAnswer[i] = c.getInt(c.getColumnIndex("sync"));

            i++;
        }

        Log.d("Currect Answer ", Arrays.toString(allRoundCorrectAns));
      //  Log.d("Number Of play ", Arrays.toString(allRoundCorrectAns));
        Log.d("  allRoundTime ", Arrays.toString(allRoundTime));
        Log.d("          Sync ", Arrays.toString(syncAnswer));



        myDatabase.close();
    }

    public String scoreCalculation(int correctAnswer, float stu_playTime ){
        if (correctAnswer <= 0){
            return "0";
        }else {
            Log.d("  ", Arrays.toString(syncAnswer));

            double test = ((double) (correctAnswer * 5) + ((double) (stu_playTime) / 6 ));
            total_seven_round_marks += test;
            return String.format("%.02f", test);

        }

    }

    public int [] fetchDataForSync() {
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

        int roundNo = 1;
        for (roundNo = 1 ; roundNo <8 ; roundNo++) {

            String sql = "SELECT * FROM android_score_table where round = " + roundNo + " ; ";
            Cursor c = null;// d = null;

            try {
                c = myDatabase.rawQuery(sql, null);
                Log.d("myLog", "raw query not error in menu");
            } catch (Exception e) {
                Log.d("myLog", "raw query error in menu");
                e.printStackTrace();
            }


            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                syncData[i++] = c.getInt(c.getColumnIndex("currect_answer"));
                syncData[i++] = c.getInt(c.getColumnIndex("time"));
            }

        }
        i = 0;

        Log.d("Data fetch call", "Calll");

        myDatabase.close();

        return syncData;
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
}
