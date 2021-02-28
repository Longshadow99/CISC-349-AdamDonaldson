package com.example.dynamiclistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class AlbumAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Album> arrayList;

    public AlbumAdapter(Context context, ArrayList<Album> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_album,
                parent, false );

        NetworkImageView alb_img = convertView.findViewById((R.id.alb_img));
        TextView alb_name = convertView.findViewById(R.id.alb_name);
        TextView artist = convertView.findViewById(R.id.artist);
        TextView dcblt = convertView.findViewById(R.id.dcblt);
        TextView dur = convertView.findViewById(R.id.dur);

        alb_img.setImageUrl(arrayList.get(position).getAlb_img(), MainActivity.imageLoader);
        alb_name.setText(arrayList.get(position).getAlb_name());
        artist.setText(arrayList.get(position).getArtist());
        dcblt.setText("Danceability: " + arrayList.get(position).getDcblt());
        String durmin = String.format("%2d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(Integer.parseInt(arrayList.get(position).getDur())),
                TimeUnit.MILLISECONDS.toSeconds(Integer.parseInt(arrayList.get(position).getDur()))-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Integer.parseInt(arrayList.get(position).getDur()))));
        dur.setText(durmin);

        return convertView;
    }
}
