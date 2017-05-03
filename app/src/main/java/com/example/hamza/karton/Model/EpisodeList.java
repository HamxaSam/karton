package com.example.hamza.karton.Model;

/**
 * Created by Hamza on 5/2/2017.
 */

public class EpisodeList {
    String Title;
    String Views;
    int Image;

    public EpisodeList(String title, String views, int image) {
        Title = title;
        Views = views;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getViews() {
        return Views;
    }

    public void setViews(String views) {
        Views = views;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
