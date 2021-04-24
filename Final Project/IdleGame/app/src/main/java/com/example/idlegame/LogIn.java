package com.example.idlegame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;


public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button lbutton = findViewById(R.id.login_login);
        EditText uedit = findViewById(R.id.usermame_login_edit);
        EditText pedit = findViewById(R.id.password_login_edit);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logIn(String.valueOf(uedit.getText()),String.valueOf(pedit.getText()));


            }
        });
    }
    private void logIn(final String user,final String pass){
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("username", user);
            json.put("password", pass);

        }catch (JSONException e){
            e.printStackTrace();
        }



        final String URL = "http://192.168.254.67:5000/login";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if( response.getInt("AID") == 0){

                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        Intent i = new Intent(LogIn.this, Game.class);
                        i.putExtra("AID", response.getInt("AID"));
                        i.putExtra("username", response.getString("username"));
                        i.putExtra("password", response.getString("password"));
                        i.putExtra("num", response.getInt("num"));
                        i.putExtra("rate1", response.getInt("rate1"));
                        i.putExtra("rate2", response.getInt("rate2"));
                        i.putExtra("rate3", response.getInt("rate3"));
                        i.putExtra("rate4", response.getInt("rate4"));

                        startActivity(i);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast toast = Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        queue.add(jsonObjectRequest);
}
}