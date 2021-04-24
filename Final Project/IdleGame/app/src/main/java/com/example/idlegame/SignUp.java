package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button sbutton = findViewById(R.id.signup_signup);
        EditText uedit = findViewById(R.id.username_signup_edit);
        EditText pedit = findViewById(R.id.password_signup_edit);
        EditText cedit = findViewById(R.id.cpassword_signup_edit);

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(String.valueOf(pedit.getText()).equals(String.valueOf(cedit.getText()))){
                    signUp(String.valueOf(uedit.getText()),String.valueOf(pedit.getText()));
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Password Mismatch", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });


    }

    private void signUp(final String user,final String pass){

        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("username", user);
            json.put("password", pass);

        }catch (JSONException e){
            e.printStackTrace();
        }



        final String URL = "http://192.168.254.67:5000/signup";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent i = new Intent(SignUp.this, LogIn.class);
                Toast toast = Toast.makeText(getApplicationContext(), "Account Created Log In", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(i);

            }
        }
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast toast = Toast.makeText(getApplicationContext(), "Username Taken", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}