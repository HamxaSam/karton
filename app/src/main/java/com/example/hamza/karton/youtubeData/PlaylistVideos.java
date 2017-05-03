package com.example.hamza.karton.youtubeData;

import com.google.api.services.youtube.model.Video;

import java.util.ArrayList;

public class PlaylistVideos extends ArrayList<Video>{
    public final String playlistId;
    private String mNextPageToken;

    public PlaylistVideos(String id) {
        playlistId = id;
    }

    public String getNextPageToken() {
        return mNextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        mNextPageToken = nextPageToken;
    }
}
