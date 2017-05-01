package com.example.hamza.karton.Model;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    private String name;

    private String thumbnail;

    public Album(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

