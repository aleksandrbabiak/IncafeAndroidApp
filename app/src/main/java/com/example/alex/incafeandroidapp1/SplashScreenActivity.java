package com.example.alex.incafeandroidapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by Alex on 04.11.2015.
 */
public class SplashScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
      Handler  handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Intent mainActivityIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(mainActivityIntent);
                finish();
            }
        };
        handler.postDelayed(r, 1000);
    }


}



