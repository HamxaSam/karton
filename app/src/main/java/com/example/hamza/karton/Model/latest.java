package com.example.hamza.karton.Model;

/**
 * Created by Hamza on 5/6/2017.
 */

public class latest {
    String nameLatest;
    int imageLatest;

    public latest(String nameLatest, int imageLatest) {
        this.nameLatest = nameLatest;
        this.imageLatest = imageLatest;
    }

    public String getNameLatest() {
        return nameLatest;
    }

    public void setNameLatest(String nameLatest) {
        this.nameLatest = nameLatest;
    }

    public int getImageLatest() {
        return imageLatest;
    }

    public void setImageLatest(int imageLatest) {
        this.imageLatest = imageLatest;
    }
}
