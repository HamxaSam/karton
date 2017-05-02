package com.example.hamza.karton.Model;

/**
 * Created by Usman on 5/1/2017.
 */

public class YoutubeModel {
    String title,thumbUrl;

    public YoutubeModel(String title, String thumbUrl) {
        this.title = title;

        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
