package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;

public class All_Round extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String USER_PHONE="user_phone";
    private final String USER_PASSWORD="user_password";
    //shared preferacne for userphone & password
    SharedPreferences pref;
    int [] currectAnswer = new int[7];
    int [] numberOfPlay = new int[7];
    int [] syncAnswer = new int[7];
    int [] whichROund = new int[7];
    int [] takenTime = new int[7];
    String [] last_play_time = new String[7];

  //  int timeNow;
    long timeNow= System.currentTimeMillis() / 1000;


    Button first_round, second_round, third_round, fourth_round, fifth_round, sixth_round, seventh_round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__round);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fetchData();


        onBackPressed();


        first_round = (Button)findViewById(R.id.first_round);
        Typeface MyCustomFont1st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        first_round.setTypeface(MyCustomFont1st_round);

        second_round =(Button)findViewById(R.id.second_round);
        Typeface MyCustomFont2st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        second_round.setTypeface(MyCustomFont2st_round);

        third_round = (Button)findViewById(R.id.third_round);
        Typeface MyCustomFont3st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        third_round.setTypeface(MyCustomFont3st_round);

        fourth_round = (Button)findViewById(R.id.fourth_round);
        Typeface MyCustomFont4st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        fourth_round.setTypeface(MyCustomFont4st_round);

        fifth_round =(Button)findViewById(R.id.fifth_round);
        Typeface MyCustomFont5st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        fifth_round.setTypeface(MyCustomFont5st_round);

        sixth_round = (Button)findViewById(R.id.sixth_round);
        Typeface MyCustomFont6st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        sixth_round.setTypeface(MyCustomFont6st_round);

        seventh_round = (Button)findViewById(R.id.seventh_round);
        Typeface MyCustomFont7st_round = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        seventh_round.setTypeface(MyCustomFont7st_round);

        changeRoundButtonColor1();
        changeRoundButtonColor2();
        changeRoundButtonColor3();
        changeRoundButtonColor4();
        changeRoundButtonColor5();
        changeRoundButtonColor6();
        changeRoundButtonColor7();

        // eikhane DB theke last play time ta niye asbi
        // then current time ta ekta variable e ralhbi
        //then 2ta time theke difference check korbi j 24h hoise kina (variable ta mone kor difTime)
        //91 line er code ta emn hbe => numberOfPlay[0] == 2 || difTime < 24h

        first_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currectAnswer[0] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[0] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[0] == 2 ){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_One.class);
                    startActivity(intent);
                }

            }
        });


        second_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currectAnswer[1] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[1] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[1] == 2){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_two.class);
                    startActivity(intent);
                }


            }
        });


        third_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currectAnswer[2] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[2] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[2] == 2){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_Three.class);
                    startActivity(intent);
                }
            }
        });

        fourth_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currectAnswer[3] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[3] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[3] == 2){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_Four.class);
                    startActivity(intent);

                }
            }
        });

        fifth_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currectAnswer[4] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[4] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[4] == 2){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_Five.class);
                    startActivity(intent);
                }


            }
        });

        sixth_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currectAnswer[5] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[5] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[5] == 2){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_Six.class);
                    startActivity(intent);
                }
            }
        });

        seventh_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currectAnswer[6] == -1){
                    Toast.makeText(getApplication(), "আগের রাউন্ডটি সম্পূর্ণ করুন", Toast.LENGTH_SHORT).show();
                } else if(currectAnswer[6] > 6){
                    Toast.makeText(getApplication(), "এই রাউন্ডটি সম্পূর্ণ হয়েছে", Toast.LENGTH_SHORT).show();
                } else if(numberOfPlay[6] == 2){
                    Toast.makeText(getApplication(), "এক দিনে দুই বারের বেশি খেলা যাবে না", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(All_Round.this, Round_Seven.class);
                    startActivity(intent);
                }

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.all__round, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
    //    if (id == R.id.action_settings) {
      //      return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(All_Round.this, Quiz_Condition.class);
            startActivity(intent);
        }
