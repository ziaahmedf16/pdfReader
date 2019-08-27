package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class WellCome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_come);


        getSupportActionBar().hide();

        Thread thread=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(4000);
                    Intent intent = new Intent(WellCome.this, StartActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
