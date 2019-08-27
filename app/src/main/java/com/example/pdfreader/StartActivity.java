package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnStart, btnLastRead, btnPage, btnAbout, btnExit;
    private EditText editPage;
    private int pageNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnLastRead = (Button) findViewById(R.id.btnLastRead);
        btnPage = (Button) findViewById(R.id.btnPage);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnExit = (Button) findViewById(R.id.btnExit);

        editPage = (EditText) findViewById(R.id.editPage);

        btnStart.setOnClickListener(this);
        btnLastRead.setOnClickListener(this);
        btnPage.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        getSupportActionBar().hide();

    }

    private int getDataFromSharedPreference()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        //SharedPreferences.Editor editor = pref.edit();
        //editor.putInt("key_page", 5);
        //editor.commit();
        pageNumber=pref.getInt("key_page", 0);
        return pageNumber;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnStart:
                Intent intent1=new Intent(StartActivity.this, MainActivity.class);
                intent1.putExtra("MyPageNumber",0);
                startActivity(intent1);
                break;
            case R.id.btnLastRead:
                Intent intent2=new Intent(StartActivity.this, MainActivity.class);
                intent2.putExtra("MyPageNumber",getDataFromSharedPreference());
                startActivity(intent2);
                break;
            case R.id.btnPage:
                try
                {
                    int directPage = Integer.parseInt(editPage.getText().toString());

                    Intent intent3=new Intent(StartActivity.this, MainActivity.class);
                    intent3.putExtra("MyPageNumber",directPage-1);
                    startActivity(intent3);
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Please enter page number", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnAbout:
                Intent intent4=new Intent(StartActivity.this, AboutActivity.class);
                startActivity(intent4);
                break;
            case R.id.btnExit:
                finish();
        }
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }
}
