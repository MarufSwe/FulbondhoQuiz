package com.example.khanmaruf.fulbondho;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Open_Fulbondhu extends AppCompatActivity {

    private final String phone="phone";


    private final String USER_PHONE="user_phone";
    private final String USER_PASSWORD="user_password";

    Button login_btn;
    Button reg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__fulbondhu);



        login_btn = (Button) findViewById(R.id.login_btn);
        Typeface MyCustomFontLoginBtn = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        login_btn.setTypeface(MyCustomFontLoginBtn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Open_Fulbondhu.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        reg_btn = (Button) findViewById(R.id.reg_btn);
        Typeface MyCustomFontRegBtn = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        reg_btn.setTypeface(MyCustomFontRegBtn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Open_Fulbondhu.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences pref = getApplicationContext().getSharedPreferences("auth", MODE_PRIVATE);
        String phone=pref.getString(USER_PHONE,null);
        String password=pref.getString(USER_PASSWORD,null);
        if (phone !=null && password!=null)
    {
//        pref.getStringSet(USER_PHONE, )
        startActivity(new Intent(Open_Fulbondhu.this,LoginActivity.class));
    }
}


//    //---------------for Internet Connection cheacker---------------------------//
//
//    public boolean isConnected(Context context) {
//
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netinfo = cm.getActiveNetworkInfo();
//
//        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
//            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
//        else return false;
//        } else
//        return false;
//    }
//
//
//    public AlertDialog.Builder buildDialog(Context c) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(c);
//        builder.setTitle("No Internet Connection");
//        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                finish();
//            }
//        });
//
//        return builder;
//    }
}
