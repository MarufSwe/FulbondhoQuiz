package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    int noOfQuestionAnswer = 0;
    boolean timeOver = false;
    String playingTime;

    String []  roundName = { "১ম রাউন্ড" , "২য় রাউন্ড" ,"৩য় রাউন্ড", "৪র্থ রাউন্ড",
                              "৫ম রাউন্ড" , "৬ষ্ঠ রাউন্ড","৭ম রাউন্ড" };


    int [] question_id;
    String [] Question;
    String [] Answer_option_1;
    String [] Answer_option_2;
    String [] Answer_option_3;
    int [] Question_answer;
    String [] Answer_details;
    int [] student_answer;

    private static final long START_TIME_IN_MILLIS = 300000;  //5 minute

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning = true;
    private int roundNo=0 ;
    private String [] dbName = {"fulbondhoQL1", "fulbondhoQL2", "fulbondhoQL3" ,"fulbondhoQL4", "fulbondhoQL5","fulbondhoQL6", "fulbondhoQL7"};
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    int stu_playTime;

    TextView round_no;

    Button question_button,
            submit_result_btn;

    TextView text_view_question;
    CheckBox checkBox_one, checkBox_two, checkBox_three;

    TextView text_view_question_2;
    CheckBox checkBox_one_2, checkBox_two_2, checkBox_three_2;


    TextView text_view_question_3;
    CheckBox checkBox_one_3, checkBox_two_3, checkBox_three_3;

    TextView text_view_question_4;
    CheckBox checkBox_one_4, checkBox_two_4, checkBox_three_4;

    TextView text_view_question_5;
    CheckBox checkBox_one_5, checkBox_two_5, checkBox_three_5;

    TextView text_view_question_6;
    CheckBox checkBox_one_6, checkBox_two_6, checkBox_three_6;

    TextView text_view_question_7;
    CheckBox checkBox_one_7, checkBox_two_7, checkBox_three_7;

    TextView text_view_question_8;
    CheckBox checkBox_one_8, checkBox_two_8, checkBox_three_8;

    TextView text_view_question_9;
    CheckBox checkBox_one_9, checkBox_two_9, checkBox_three_9;

    TextView text_view_question_10;
    CheckBox checkBox_one_10, checkBox_two_10, checkBox_three_10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        // hideNavigationBar();



        Bundle extras = getIntent().getExtras();
        roundNo = extras.getInt("round");

       // roundNo =1;

        UploadNumberOfPlay();


        round_no = (TextView) findViewById(R.id.round_no);
       // round_no.setText(String.valueOf(roundNo));
        round_no.setText(roundName[roundNo-1]);
        //question 1

        text_view_question = findViewById(R.id.text_view_question_1);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question.setTypeface(MyCustomFont);

        checkBox_one = findViewById(R.id.checkBox_one_1);
        Typeface MyCustomFontQ1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one.setTypeface(MyCustomFontQ1_CB1);

        checkBox_two = findViewById(R.id.checkBox_two_1);
        Typeface MyCustomFontQ1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two.setTypeface(MyCustomFontQ1_CB2);

        checkBox_three = findViewById(R.id.checkBox_three_1);
        Typeface MyCustomFontQ1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three.setTypeface(MyCustomFontQ1_CB3);



        //------------------------Click Just One CheckBox-------------------------//

        //Question 1

        checkBox_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one.isChecked()){
                    checkBox_two.setChecked(false);
                    checkBox_three.setChecked(false);
                }
            }
        });

        checkBox_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(checkBox_two.isChecked()){
                   checkBox_one.setChecked(false);
                   checkBox_three.setChecked(false);
               }
            }
        });

        checkBox_three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three.isChecked()){
                    checkBox_two.setChecked(false);
                    checkBox_one.setChecked(false);
                }
            }
        });

        //question 2

        text_view_question_2 = findViewById(R.id.text_view_question_2);
        Typeface MyCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_2.setTypeface(MyCustomFont2);

        checkBox_one_2 = findViewById(R.id.checkBox_one_2);
        Typeface MyCustomFontQ2_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_2.setTypeface(MyCustomFontQ2_CB1);

        checkBox_two_2 = findViewById(R.id.checkBox_two_2);
        Typeface MyCustomFontQ2_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_2.setTypeface(MyCustomFontQ2_CB2);

        checkBox_three_2 = findViewById(R.id.checkBox_three_2);
        Typeface MyCustomFontQ2_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_2.setTypeface(MyCustomFontQ2_CB3);


        //Question 2

        checkBox_one_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_2.isChecked()){
                    checkBox_two_2.setChecked(false);
                    checkBox_three_2.setChecked(false);
                }
            }
        });

        checkBox_two_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_2.isChecked()){
                    checkBox_one_2.setChecked(false);
                    checkBox_three_2.setChecked(false);
                }
            }
        });

        checkBox_three_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_2.isChecked()){
                    checkBox_two_2.setChecked(false);
                    checkBox_one_2.setChecked(false);
                }
            }
        });

        //question 3

        text_view_question_3 = findViewById(R.id.text_view_question_3);
        Typeface MyCustomFont3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_3.setTypeface(MyCustomFont3);

        checkBox_one_3 = findViewById(R.id.checkBox_one_3);
        Typeface MyCustomFontQ3_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_3.setTypeface(MyCustomFontQ3_CB1);

        checkBox_two_3 = findViewById(R.id.checkBox_two_3);
        Typeface MyCustomFontQ3_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_3.setTypeface(MyCustomFontQ3_CB2);

        checkBox_three_3 = findViewById(R.id.checkBox_three_3);
        Typeface MyCustomFontQ3_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_3.setTypeface(MyCustomFontQ3_CB3);


        //Question 3

        checkBox_one_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_3.isChecked()){
                    checkBox_two_3.setChecked(false);
                    checkBox_three_3.setChecked(false);
                }
            }
        });

        checkBox_two_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_3.isChecked()){
                    checkBox_one_3.setChecked(false);
                    checkBox_three_3.setChecked(false);
                }
            }
        });

        checkBox_three_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_3.isChecked()){
                    checkBox_two_3.setChecked(false);
                    checkBox_one_3.setChecked(false);
                }
            }
        });


      //Question 4

        text_view_question_4 = findViewById(R.id.text_view_question_4);
        Typeface MyCustomFont4 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_4.setTypeface(MyCustomFont4);

        checkBox_one_4 = findViewById(R.id.checkBox_one_4);
        Typeface MyCustomFontQ4_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_4.setTypeface(MyCustomFontQ4_CB1);

        checkBox_two_4 = findViewById(R.id.checkBox_two_4);
        Typeface MyCustomFontQ4_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_4.setTypeface(MyCustomFontQ4_CB2);

        checkBox_three_4 = findViewById(R.id.checkBox_three_4);
        Typeface MyCustomFontQ4_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_4.setTypeface(MyCustomFontQ4_CB3);


        //Question 4

        checkBox_one_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_4.isChecked()){
                    checkBox_two_4.setChecked(false);
                    checkBox_three_4.setChecked(false);
                }
            }
        });

        checkBox_two_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_4.isChecked()){
                    checkBox_one_4.setChecked(false);
                    checkBox_three_4.setChecked(false);
                }
            }
        });

        checkBox_three_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_4.isChecked()){
                    checkBox_two_4.setChecked(false);
                    checkBox_one_4.setChecked(false);
                }
            }
        });


        //Question 5

        text_view_question_5 = findViewById(R.id.text_view_question_5);
        Typeface MyCustomFont5 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_5.setTypeface(MyCustomFont5);

        checkBox_one_5 = findViewById(R.id.checkBox_one_5);
        Typeface MyCustomFontQ5_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_5.setTypeface(MyCustomFontQ5_CB1);

        checkBox_two_5 = findViewById(R.id.checkBox_two_5);
        Typeface MyCustomFontQ5_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_5.setTypeface(MyCustomFontQ5_CB2);

        checkBox_three_5 = findViewById(R.id.checkBox_three_5);
        Typeface MyCustomFontQ5_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_5.setTypeface(MyCustomFontQ5_CB3);

        //Question 5

        checkBox_one_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_5.isChecked()){
                    checkBox_two_5.setChecked(false);
                    checkBox_three_5.setChecked(false);
                }
            }
        });

        checkBox_two_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_5.isChecked()){
                    checkBox_one_5.setChecked(false);
                    checkBox_three_5.setChecked(false);
                }
            }
        });

        checkBox_three_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_5.isChecked()){
                    checkBox_two_5.setChecked(false);
                    checkBox_one_5.setChecked(false);
                }
            }
        });



        //question 6

        text_view_question_6 = findViewById(R.id.text_view_question_6);
        Typeface MyCustomFont6 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_6.setTypeface(MyCustomFont6);

        checkBox_one_6 = findViewById(R.id.checkBox_one_6);
        Typeface MyCustomFontQ6_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_6.setTypeface(MyCustomFontQ6_CB1);

        checkBox_two_6 = findViewById(R.id.checkBox_two_6);
        Typeface MyCustomFontQ6_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_6.setTypeface(MyCustomFontQ6_CB2);

        checkBox_three_6 = findViewById(R.id.checkBox_three_6);
        Typeface MyCustomFontQ6_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_6.setTypeface(MyCustomFontQ6_CB3);

        //Question 6

        checkBox_one_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_6.isChecked()){
                    checkBox_two_6.setChecked(false);
                    checkBox_three_6.setChecked(false);
                }
            }
        });

        checkBox_two_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_6.isChecked()){
                    checkBox_one_6.setChecked(false);
                    checkBox_three_6.setChecked(false);
                }
            }
        });

        checkBox_three_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_6.isChecked()){
                    checkBox_two_6.setChecked(false);
                    checkBox_one_6.setChecked(false);
                }
            }
        });



        //question 7

        text_view_question_7 = findViewById(R.id.text_view_question_7);
        Typeface MyCustomFont7 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_7.setTypeface(MyCustomFont7);


        checkBox_one_7 = findViewById(R.id.checkBox_one_7);
        Typeface MyCustomFontQ7_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_7.setTypeface(MyCustomFontQ7_CB1);

        checkBox_two_7 = findViewById(R.id.checkBox_two_7);
        Typeface MyCustomFontQ7_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_7.setTypeface(MyCustomFontQ7_CB2);

        checkBox_three_7 = findViewById(R.id.checkBox_three_7);
        Typeface MyCustomFontQ7_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_7.setTypeface(MyCustomFontQ7_CB3);


        //Question 7

        checkBox_one_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_7.isChecked()){
                    checkBox_two_7.setChecked(false);
                    checkBox_three_7.setChecked(false);
                }
            }
        });

        checkBox_two_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_7.isChecked()){
                    checkBox_one_7.setChecked(false);
                    checkBox_three_7.setChecked(false);
                }
            }
        });

        checkBox_three_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_7.isChecked()){
                    checkBox_two_7.setChecked(false);
                    checkBox_one_7.setChecked(false);
                }
            }
        });


        //question 8

        text_view_question_8 = findViewById(R.id.text_view_question_8);
        Typeface MyCustomFont8 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_8.setTypeface(MyCustomFont8);


        checkBox_one_8 = findViewById(R.id.checkBox_one_8);
        Typeface MyCustomFontQ8_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_8.setTypeface(MyCustomFontQ8_CB1);

        checkBox_two_8 = findViewById(R.id.checkBox_two_8);
        Typeface MyCustomFontQ8_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_8.setTypeface(MyCustomFontQ8_CB2);

        checkBox_three_8 = findViewById(R.id.checkBox_three_8);
        Typeface MyCustomFontQ8_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_8.setTypeface(MyCustomFontQ8_CB3);


        //Question 8

        checkBox_one_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_8.isChecked()){
                    checkBox_two_8.setChecked(false);
                    checkBox_three_8.setChecked(false);
                }
            }
        });

        checkBox_two_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_8.isChecked()){
                    checkBox_one_8.setChecked(false);
                    checkBox_three_8.setChecked(false);
                }
            }
        });

        checkBox_three_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_8.isChecked()){
                    checkBox_two_8.setChecked(false);
                    checkBox_one_8.setChecked(false);
                }
            }
        });

        //question 9

        text_view_question_9 = findViewById(R.id.text_view_question_9);
        Typeface MyCustomFont9 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_9.setTypeface(MyCustomFont9);

        checkBox_one_9 = findViewById(R.id.checkBox_one_9);
        Typeface MyCustomFontQ9_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_9.setTypeface(MyCustomFontQ9_CB1);

        checkBox_two_9 = findViewById(R.id.checkBox_two_9);
        Typeface MyCustomFontQ9_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_9.setTypeface(MyCustomFontQ9_CB2);

        checkBox_three_9 = findViewById(R.id.checkBox_three_9);
        Typeface MyCustomFontQ9_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_9.setTypeface(MyCustomFontQ9_CB3);


        //Question 9

        checkBox_one_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_9.isChecked()){
                    checkBox_two_9.setChecked(false);
                    checkBox_three_9.setChecked(false);
                }
            }
        });

        checkBox_two_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_9.isChecked()){
                    checkBox_one_9.setChecked(false);
                    checkBox_three_9.setChecked(false);
                }
            }
        });

        checkBox_three_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_9.isChecked()){
                    checkBox_two_9.setChecked(false);
                    checkBox_one_9.setChecked(false);
                }
            }
        });

        //question 10

        text_view_question_10 = findViewById(R.id.text_view_question_10);
        Typeface MyCustomFont10 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        text_view_question_10.setTypeface(MyCustomFont10);

        checkBox_one_10 = findViewById(R.id.checkBox_one_10);
        Typeface MyCustomFontQ10_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_one_10.setTypeface(MyCustomFontQ10_CB1);

        checkBox_two_10 = findViewById(R.id.checkBox_two_10);
        Typeface MyCustomFontQ10_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_two_10.setTypeface(MyCustomFontQ10_CB2);

        checkBox_three_10 = findViewById(R.id.checkBox_three_10);
        Typeface MyCustomFontQ10_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        checkBox_three_10.setTypeface(MyCustomFontQ10_CB3);


        //Question 10

        checkBox_one_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_one_10.isChecked()){
                    checkBox_two_10.setChecked(false);
                    checkBox_three_10.setChecked(false);
                }
            }
        });

        checkBox_two_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_two_10.isChecked()){
                    checkBox_one_10.setChecked(false);
                    checkBox_three_10.setChecked(false);
                }
            }
        });

        checkBox_three_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_three_10.isChecked()){
                    checkBox_two_10.setChecked(false);
                    checkBox_one_10.setChecked(false);
                }
            }
        });


        //question_button = findViewById(R.id.question_button);

        submit_result_btn = findViewById(R.id.submit_result_btn);
        Typeface MyCustomFontResultBtn = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        submit_result_btn.setTypeface(MyCustomFontResultBtn);


        submit_result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                goNext();
            }
        });



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


        String sql = "SELECT Id, Question, Answer_option_1, Answer_option_2 , Answer_option_3 , Question_answer, Answer_details FROM "+ dbName[roundNo-1] + " ORDER BY RANDOM() LIMIT 10;";
        Log.d("sql ", sql);


        Cursor c = null;// d = null;

        try {
            c = myDatabase.rawQuery(sql, null);
            Log.d("myLog", "raw query not error in menu");
        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }

        //  Log.d("myLog", String.valueOf(c.getCount()));
        variableInatial(10);


        int i = 0;
        String a;

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {


            // Log.d("myLog.............................", "  " + 1);

            question_id[i] = c.getInt(c.getColumnIndex("Id"));
            Question[i] = c.getString(c.getColumnIndex("Question"));
            Answer_option_1[i] = c.getString(c.getColumnIndex("Answer_option_1"));
            Answer_option_2[i] = c.getString(c.getColumnIndex("Answer_option_2"));
            Answer_option_3[i] = c.getString(c.getColumnIndex("Answer_option_3"));
            Question_answer[i] = c.getInt(c.getColumnIndex("Question_answer"));
            Answer_details[i++] = c.getString(c.getColumnIndex("Answer_details"));
        }

        Log.d("sql ", Arrays.toString(Question_answer));


        myDatabase.close();


        checkBox_one.setText(Answer_option_1[0].toString());
        checkBox_two.setText(Answer_option_2[0].toString());
        checkBox_three.setText(Answer_option_3[0].toString());
        text_view_question.setText("১। " + Question[0].toString());


        checkBox_one_2.setText(Answer_option_1[1].toString());
        checkBox_two_2.setText(Answer_option_2[1].toString());
        checkBox_three_2.setText(Answer_option_3[1].toString());
        text_view_question_2.setText("২। " + Question[1].toString());


        checkBox_one_3.setText(Answer_option_1[2].toString());
        checkBox_two_3.setText(Answer_option_2[2].toString());
        checkBox_three_3.setText(Answer_option_3[2].toString());
        text_view_question_3.setText("৩। " + Question[2].toString());


        checkBox_one_4.setText(Answer_option_1[3].toString());
        checkBox_two_4.setText(Answer_option_2[3].toString());
        checkBox_three_4.setText(Answer_option_3[3].toString());
        text_view_question_4.setText("৪। " + Question[3].toString());


        checkBox_one_5.setText(Answer_option_1[4].toString());
        checkBox_two_5.setText(Answer_option_2[4].toString());
        checkBox_three_5.setText(Answer_option_3[4].toString());
        text_view_question_5.setText("৫। " + Question[4].toString());


        checkBox_one_6.setText(Answer_option_1[5].toString());
        checkBox_two_6.setText(Answer_option_2[5].toString());
        checkBox_three_6.setText(Answer_option_3[5].toString());
        text_view_question_6.setText("৬। " + Question[5].toString());


        checkBox_one_7.setText(Answer_option_1[6].toString());
        checkBox_two_7.setText(Answer_option_2[6].toString());
        checkBox_three_7.setText(Answer_option_3[6].toString());
        text_view_question_7.setText("৭। " + Question[6].toString());


        checkBox_one_8.setText(Answer_option_1[7].toString());
        checkBox_two_8.setText(Answer_option_2[7].toString());
        checkBox_three_8.setText(Answer_option_3[7].toString());
        text_view_question_8.setText("৮। " + Question[7].toString());


        checkBox_one_9.setText(Answer_option_1[8].toString());
        checkBox_two_9.setText(Answer_option_2[8].toString());
        checkBox_three_9.setText(Answer_option_3[8].toString());
        text_view_question_9.setText("৯। " + Question[8].toString());


        checkBox_one_10.setText(Answer_option_1[9].toString());
        checkBox_two_10.setText(Answer_option_2[9].toString());
        checkBox_three_10.setText(Answer_option_3[9].toString());
        text_view_question_10.setText("১০। " + Question[9].toString());


        startTimer();

        updateCountDownText();
    }


    //--------------for CheckBox Color-----------------//

    public  void goNext(){
        playingTime = getTime();
        noOfQuestionAnswer = 1;  //for question all answer


        //Q1

        if (checkBox_one.isChecked()) {
            student_answer[0] = 1;
        } else if (checkBox_two.isChecked()) {
            student_answer[0] = 2;
        } else if (checkBox_three.isChecked()) {
            student_answer[0] = 3;
        } else {
            student_answer[0] = 0;
            noOfQuestionAnswer = 0;
        }


        //Q2

        if (checkBox_one_2.isChecked()) {
            student_answer[1] = 1;
        } else if (checkBox_two_2.isChecked()) {
            student_answer[1] = 2;
        } else if (checkBox_three_2.isChecked()) {
            student_answer[1] = 3;
        } else {
            student_answer[1] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q3

        if (checkBox_one_3.isChecked()) {
            student_answer[2] = 1;
        } else if (checkBox_two_3.isChecked()) {
            student_answer[2] = 2;
        } else if (checkBox_three_3.isChecked()) {
            student_answer[2] = 3;
        } else {
            student_answer[2] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q4

        if (checkBox_one_4.isChecked()) {
            student_answer[3] = 1;
        } else if (checkBox_two_4.isChecked()) {
            student_answer[3] = 2;
        } else if (checkBox_three_4.isChecked()) {
            student_answer[3] = 3;
        } else {
            student_answer[3] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q5

        if (checkBox_one_5.isChecked()) {
            student_answer[4] = 1;
        } else if (checkBox_two_5.isChecked()) {
            student_answer[4] = 2;
        } else if (checkBox_three_5.isChecked()) {
            student_answer[4] = 3;
        } else {
            student_answer[4] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q6

        if (checkBox_one_6.isChecked()) {
            student_answer[5] = 1;
        } else if (checkBox_two_6.isChecked()) {
            student_answer[5] = 2;
        } else if (checkBox_three_6.isChecked()) {
            student_answer[5] = 3;
        } else {
            student_answer[5] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q7

        if (checkBox_one_7.isChecked()) {
            student_answer[6] = 1;
        } else if (checkBox_two_7.isChecked()) {
            student_answer[6] = 2;
        } else if (checkBox_three_7.isChecked()) {
            student_answer[6] = 3;
        } else {
            student_answer[6] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q8

        if (checkBox_one_8.isChecked()) {
            student_answer[7] = 1;
        } else if (checkBox_two_8.isChecked()) {
            student_answer[7] = 2;
        } else if (checkBox_three_8.isChecked()) {
            student_answer[7] = 3;
        } else {
            student_answer[7] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q9

        if (checkBox_one_9.isChecked()) {
            student_answer[8] = 1;
        } else if (checkBox_two_9.isChecked()) {
            student_answer[8] = 2;
        } else if (checkBox_three_9.isChecked()) {
            student_answer[8] = 3;
        } else {
            student_answer[8] = 0;
            noOfQuestionAnswer = 0;
        }

        //Q10

        if (checkBox_one_10.isChecked()) {
            student_answer[9] = 1;
        } else if (checkBox_two_10.isChecked()) {
            student_answer[9] = 2;
        } else if (checkBox_three_10.isChecked()) {
            student_answer[9] = 3;
        } else {
            student_answer[9] = 0;
            noOfQuestionAnswer = 0;
        }

        //--------------------------
        Log.d("Question ans ", Arrays.toString(Question_answer));
        Log.d("Student ans ", Arrays.toString(student_answer));

        if (noOfQuestionAnswer == 0 && timeOver == false){
            Toast.makeText(getApplicationContext(), "সব গুলি প্রশ্নের উত্তর দিন", Toast.LENGTH_LONG).show();
        }else {
            mTimerRunning = false;


            Intent intent = new Intent(HomeActivity.this, UttorMala.class);

            intent.putExtra("Question", Question);
            intent.putExtra("Answer_option_1", Answer_option_1);
            intent.putExtra("Answer_option_2", Answer_option_2);
            intent.putExtra("Answer_option_3", Answer_option_3);
            intent.putExtra("Answer_details", Answer_details);
            intent.putExtra("playingTime", playingTime);
            intent.putExtra("Question_answer", Question_answer);
            intent.putExtra("studentAnswer", student_answer);
            intent.putExtra("roundNo", roundNo);
            intent.putExtra("stu_playTime", stu_playTime);
            startActivity(intent);

        }

    }




    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }



//--------------------------For Timer--------------------------------//

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                if (mTimerRunning != false){
                    mTimerRunning = false;

                    mTextViewCountDown.setText("সময় শেষ  ");
                    timeOver = true;
                    goNext();
                }
//                mTimerRunning = false;
//
//                mTextViewCountDown.setText("সময় শেষ  ");
//                timeOver = true;
//                goNext();
               // mButtonStartPause.setText("Start");
             //   mButtonStartPause.setVisibility(View.INVISIBLE);
               // mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
//        mButtonStartPause.setText("pause");
//        mButtonReset.setVisibility(View.INVISIBLE);
    }



//------------------------Send Student Play Time (Start)------------------------//

    private String getTime() {
        int minutes = (int) ((START_TIME_IN_MILLIS - mTimeLeftInMillis) / 1000) / 60;
        int seconds = (int) ((START_TIME_IN_MILLIS - mTimeLeftInMillis) / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        stu_playTime = (int)((START_TIME_IN_MILLIS - mTimeLeftInMillis) / 1000);
        return timeLeftFormatted;
    }
//------------------------Send Student Play Time (End)------------------------//




    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }



    private void variableInatial(int valu){
        Log.d("Variable Inatial", "method Start valu is "+valu);
        question_id  = new int [valu];
        Question  = new String[valu];
        Answer_option_1  = new String[valu];
        Answer_option_2  = new String[valu];
        Answer_option_3  = new String[valu];
        Question_answer  = new int [valu];
        Answer_details  = new String[valu];
        student_answer = new int[valu];

        }

    public void UploadNumberOfPlay () {
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


        String sql2 = "Update android_score_table set number_of_play = number_of_play + 1 where round = "+(roundNo)+" ;";
        Log.d("frtyu", sql2);

       // int number_of_play = 1000;
        long number_of_play= System.currentTimeMillis() / 1000;
        String sql3 = "Update android_score_table set last_play_time = "+ number_of_play +" where round = "+(roundNo)+" ;";


        Cursor c = null;//

        try {
             myDatabase.execSQL(sql2);
             myDatabase.execSQL(sql3);

            Log.d("myLog", "raw query not error in menu");
            Log.d("myLog", sql2);
        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }


//        Log.d("Currect Answer ", Arrays.toString(currectAnswer));
//        Log.d("Number Of play ", Arrays.toString(numberOfPlay));
//        Log.d("Currect Answer ", Arrays.toString(syncAnswer));
//        Log.d("Number Of play ", Arrays.toString(takenTime));
//        Log.d("Currect Answer ", Arrays.toString(last_play_time));


        myDatabase.close();
        }
    }

