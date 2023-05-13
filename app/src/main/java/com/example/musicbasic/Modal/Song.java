package com.example.musicbasic.Modal;

import android.net.Uri;

import java.io.File;

public class Song {
    private String nameSong;
    private Uri File;

    public Song(String nameSong, Uri file) {
        this.nameSong = nameSong;
        File = file;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public Uri getFile() {
        return File;
    }

    public void setFile(Uri file) {
        File = file;
    }
}