//        else if (id == R.id.nav_slideshow) {
//            Intent intent = new Intent(All_Round.this, Developers.class);
//            startActivity(intent);
//        }
        else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(All_Round.this, All_Round_Number_Show.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_link) {
            Intent intent = new Intent(All_Round.this, SupportLink.class);
            startActivity(intent);
        } else if (id == R.id.ic_menu_manage) {
            pref = getApplicationContext().getSharedPreferences("auth", MODE_PRIVATE);

            SharedPreferences.Editor editor = pref.edit();
            //  editor.putString(USER_PHONE,null);
            // editor.putString(USER_PASSWORD,null);
            editor.putString("phone",null);
            editor.apply();
            editor.clear();
            editor.commit();
            Log.d("myLog", "Sesh");


            Intent intent = new Intent(All_Round.this, LoginActivity.class);
            startActivity(intent);}

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


    //-------------------------data fetch / data show by DB---------------------//
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

            currectAnswer[i] = c.getInt(c.getColumnIndex("currect_answer"));
            numberOfPlay[i] = c.getInt(c.getColumnIndex("number_of_play"));
            syncAnswer[i] = c.getInt(c.getColumnIndex("sync"));
            takenTime[i] = c.getInt(c.getColumnIndex("time"));
            last_play_time[i] = c.getString(c.getColumnIndex("last_play_time"));
            i++;
        }

        //------------------------Realise Round Lock for Student (Start)---------------------//

        String sql3;
        for (int x = 0; x < 7; x++){
            if ((Long.parseLong(last_play_time[x]) + 86400) <= timeNow){
                numberOfPlay[x] = 0;

                sql3 = "Update android_score_table set number_of_play = 0 where round = "+(x+1)+" ;";
                myDatabase.execSQL(sql3);
                Log.d("Count Lock", String.valueOf(numberOfPlay[x]));
            }
        }
        //------------------------Realise Round Lock for Student (End)---------------------//


        Log.d("Currect Answer ", Arrays.toString(currectAnswer));
        Log.d("Number Of play ", Arrays.toString(numberOfPlay));
        Log.d("          Sync ", Arrays.toString(syncAnswer));
        Log.d("Time for play ", Arrays.toString(takenTime));
        Log.d("Last play time", Arrays.toString(last_play_time));


        myDatabase.close();
    }

    //---------------Round Button Color Change Start-------------------------//

    public void changeRoundButtonColor1(){
        if (currectAnswer[0] > 7){
            first_round.setBackgroundColor(Color.parseColor("#00E676"));  // 2E64FE  A9F5E1  E0E6F8
        }else if (currectAnswer[0] >5){
            first_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            first_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    public void changeRoundButtonColor2(){
        if (currectAnswer[1] > 7){
            second_round.setBackgroundColor(Color.parseColor("#00E676"));
        }else if (currectAnswer[1] >5){
            second_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            second_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    public void changeRoundButtonColor3(){
        if (currectAnswer[2] > 7){
            third_round.setBackgroundColor(Color.parseColor("#00E676"));
        }else if (currectAnswer[2] >5){
            third_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            third_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    public void changeRoundButtonColor4(){
        if (currectAnswer[3] > 7){
            fourth_round.setBackgroundColor(Color.parseColor("#00E676"));
        }else if (currectAnswer[3] >5){
            fourth_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            fourth_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    public void changeRoundButtonColor5(){
        if (currectAnswer[4] > 7){
            fifth_round.setBackgroundColor(Color.parseColor("#00E676"));
        }else if (currectAnswer[4] >5){
            fifth_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            fifth_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    public void changeRoundButtonColor6(){
        if (currectAnswer[5] > 7){
            sixth_round.setBackgroundColor(Color.parseColor("#00E676"));
        }else if (currectAnswer[5] >5){
            sixth_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            sixth_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    public void changeRoundButtonColor7(){
        if (currectAnswer[6] > 7){
            seventh_round.setBackgroundColor(Color.parseColor("#00E676"));
        }else if (currectAnswer[6] >5){
            seventh_round.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else {
            seventh_round.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    //---------------Round Button Color Change End-------------------------//


    @Override
    public void onBackPressed() {}

}
