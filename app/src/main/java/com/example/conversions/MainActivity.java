package com.example.conversions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Passing variables for Buttons and animations
    Button buttonStart, buttonSettings, buttonQuit;
    Animation scaleUp, scaleDown;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking buttons
        buttonStart = findViewById(R.id.button);
        buttonSettings = findViewById(R.id.button2);
        buttonQuit = findViewById(R.id.button3);

        //button animations
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down);

        //linking button animations
        buttonStart.setOnTouchListener((v, event) -> {

            if(event.getAction()==MotionEvent.ACTION_DOWN){
                buttonStart.startAnimation(scaleUp);

            }else if(event.getAction()==MotionEvent.ACTION_UP){
                buttonStart.startAnimation(scaleDown);

            }
            return false;
        });

        buttonSettings.setOnTouchListener((v, event) -> {

            if(event.getAction()==MotionEvent.ACTION_DOWN){
                buttonStart.startAnimation(scaleUp);

            }else if(event.getAction()==MotionEvent.ACTION_UP){
                buttonStart.startAnimation(scaleDown);

            }
            return false;
        });

        buttonQuit.setOnTouchListener((v, event) -> {

            if(event.getAction()==MotionEvent.ACTION_DOWN){
                buttonStart.startAnimation(scaleUp);

            }else if(event.getAction()==MotionEvent.ACTION_UP){
                buttonStart.startAnimation(scaleDown);

            }
            return false;
        });

        //onClick listeners
        buttonStart.setOnClickListener(v -> btnOnClick());


    }
    //Switching activity on Click
    public void btnOnClick() {
        Intent intent = new Intent(MainActivity.this, Introduction.class);
        startActivity(intent);
    }
}