package com.example.hamza.karton.Model;

/**
 * Created by Hamza on 5/3/2017.
 */

public class favEpisode {
    String Title;
    int Image;

    public favEpisode(String title, String views, int image) {
        Title = title;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

}
