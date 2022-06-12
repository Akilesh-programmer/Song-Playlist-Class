package com.akilesh;

public class Main {

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.createAlbum("A", "Believer");
        playlist.createAlbum("B", "Shape of You");
        playlist.addSong("A", "Faded");
        playlist.addSong("A", "Unstoppable");
        playlist.addSong("B", "Despacito");
        playlist.addSong("B", "Honest");

        playlist.startPlaying("A");
    }
}
