package com.example.dynamiclistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static ImageLoader imageLoader;
    public static final String EXTRA_PLAYLIST_NAME = "com.example.dynamiclistview.playlist_name";
    public static final String EXTRA_PLAYLIST_IMG = "com.example.dynamiclistview.playlist_img";
    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Album> arrayOfAlbum = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        AlbumAdapter adapter = new AlbumAdapter(this, arrayOfAlbum);
        ListView listView = (ListView) findViewById(R.id.my_list_view);
        listView.setAdapter(adapter);

        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String,
                    Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);

            }
        });

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        "https://setify.info:3000/holiday_songs_spotify", null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObjectFromArray =
                                                response.getJSONObject(i);
                                            arrayOfAlbum.add(new Album(jsonObjectFromArray.getString("album_img"),jsonObjectFromArray.getString("album_name"),jsonObjectFromArray.getString("artist_name"),jsonObjectFromArray.getString("danceability"),jsonObjectFromArray.getString("duration_ms"),jsonObjectFromArray.getString("playlist_img")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSONArray Error", "Error:" + error);
                    }
                });


        queue.add(jsonArrayRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,

                                    int position, long id)
            {
                Intent i = new Intent(MainActivity.this, PlaylistActivity.class);
                i.putExtra(EXTRA_PLAYLIST_NAME, arrayOfAlbum.get(position).getAlb_name());
                i.putExtra(EXTRA_PLAYLIST_IMG, arrayOfAlbum.get(position).getPlylst_img());
                startActivityForResult(i, REQUEST_CODE);

            }

        });


    }
}