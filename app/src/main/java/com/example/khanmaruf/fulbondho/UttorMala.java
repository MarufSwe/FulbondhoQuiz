package com.example.khanmaruf.fulbondho;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Arrays;

public class UttorMala extends AppCompatActivity {

    RatingBar ratingBar;
    TextView ratingBar_value;
    TextView uttorMala_totalTime;
    TextView uttorMala_allAnswer;
    TextView uttorMala_totalNumber;
    TextView round_no_uttorMala;

    Button uttorMala_nextRound_btn;
    Button uttorMala_againPlay_btn;


    String [] question;
    String [] Answer_option_1;
    String [] Answer_option_2;
    String [] Answer_option_3;
    String [] Answer_details;
    int [] Question_answer;
    int [] studentAnswer;
    double mark = 0;
    int correctAnswer = 0 ;
    int roundNo , previousAnswer;
    String [] roundName = {"", "১ম রাউন্ড", "২য় রাউন্ড", "৩য় রাউন্ড", "৪র্থ রাউন্ড", "৫ম রাউন্ড", "৬ষ্ঠ রাউন্ড", "৭ম রাউন্ড"};
    int stu_playTime;

    String PlayingTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uttor_mala);
        //Intent intent = new Intent(UttorMala.this, result_show.class);


        onBackPressed();

        uttorMala_againPlay_btn = (Button) findViewById(R.id.uttorMala_againPlay_btn);
        uttorMala_againPlay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UttorMala.this, HomeActivity.class);
                intent.putExtra("round", roundNo);


                startActivity(intent);
            }
        });

        uttorMala_nextRound_btn = (Button) findViewById(R.id.uttorMala_nextRound_btn);//eta kiser

        uttorMala_nextRound_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        Intent intent = new Intent(UttorMala.this, result_show.class);

                intent.putExtra("Question", question);
                intent.putExtra("Answer_option_1", Answer_option_1);
                intent.putExtra("Answer_option_2", Answer_option_2);
                intent.putExtra("Answer_option_3", Answer_option_3);
                intent.putExtra("Answer_details", Answer_details);
                intent.putExtra("playingTime", PlayingTime);
                intent.putExtra("Question_answer", Question_answer);
                intent.putExtra("studentAnswer", studentAnswer);
                intent.putExtra("roundNo", roundNo);
                intent.putExtra("stu_playTime",stu_playTime);

                startActivity(intent);

            }
        });


        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setNumStars(3);
        ratingBar.setStepSize(1.0f);
        ratingBar.setMax(3);
        ratingBar.setRating(0.0f);


        //-----------------casting-----------------------//

        uttorMala_totalTime = (TextView) findViewById(R.id.uttorMala_totalTime);
        uttorMala_allAnswer = (TextView) findViewById(R.id.uttorMala_allAnswer);
        uttorMala_totalNumber = (TextView) findViewById(R.id.uttorMala_totalNumber);

        round_no_uttorMala = (TextView) findViewById(R.id.round_no_uttorMala);

        Bundle extras = getIntent().getExtras();

        studentAnswer = extras.getIntArray("studentAnswer");
        Question_answer = extras.getIntArray("Question_answer");
        roundNo = extras.getInt("roundNo");
        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_details = extras.getStringArray("Answer_details");
        question = extras.getStringArray("Question");
        PlayingTime = extras.getString("playingTime");
        stu_playTime = extras.getInt("stu_playTime");

        // =------------------ Mark Calculation ---------------
        mark = calculationMark(studentAnswer, Question_answer);

        //----------------for sync------------------///
        if(isConnected(getApplicationContext()) == true){
           // Toast.makeText(this, "net on: " ,Toast.LENGTH_LONG).show();
            syncData();
        }else {
            Toast.makeText(this, "ইন্টারনেট অন করে আপনার নাম্বার আপডেট করুন: " ,Toast.LENGTH_LONG).show();
        }
        
        //--------------------------------------------------------------//

        round_no_uttorMala.setText(roundName[roundNo]);

        uttorMala_totalTime.setText(PlayingTime);
        uttorMala_allAnswer.setText(String.valueOf(correctAnswer));
        if(correctAnswer == 0) {
            uttorMala_totalNumber.setText("0");

        } else {
            uttorMala_totalNumber.setText(String.valueOf((int) mark));
        }
    }

    private double calculationMark (int [] studentAnswer, int [] questionAnswer) {

        Log.d("playing time", PlayingTime);

        for(int i = 0 ; i < 10 ; i++) {
            if(studentAnswer[i] == questionAnswer [i]) {
                correctAnswer++;

                if (correctAnswer > 7) {
                    ratingBar.setRating(3.0f);
                } else if (correctAnswer > 5) {
                    ratingBar.setRating(2.0f);
                } else if (correctAnswer > 3) {
                    ratingBar.setRating(1.0f);
                }
//                else if(correctAnswer > 2) {
//                    ratingBar.setRating(0.0f);
//                }
                else if (correctAnswer > -1){
                    ratingBar.setRating(0.0f);
                }


                //Play again
                if (correctAnswer < 5)
                {
                    uttorMala_againPlay_btn.setVisibility(View.VISIBLE);
                }else {
                    uttorMala_againPlay_btn.setVisibility(View.INVISIBLE);
                }
            }
        }

       // return (double) (correctAnswer * 5);
        return  ((double) (correctAnswer * 5) + ((double) (300 - stu_playTime) / 6 ));


    }

    @Override
    public void onBackPressed() {}


    //----------------Check Internet Connection------------------------//

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }


    public String getStudentPhoneNo(){
        SharedPreferences sharedPreferences = getSharedPreferences("student id", getApplication().MODE_PRIVATE);

        String phone = sharedPreferences.getString("phone","");
        Toast.makeText(this, "phones: "+ phone, Toast.LENGTH_LONG).show();
        return  phone;
    }

    public  void syncData(){

      String dataForSync;

        dataForSync = roundNo + "," + correctAnswer + "," + stu_playTime;
       // String myURL = "http://fulbondhu.net/sheiblu/api/v1/sy3.php?phone=" + getStudentPhoneNo() + "&data=" + dataForSync;

        String myURL =  "https://sererl.com/api/v1/sync_single_round.php?phone=" + getStudentPhoneNo() + "&data=" + dataForSync;
        Log.d("MySQL Show", myURL);

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
}
