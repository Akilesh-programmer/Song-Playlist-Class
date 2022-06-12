package com.akilesh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Playlist {

    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Album> albums = new ArrayList<>();

    public void createAlbum(String albumName, String firstSongTitle) {
        Album album = new Album(albumName, firstSongTitle);
        albums.add(album);
    }

    private int getIndexOfAlbum(String albumName) {
        int index;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getName().equals(albumName)) {
                index = i;
                return index;
            }
        }
        System.out.println("No album with the name " + albumName + " was found.");
        return -1;
    }

    public void addSong(String albumName, String songTitle) {
        int index = getIndexOfAlbum(albumName);
        Album album = albums.get(index);
        album.addSong(songTitle);
    }

    public void startPlaying(String albumName) {
        int albumIndex = getIndexOfAlbum(albumName);
        Album album = albums.get(albumIndex);
        LinkedList<Song> songs = album.getSongs();
        ListIterator<Song> listIterator = songs.listIterator();
        boolean quit = false;
        printMenu();
        String currentSongTitle = "";
        boolean setFirst = false;
        while (!quit) {
            System.out.println("Press: ");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    setFirst = true;
                    // Making the iterator to point in the first element by reinitializing it.
                    listIterator = songs.listIterator();
                    if (listIterator.hasNext()) {
                        Song song = listIterator.next();
                        String title = song.getTitle();
                        System.out.println("Playing " + title);
                        currentSongTitle = title;
                    } else {
                        System.out.println("The album doesn't have any song.");
                    }
                    break;
                case 2:
                    if (listIterator.hasNext()) {
                        Song skipSong = listIterator.next();
                        if (listIterator.hasNext()) {
                            Song nextSong = listIterator.next();
                            System.out.println("Skipped " + skipSong.getTitle() +
                                    ", now playing " + nextSong.getTitle());
                            currentSongTitle = nextSong.getTitle();
                        } else {
                            System.out.println("Reached the end of the list.");
                        }
                    } else {
                        System.out.println("Reached the end of the list.");
                    }
                    break;
                case 3:
                    setFirst = true;
                    if (listIterator.hasNext()) {
                        Song nextSong = listIterator.next();
                        System.out.println("PLaying " + nextSong.getTitle());
                        currentSongTitle = nextSong.getTitle();
                    } else {
                        System.out.println("Reached the end of the list.");
                    }
                    break;
                case 4:
                    setFirst = true;
                    if (listIterator.hasPrevious()) {
                        listIterator.previous();
                        if (listIterator.hasPrevious()) {
                            Song previousSong = listIterator.previous();
                            System.out.println("Playing " + previousSong.getTitle());
                        } else {
                            System.out.println("Reached the start of the list.");
                        }
                    } else {
                        System.out.println("Reached the start of the list.");
                    }
                    break;
                case 5:
                    if (!currentSongTitle.isEmpty()) {
                        System.out.println("Replaying " + currentSongTitle);
                    } else {
                        System.out.println("You need to choose a song first to replay it.");
                    }
                    break;
                case 6:
                    printMenu();
                    break;
                case 7:
                    if (setFirst) {
                        listIterator.remove();
                        System.out.println("Removed " + currentSongTitle);
                    } else {
                        System.out.println("Play some song or go to next or previous song.");
                    }
                    if (!listIterator.hasNext()) {
                        listIterator = songs.listIterator();
                        Song currentSong = listIterator.next();
                        currentSongTitle = currentSong.getTitle();
                        System.out.println("Playing " + currentSongTitle);
                    } else {
                        Song currentSong = listIterator.next();
                        currentSongTitle = currentSong.getTitle();
                        System.out.println("Playing " + currentSongTitle);
                    }
            }
        }
    }

    public void printMenu() {
        System.out.println("""
                0 - Quit
                1 - Start
                2 - Skip
                3 - Next Song
                4 - Previous Song
                5 - Replay
                6 - Print Menu
                7 - Remove Current Song
                """);
    }
}
