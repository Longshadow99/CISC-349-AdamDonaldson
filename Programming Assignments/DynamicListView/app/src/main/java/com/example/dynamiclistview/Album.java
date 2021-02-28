package com.example.dynamiclistview;

public class Album {

    private String alb_img;
    private String alb_name;
    private String artist;
    private String dcblt;
    private String dur;
    private String plylst_img;

    public String getAlb_img() {
        return alb_img;
    }

    public String getAlb_name() {
        return alb_name;
    }

    public String getArtist() {
        return artist;
    }

    public String getDcblt() {
        return dcblt;
    }

    public String getDur() {
        return dur;
    }

    public String getPlylst_img() {
        return plylst_img;
    }

    public Album(String alb_img, String alb_name, String artist, String dcblt, String dur, String plylst_img) {
        this.alb_img = alb_img;
        this.alb_name = alb_name;
        this.artist = artist;
        this.dcblt = dcblt;
        this.dur = dur;
        this.plylst_img = plylst_img;
    }
}
