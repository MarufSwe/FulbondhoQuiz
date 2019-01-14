package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Arrays;

public class Round_One extends AppCompatActivity {

    private Button first_round_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round__one);


        first_round_btn = (Button)findViewById(R.id.first_round_btn);

        first_round_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Round_One.this, HomeActivity.class);
                intent.putExtra("round", 1);
                startActivity(intent);

            }
        });
    }
}
