package com.example.hamza.karton.Model;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Albumtwo {
    private String name;
    private String numOfSongs;
    private String thumbnail;

    public Albumtwo() {
    }

    public Albumtwo(String name, String numOfSongs, String thumbnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(String numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
