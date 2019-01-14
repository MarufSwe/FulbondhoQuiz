package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Process;
import android.widget.Button;

import java.io.IOException;

public class LogOut extends AppCompatActivity {

    SharedPreferences pref;
    private final String USER_PHONE="user_phone";
    private final String USER_PASSWORD="user_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        Log.d("myLog", "Update call");

        updatePlayerScore();

        Log.d("myLog", "update finish");


        pref = getApplicationContext().getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
      //  editor.putString(USER_PHONE,null);
       // editor.putString(USER_PASSWORD,null);
        editor.putString("phone",null);
        editor.apply();
        editor.clear();
        editor.commit();

        updatePlayerScore();

       // startActivity(new Intent(LogOut.this,LoginActivity.class));
        //finish();
    }


    public void  updatePlayerScore() {
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
                if (i != 1){
                    Log.d("myLog", "Update...............");

                    myDatabase.execSQL("Update android_score_table set currect_answer = -1 where round = "+ i +" ;");
                }else{
                    myDatabase.execSQL("Update android_score_table set currect_answer = 0 where round = "+ i +" ;");
                }


            //    myDatabase.execSQL("Update android_score_table set currect_answer = 0 where round = "+ i +" ;");
                myDatabase.execSQL("Update android_score_table set time = 0 where round = "+ i +" ;");
                myDatabase.execSQL("Update android_score_table set number_of_play = 0 where round = "+ i +" ;");
               // String a = "Update android_score_table set currect_answer = " + currectAnswer[i-1] + " where round = "+ i +" ;" ;
            }



        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }


        myDatabase.close();
    }


}
