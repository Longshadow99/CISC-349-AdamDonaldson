package com.example.dynamiclistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        Intent i = getIntent();
        String name = i.getStringExtra(MainActivity.EXTRA_PLAYLIST_NAME);
        String img = i.getStringExtra(MainActivity.EXTRA_PLAYLIST_IMG);

        TextView alb_name = findViewById(R.id.alb_name);
        alb_name.setText(name);

        NetworkImageView plylst_img = findViewById((R.id.plylst_img));
        plylst_img.setImageUrl(img, MainActivity.imageLoader);
    }
}