package com.example.khanmaruf.fulbondho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Round_Seven extends AppCompatActivity {

    Button seventh_round_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round__seven);

        seventh_round_btn = (Button)findViewById(R.id.seventh_round_btn);

        seventh_round_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Round_Seven.this, HomeActivity.class);
                intent.putExtra("round", 7);
                startActivity(intent);

            }
        });
    }
}
