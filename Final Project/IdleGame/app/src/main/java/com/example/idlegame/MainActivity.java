package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lbutton = findViewById(R.id.login_main);
        Button sbutton = findViewById(R.id.signup_main);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LogIn.class);
                startActivityForResult(i, REQUEST_CODE);

            }
        });
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }
}