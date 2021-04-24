package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Game extends AppCompatActivity {
    int r1aval;
    int r1rval;
    int r2rval;
    int r3rval;
    int r4rval;
    int mult;
    int tick = 0;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        r1aval = this.getIntent().getExtras().getInt("num");
        r1rval = this.getIntent().getExtras().getInt("rate1");
        r2rval = this.getIntent().getExtras().getInt("rate2");
        r3rval = this.getIntent().getExtras().getInt("rate3");
        r4rval = this.getIntent().getExtras().getInt("rate4");
        user = this.getIntent().getExtras().getString("username");

        TextView r1a = findViewById(R.id.amount1);
        TextView r1r = findViewById(R.id.productionsec1);
        TextView r2r = findViewById(R.id.productionsec2);
        TextView r3r = findViewById(R.id.productionsec3);
        TextView r4r = findViewById(R.id.productionsec4);
        Button r1i = findViewById(R.id.invest1);
        Button r2i = findViewById(R.id.invest2);
        Button r3i = findViewById(R.id.invest3);
        Button r4i = findViewById(R.id.invest4);
        Button vm1 = findViewById(R.id.valmul1);
        Button vm2 = findViewById(R.id.valmul10);
        Button vm3 = findViewById(R.id.valmul100);

        r1a.setText(String.valueOf(r1aval));
        r1r.setText(String.valueOf(r1rval));
        r2r.setText(String.valueOf(r2rval));
        r3r.setText(String.valueOf(r3rval));
        r4r.setText(String.valueOf(r4rval));

        r1i.setText(String.valueOf(mult));
        r2i.setText(String.valueOf(mult * 1000));
        r3i.setText(String.valueOf(mult * 100000));
        r4i.setText(String.valueOf(mult * 1000000));

        NumberFormat numFormat = new DecimalFormat();
        numFormat = new DecimalFormat("0.#####E0");
        Handler handler = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {

                r1aval += r1rval;
                r1rval += r2rval;
                r2rval += r3rval;
                r3rval += r4rval;

                r1i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (r1aval >= mult) {
                            r1aval -= mult;
                            r1rval += mult;
                        }

                    }
                });

                r2i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (r1aval >= 1000 * mult) {
                            r1aval -= 1000 * mult;
                            r2rval += mult;
                        }

                    }

                });
                r3i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (r1aval >= 100000 * mult) {
                            r1aval -= 100000 * mult;
                            r3rval += mult;
                        }

                    }

                });
                r4i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (r1aval >= 1000000 * mult) {
                            r1aval -= 1000000 * mult;
                            r4rval += mult;
                        }

                    }
                });
                vm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mult = 1;

                    }
                });
                vm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mult = 10;

                    }
                });
                vm3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mult = 100;

                    }
                });

                r1a.setText(String.valueOf(r1aval));
                r1r.setText(String.valueOf(r1rval));
                r2r.setText(String.valueOf(r2rval));
                r3r.setText(String.valueOf(r3rval));
                r4r.setText(String.valueOf(r4rval));
                r1i.setText(String.valueOf(mult));
                r2i.setText(String.valueOf(mult * 1000));
                r3i.setText(String.valueOf(mult * 100000));
                r4i.setText(String.valueOf(mult * 1000000));
                tick += 1;
                if(tick ==30){
                    Save();
                    tick = 0;
                }
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);



        }
    private void Save(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("username", user);
            json.put("num", r1aval);
            json.put("rate1", r1rval);
            json.put("rate2", r2rval);
            json.put("rate3", r3rval);
            json.put("rate4", r4rval);


        }catch (JSONException e){
            e.printStackTrace();
        }
        final String URL = "http://192.168.254.67:5000/save";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast toast = Toast.makeText(getApplicationContext(), "Autosave Successful", Toast.LENGTH_SHORT);
                toast.show();

            }
        }
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast toast = Toast.makeText(getApplicationContext(), "Autosave Failed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}