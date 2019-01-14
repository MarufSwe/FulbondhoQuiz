package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;

public class result_show extends AppCompatActivity {

    TextView text_view_show_time_round1;
    TextView text_view_show_number_round1;

    String [] question;
    String [] Answer_option_1;
    String [] Answer_option_2;
    String [] Answer_option_3;
    String [] Answer_details;
    int [] Question_answer;
    int [] studentAnswer;
    int stu_playTime;
    float mark = 0;
    int correctAnswer = 0 ;
    int roundNo;
    int[] previousAnswer = new  int[8];

    String PlayingTime;

    Button goBack2Home;


    // -----------------------------Variable declare----------------------------//
    //for question
    TextView questn1, questn2, questn3, questn4, questn5, questn6, questn7, questn8, questn9, questn10;

    //for CheckBox-------------//
    CheckBox Answer_option1, Answer_option2, Answer_option3,
             Answer_Q2_option_1, Answer_Q2_option_2, Answer_Q2_option_3,
             Answer_Q3_option_1, Answer_Q3_option_2, Answer_Q3_option_3,
             Answer_Q4_option_1, Answer_Q4_option_2, Answer_Q4_option_3,
             Answer_Q5_option_1, Answer_Q5_option_2, Answer_Q5_option_3,
             Answer_Q6_option_1, Answer_Q6_option_2, Answer_Q6_option_3,
             Answer_Q7_option_1, Answer_Q7_option_2, Answer_Q7_option_3,
             Answer_Q8_option_1, Answer_Q8_option_2, Answer_Q8_option_3,
             Answer_Q9_option_1, Answer_Q9_option_2, Answer_Q9_option_3,
             Answer_Q10_option_1, Answer_Q10_option_2, Answer_Q10_option_3;


