package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Round_Six extends AppCompatActivity {

    Button sixth_round_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round__six);

        sixth_round_btn = (Button)findViewById(R.id.sixth_round_btn);

        sixth_round_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Round_Six.this, HomeActivity.class);
                intent.putExtra("round", 6);
                startActivity(intent);

            }
        });
    }
}
