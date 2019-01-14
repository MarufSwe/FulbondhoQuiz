package com.example.khanmaruf.fulbondho;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class SupportLink extends AppCompatActivity {

    TextView supportLink, condition1, condition2, condition3, condition4, condition5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_link);

//        PDFView p = (PDFView)findViewById(R.id.pdfView);
//        p.fromAsset("demo.pdf").load();

        supportLink = findViewById(R.id.supportLink);
        Typeface MyCustomFontSL = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
        supportLink.setTypeface(MyCustomFontSL);

//        condition1 = findViewById(R.id.condition1);
//        Typeface MyCustomFontC1 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
//        condition1.setTypeface(MyCustomFontC1);
//
//        condition2 = findViewById(R.id.condition2);
//        Typeface MyCustomFontC2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
//        condition2.setTypeface(MyCustomFontC2);
//
//        condition3 = findViewById(R.id.condition3);
//        Typeface MyCustomFontC3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
//        condition3.setTypeface(MyCustomFontC3);
//
//        condition4 = findViewById(R.id.condition4);
//        Typeface MyCustomFontC4 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
//        condition4.setTypeface(MyCustomFontC4);
//
//        condition5 = findViewById(R.id.condition5);
//        Typeface MyCustomFontC5 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi.ttf");
//        condition5.setTypeface(MyCustomFontC5);

    }
}
