package com.example.conversions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Introduction extends AppCompatActivity {

    Button buttonNext, buttonBack, buttonSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        buttonNext = findViewById(R.id.button4);
        buttonBack = findViewById(R.id.button5);
        buttonSkip = findViewById(R.id.button14);

        buttonNext.setOnClickListener(v -> btnOnClick());
        buttonBack.setOnClickListener(v -> btnOnClick1());
        buttonSkip.setOnClickListener(v -> btnOnClick2());
    }

    private void btnOnClick2() {
        Intent myIntent = new Intent(Introduction.this, Level1.class);
        Introduction.this.startActivity(myIntent);
    }

    private void btnOnClick1() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void btnOnClick() {

        Intent intent = new Intent(this, Level1.class);
        startActivity(intent);
    }



}

