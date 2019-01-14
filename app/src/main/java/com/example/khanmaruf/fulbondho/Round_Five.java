package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Round_Five extends AppCompatActivity {

    Button fifth_round_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round__five);

        fifth_round_btn = (Button)findViewById(R.id.fifth_round_btn);

        fifth_round_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Round_Five.this, HomeActivity.class);
                intent.putExtra("round", 5);
                startActivity(intent);

            }
        });
    }
}
