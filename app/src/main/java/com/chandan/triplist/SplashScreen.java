package com.chandan.triplist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        WindowCompat.setDecorFitsSystemWindows(getWindow(),false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        dataBase = new DataBase(this);
        if(!DataBase.getAllData().isEmpty()){
            DataBase.getAllData().removeAll(DataBase.getAllData());
        }
        DataBase.getAllData().addAll(dataBase.fetchData());
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
               finish();
               startActivity(mainActivity);
           }
       },4000);
    }
}