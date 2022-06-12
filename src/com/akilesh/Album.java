package com.akilesh;

import java.util.LinkedList;

public class Album {

    private final String name;
    private final LinkedList<Song> songs = new LinkedList<>();

    public Album(String albumName,String firstSongTitle) {
        this.name = albumName;
        Song song = new Song(firstSongTitle);
        songs.add(song);
    }

    public void addSong(String title) {
        Song song = new Song(title);
        songs.add(song);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getSongs() {
        return songs;
    }
}