    //for question detail--------------//
    TextView questn1_details, questn2_details, questn3_details, questn4_details,questn5_details,
            questn6_details, questn7_details, questn8_details, questn9_details, questn10_details;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);



        previousAnswer[7] = 0;


        //---------------------------------Casting-----------------------------------//

        text_view_show_time_round1 =(TextView) findViewById(R.id.text_view_show_time_round1);
        text_view_show_number_round1 =(TextView) findViewById(R.id.text_view_show_number_round1);


        questn1 = (TextView) findViewById(R.id.questn1);
        Answer_option1 = (CheckBox) findViewById(R.id.Answer_option1);
        Answer_option2 = (CheckBox) findViewById(R.id.Answer_option2);
        Answer_option3 = (CheckBox) findViewById(R.id.Answer_option3);
        questn1_details = (TextView) findViewById(R.id.questn1_details);


        questn2 = (TextView) findViewById(R.id.questn2);
        Answer_Q2_option_1 = (CheckBox) findViewById(R.id.Answer_Q2_option_1);
        Answer_Q2_option_2 = (CheckBox) findViewById(R.id.Answer_Q2_option_2);
        Answer_Q2_option_3 = (CheckBox) findViewById(R.id.Answer_Q2_option_3);
        questn2_details = (TextView) findViewById(R.id.questn2_details);


        questn3 = (TextView) findViewById(R.id.questn3);
        Answer_Q3_option_1 = (CheckBox) findViewById(R.id.Answer_Q3_option_1);
        Answer_Q3_option_2 = (CheckBox) findViewById(R.id.Answer_Q3_option_2);
        Answer_Q3_option_3 = (CheckBox) findViewById(R.id.Answer_Q3_option_3);
        questn3_details = (TextView) findViewById(R.id.questn3_details);


        questn4 = (TextView) findViewById(R.id.questn4);
        Answer_Q4_option_1 = (CheckBox) findViewById(R.id.Answer_Q4_option_1);
        Answer_Q4_option_2 = (CheckBox) findViewById(R.id.Answer_Q4_option_2);
        Answer_Q4_option_3 = (CheckBox) findViewById(R.id.Answer_Q4_option_3);
        questn4_details = (TextView) findViewById(R.id.questn4_details);


        questn5 = (TextView) findViewById(R.id.questn5);
        Answer_Q5_option_1 = (CheckBox) findViewById(R.id.Answer_Q5_option_1);
        Answer_Q5_option_2 = (CheckBox) findViewById(R.id.Answer_Q5_option_2);
        Answer_Q5_option_3 = (CheckBox) findViewById(R.id.Answer_Q5_option_3);
        questn5_details = (TextView) findViewById(R.id.questn5_details);


        questn6 = (TextView) findViewById(R.id.questn6);
        Answer_Q6_option_1 = (CheckBox) findViewById(R.id.Answer_Q6_option_1);
        Answer_Q6_option_2 = (CheckBox) findViewById(R.id.Answer_Q6_option_2);
        Answer_Q6_option_3 = (CheckBox) findViewById(R.id.Answer_Q6_option_3);
        questn6_details = (TextView) findViewById(R.id.questn6_details);


        questn7 = (TextView) findViewById(R.id.questn7);
        Answer_Q7_option_1 = (CheckBox) findViewById(R.id.Answer_Q7_option_1);
        Answer_Q7_option_2 = (CheckBox) findViewById(R.id.Answer_Q7_option_2);
        Answer_Q7_option_3 = (CheckBox) findViewById(R.id.Answer_Q7_option_3);
        questn7_details = (TextView) findViewById(R.id.questn7_details);


        questn8 = (TextView) findViewById(R.id.questn8);
        Answer_Q8_option_1 = (CheckBox) findViewById(R.id.Answer_Q8_option_1);
        Answer_Q8_option_2 = (CheckBox) findViewById(R.id.Answer_Q8_option_2);
        Answer_Q8_option_3 = (CheckBox) findViewById(R.id.Answer_Q8_option_3);
        questn8_details = (TextView) findViewById(R.id.questn8_details);


        questn9 = (TextView) findViewById(R.id.questn9);
        Answer_Q9_option_1 = (CheckBox) findViewById(R.id.Answer_Q9_option_1);
        Answer_Q9_option_2 = (CheckBox) findViewById(R.id.Answer_Q9_option_2);
        Answer_Q9_option_3 = (CheckBox) findViewById(R.id.Answer_Q9_option_3);
        questn9_details = (TextView) findViewById(R.id.questn9_details);


        questn10 = (TextView) findViewById(R.id.questn10);
        Answer_Q10_option_1 = (CheckBox) findViewById(R.id.Answer_Q10_option_1);
        Answer_Q10_option_2 = (CheckBox) findViewById(R.id.Answer_Q10_option_2);
        Answer_Q10_option_3 = (CheckBox) findViewById(R.id.Answer_Q10_option_3);
        questn10_details = (TextView) findViewById(R.id.questn10_details);




        Bundle extras = getIntent().getExtras();


        studentAnswer = extras.getIntArray("studentAnswer");
        Question_answer = extras.getIntArray("Question_answer");
        roundNo = extras.getInt("roundNo");



        Log.d("sql ", Arrays.toString(Question_answer));
        Log.d("Student Ans in Result ", Arrays.toString(studentAnswer));
        question = extras.getStringArray("Question");

        stu_playTime = extras.getInt("stu_playTime");

        PlayingTime = extras.getString("playingTime");
        text_view_show_time_round1.setText(PlayingTime);

   // =------------------ Mark Calculation ---------------
        mark = calculationMark(studentAnswer, Question_answer, 1);
        text_view_show_number_round1.setText(""+ mark);



        //.............for question 1................//

       // text_view_question_10.setText("১০। " + Question[9].toString());

        questn1.setText("১। "+ question[0]);
        Typeface Question1_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn1.setTypeface(Question1_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_option1.setText(Answer_option_1[0]);
        Typeface Question1_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_option1.setTypeface(Question1_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_option2.setText(Answer_option_2[0]);
        Typeface Question1_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_option2.setTypeface(Question1_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_option3.setText(Answer_option_3[0]);
        Typeface Question1_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_option3.setTypeface(Question1_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn1_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[0]);
        Typeface QuestionDetails1_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn1_details.setTypeface(QuestionDetails1_round1);


        //.............for question 2................//

        question = extras.getStringArray("Question");
        questn2.setText("২। "+ question[1]);
        Typeface Question2_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn2.setTypeface(Question2_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q2_option_1.setText(Answer_option_1[1]);
        Typeface Question2_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q2_option_1.setTypeface(Question2_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q2_option_2.setText(Answer_option_2[1]);
        Typeface Question2_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q2_option_2.setTypeface(Question2_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q2_option_3.setText(Answer_option_3[1]);
        Typeface Question2_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q2_option_3.setTypeface(Question2_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn2_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[1]);
        Typeface QuestionDetails2_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn2_details.setTypeface(QuestionDetails2_round1);


        //.............for question 3................//

        question = extras.getStringArray("Question");
        questn3.setText("৩। "+ question[2]);
        Typeface Question3_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn3.setTypeface(Question3_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q3_option_1.setText(Answer_option_1[2]);
        Typeface Question3_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q3_option_1.setTypeface(Question3_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q3_option_2.setText(Answer_option_2[2]);
        Typeface Question3_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q3_option_2.setTypeface(Question3_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q3_option_3.setText(Answer_option_3[2]);
        Typeface Question3_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q3_option_3.setTypeface(Question3_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn3_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[2]);
        Typeface QuestionDetails3_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn3_details.setTypeface(QuestionDetails3_round1);



        //.............for question 4................//

        question = extras.getStringArray("Question");
        questn4.setText("৪। "+ question[3]);
        Typeface Question4_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn4.setTypeface(Question4_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q4_option_1.setText(Answer_option_1[3]);
        Typeface Question4_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q4_option_1.setTypeface(Question4_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q4_option_2.setText(Answer_option_2[3]);
        Typeface Question4_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q4_option_2.setTypeface(Question4_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q4_option_3.setText(Answer_option_3[3]);
        Typeface Question4_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q4_option_3.setTypeface(Question4_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn4_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[3]);
        Typeface QuestionDetails4_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn4_details.setTypeface(QuestionDetails4_round1);


        //.............for question 5................//

        question = extras.getStringArray("Question");
        questn5.setText("৫। "+ question[4]);
        Typeface Question5_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn5.setTypeface(Question5_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q5_option_1.setText(Answer_option_1[4]);
        Typeface Question5_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q5_option_1.setTypeface(Question5_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q5_option_2.setText(Answer_option_2[4]);
        Typeface Question5_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q5_option_2.setTypeface(Question5_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q5_option_3.setText(Answer_option_3[4]);
        Typeface Question5_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q5_option_3.setTypeface(Question5_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn5_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[4]);
        Typeface QuestionDetails5_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn5_details.setTypeface(QuestionDetails5_round1);



        //.............for question 6................//

        question = extras.getStringArray("Question");
        questn6.setText("৬। "+ question[5]);
        Typeface Question6_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn6.setTypeface(Question6_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q6_option_1.setText(Answer_option_1[5]);
        Typeface Question6_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q6_option_1.setTypeface(Question6_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q6_option_2.setText(Answer_option_2[5]);
        Typeface Question6_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q6_option_2.setTypeface(Question6_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q6_option_3.setText(Answer_option_3[5]);
        Typeface Question6_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q6_option_3.setTypeface(Question6_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn6_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[5]);
        Typeface QuestionDetails6_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn6_details.setTypeface(QuestionDetails6_round1);


        //.............for question 7................//

        question = extras.getStringArray("Question");
        questn7.setText("৭। "+ question[6]);
        Typeface Question7_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn7.setTypeface(Question7_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q7_option_1.setText(Answer_option_1[6]);
        Typeface Question7_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q7_option_1.setTypeface(Question7_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q7_option_2.setText(Answer_option_2[6]);
        Typeface Question7_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q7_option_2.setTypeface(Question7_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q7_option_3.setText(Answer_option_3[6]);
        Typeface Question7_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q7_option_3.setTypeface(Question7_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn7_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[6]);
        Typeface QuestionDetails7_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn7_details.setTypeface(QuestionDetails7_round1);


        //.............for question 8................//

        question = extras.getStringArray("Question");
        questn8.setText("৮। "+ question[7]);
        Typeface Question8_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn8.setTypeface(Question8_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q8_option_1.setText(Answer_option_1[7]);
        Typeface Question8_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q8_option_1.setTypeface(Question8_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q8_option_2.setText(Answer_option_2[7]);
        Typeface Question8_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q8_option_2.setTypeface(Question8_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q8_option_3.setText(Answer_option_3[7]);
        Typeface Question8_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q8_option_3.setTypeface(Question8_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn8_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[7]);
        Typeface QuestionDetails8_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn8_details.setTypeface(QuestionDetails8_round1);


        //.............for question 9................//

        question = extras.getStringArray("Question");
        questn9.setText("৯। "+ question[8]);
        Typeface Question9_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn9.setTypeface(Question9_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q9_option_1.setText(Answer_option_1[8]);
        Typeface Question9_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q9_option_1.setTypeface(Question9_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q9_option_2.setText(Answer_option_2[8]);
        Typeface Question9_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q9_option_2.setTypeface(Question9_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q9_option_3.setText(Answer_option_3[8]);
        Typeface Question9_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q9_option_3.setTypeface(Question9_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn9_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[8]);
        Typeface QuestionDetails9_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn9_details.setTypeface(QuestionDetails9_round1);


        //.............for question 10................//

        question = extras.getStringArray("Question");
        questn10.setText("১০। "+ question[9]);
        Typeface Question10_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn10.setTypeface(Question10_round1);

        Answer_option_1 = extras.getStringArray("Answer_option_1");
        Answer_Q10_option_1.setText(Answer_option_1[9]);
        Typeface Question10_round1_CB1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q10_option_1.setTypeface(Question10_round1_CB1);

        Answer_option_2 = extras.getStringArray("Answer_option_2");
        Answer_Q10_option_2.setText(Answer_option_2[9]);
        Typeface Question10_round1_CB2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q10_option_2.setTypeface(Question10_round1_CB2);

        Answer_option_3 = extras.getStringArray("Answer_option_3");
        Answer_Q10_option_3.setText(Answer_option_3[9]);
        Typeface Question10_round1_CB3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        Answer_Q10_option_3.setTypeface(Question10_round1_CB3);

        Answer_details = extras.getStringArray("Answer_details");
        questn10_details.setText("সংক্ষিপ্ত বর্ণনা: "+ Answer_details[9]);
        Typeface QuestionDetails10_round1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        questn10_details.setTypeface(QuestionDetails10_round1);




// ---------------------------For Question CheckBox Color--------------------------//


        //Qestion 1

        if(studentAnswer[0] == Question_answer[0]){
            if(studentAnswer[0] == 1) {
                Answer_option1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[0] == 2) {
                Answer_option2.setTextColor(Color.parseColor("#00cd00"));
            } else {
                Answer_option3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[0] == 1) {
                Answer_option1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[0] == 2) {
                Answer_option2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[0] == 3){
                Answer_option3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[0] == 1) {
                Answer_option1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[0] == 2) {
                Answer_option2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[0] == 3){
                Answer_option3.setTextColor(Color.parseColor("#00cd00"));
            }

        }



        //Qestion 2

           if(studentAnswer[1] == Question_answer[1]){
                if(studentAnswer[1] == 1) {
                Answer_Q2_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[1] == 2) {
                   Answer_Q2_option_2.setTextColor(Color.parseColor("#00cd00"));
                } else {
                Answer_Q2_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[1] == 1) {
                Answer_Q2_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[1] == 2) {
                Answer_Q2_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[1] == 3){
                Answer_Q2_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[1] == 1) {
                Answer_Q2_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[1] == 2) {
                Answer_Q2_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[1] == 3){
                Answer_Q2_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }


        //Qestion 3

        if(studentAnswer[2] == Question_answer[2]){
            if(studentAnswer[2] == 1) {
                Answer_Q3_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[2] == 2) {
                Answer_Q3_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else {
                Answer_Q3_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[2] == 1) {
                Answer_Q3_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[2] == 2) {
                Answer_Q3_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[2] == 3){
                Answer_Q3_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[2] == 1) {
                Answer_Q3_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[2] == 2) {
                Answer_Q3_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[2] == 3){
                Answer_Q3_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }

        //Qestion 4

        if(studentAnswer[3] == Question_answer[3]){
            if(studentAnswer[3] == 1) {
                Answer_Q4_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[3] == 2) {
                Answer_Q4_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else {
                Answer_Q4_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[3] == 1) {
                Answer_Q4_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[3] == 2) {
                Answer_Q4_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[3] == 3){
                Answer_Q4_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[3] == 1) {
                Answer_Q4_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[3] == 2) {
                Answer_Q4_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[3] == 3){
                Answer_Q4_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }


        //Qestion 5

        if(studentAnswer[4] == Question_answer[4]){
            if(studentAnswer[4] == 1) {
                Answer_Q5_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[4] == 2) {
                Answer_Q5_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[4] == 3){
                Answer_Q5_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[4] == 1) {
                Answer_Q5_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[4] == 2) {
                Answer_Q5_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[4] == 3){
                Answer_Q5_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[4] == 1) {
                Answer_Q5_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[4] == 2) {
                Answer_Q5_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[4] == 3){
                Answer_Q5_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }


        //Qestion 6

        if(studentAnswer[5] == Question_answer[5]){
            if(studentAnswer[5] == 1) {
                Answer_Q6_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[5] == 2) {
                Answer_Q6_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[5] == 3){
                Answer_Q6_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[5] == 1) {
                Answer_Q6_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[5] == 2) {
                Answer_Q6_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[5] == 3){
                Answer_Q6_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[5] == 1) {
                Answer_Q6_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[5] == 2) {
                Answer_Q6_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[5] == 3){
                Answer_Q6_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }


        //Qestion 7

        if(studentAnswer[6] == Question_answer[6]){
            if(studentAnswer[6] == 1) {
                Answer_Q7_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[6] == 2) {
                Answer_Q7_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[6] == 3){
                Answer_Q7_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[6] == 1) {
                Answer_Q7_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[6] == 2) {
                Answer_Q7_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[6] == 3) {
                Answer_Q7_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[6] == 1) {
                Answer_Q7_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[6] == 2) {
                Answer_Q7_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[6] == 3){
                Answer_Q7_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }


        //Qestion 8

        if(studentAnswer[7] == Question_answer[7]){
            if(studentAnswer[7] == 1) {
                Answer_Q8_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[7] == 2) {
                Answer_Q8_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[7] == 3){
                Answer_Q8_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[7] == 1) {
                Answer_Q8_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[7] == 2) {
                Answer_Q8_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[7] == 3) {
                Answer_Q8_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[7] == 1) {
                Answer_Q8_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[7] == 2) {
                Answer_Q8_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[7] == 3){
                Answer_Q8_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }



        //Qestion 9

        if(studentAnswer[8] == Question_answer[8]){
            if(studentAnswer[8] == 1) {
                Answer_Q9_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[8] == 2) {
                Answer_Q9_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[8] == 3){
                Answer_Q9_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[8] == 1) {
                Answer_Q9_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[8] == 2) {
                Answer_Q9_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[0] == 3){
                Answer_Q9_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[8] == 1) {
                Answer_Q9_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[8] == 2) {
                Answer_Q9_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[8] == 3){
                Answer_Q9_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        }


        //Qestion 10

        if(studentAnswer[9] == Question_answer[9]){
            if(studentAnswer[9] == 1) {
                Answer_Q10_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[9] == 2) {
                Answer_Q10_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if(studentAnswer[9] == 3){
                Answer_Q10_option_3.setTextColor(Color.parseColor("#00cd00"));
            }
        } else {

            if(studentAnswer[9] == 1) {
                Answer_Q10_option_1.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[9] == 2) {
                Answer_Q10_option_2.setTextColor(Color.parseColor("#FF0000"));
            } else if(studentAnswer[9] == 3) {
                Answer_Q10_option_3.setTextColor(Color.parseColor("#FF0000"));
            }

            if(Question_answer[9] == 1) {
                Answer_Q10_option_1.setTextColor(Color.parseColor("#00cd00"));
            } else if(Question_answer[9] == 2) {
                Answer_Q10_option_2.setTextColor(Color.parseColor("#00cd00"));
            } else if (Question_answer[9] == 3){
                Answer_Q10_option_3.setTextColor(Color.parseColor("#00cd00"));
            }

        }



        fetchData();
        Log.d("Call update", correctAnswer + " >= " + previousAnswer[roundNo - 1]);
        if(correctAnswer > previousAnswer[roundNo - 1]) {
            updatePlayerScore();
        }


        goBack2Home = (Button) findViewById(R.id.goBack2Home);
        goBack2Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(result_show.this, All_Round.class);
                startActivity(intent);
            }
        });
        //
    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    private float calculationMark (int [] studentAnswer, int [] questionAnswer, int mark ) {

        for(int i = 0 ; i< 10 ; i++) {
            if(studentAnswer[i] == questionAnswer [i]){
                correctAnswer++;
            }
        }

        return correctAnswer * 5;
    }

// Fetch Data --------------------------------------------
    public void fetchData() {
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


        String sql = "SELECT currect_answer FROM android_score_table";

        // String sql = "SELECT currect_answer FROM android_score_table where round = "+ roundNo+" ; ";

        Cursor c = null;// d = null;

        try {
            c = myDatabase.rawQuery(sql, null);
            Log.d("myLog", "raw query not error in menu");
        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }


        Log.d("time", String.valueOf((int)(System.currentTimeMillis())) );

        int i = 0;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            previousAnswer[i++] = c.getInt(c.getColumnIndex("currect_answer"));

        }

        Log.d("Data fetch call", "Calll");

        myDatabase.close();
    }

    // Update Player info ----------------------------

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


        String sql2 = "Update android_score_table set currect_answer = "+correctAnswer+" where round = "+roundNo+" ;";
//        Log.d("sql ", sql2);
        Log.d("Currect answer ", String.valueOf(correctAnswer));



        try {

            Log.d("previous round ", String.valueOf(previousAnswer[roundNo - 1]));

            Log.d(" round ", String.valueOf(roundNo));
            Log.d(" Arry round ", String.valueOf(Arrays.toString(previousAnswer)));


            if(correctAnswer > 3 && previousAnswer[roundNo] == -1 && roundNo != 7){
                String sql4 = "Update android_score_table set currect_answer = 0 where round = "+(roundNo+1)+" ;";
                Log.d("New Round open ", sql4);
                myDatabase.execSQL(sql4);
                myDatabase.execSQL(sql2);


            } else if (correctAnswer > previousAnswer[roundNo - 1]){
                Log.d("New Score update open ", sql2);
                myDatabase.execSQL(sql2);

            }

            stu_playTime = 300 - stu_playTime;
             sql2 = "Update android_score_table set time = "+stu_playTime+" where round = "+roundNo+" ;";
            myDatabase.execSQL(sql2);

            Log.d("myLog", "raw query not error in menu");
        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }


        myDatabase.close();
    }
}
