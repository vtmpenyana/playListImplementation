package com.vincentmpenyana;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Album> albums = new ArrayList<>();
    private static LinkedList<Song> playList = new LinkedList<>();

    public static void main(String[] args) {
        Album getLifted = new Album("Get Lifted Again", "John Legend");
        Album GoodKid = new Album("Good Kid Mad City", "Kendrick Lamar");

        getLifted.addSong("Refuge", "3:32");
        getLifted.addSong("Stay with you", "4:12");
        getLifted.addSong("Ordinary People", "4:00");

        GoodKid.addSong("Backseat Freestyle", "3:40");
        GoodKid.addSong("Swimming Pool", "4:00");
        GoodKid.addSong("Real", "7:22");

        getLifted.showSongs();
        GoodKid.showSongs();
        addAlbum(getLifted);
        addAlbum(GoodKid);

        underline();
        showAlbums();
        underline();

        addSongToPlayList("Real", GoodKid);
        addSongToPlayList("Swimming Pool", GoodKid);
        addSongToPlayList("Stay with you", getLifted);
        addSongToPlayList("Backseat Freestyle", GoodKid);
        addSongToPlayList("Refuge", getLifted);
        addSongToPlayList("Ordinary People", getLifted);

        underline();
        showPlaylistSongs();
        underline();


        ArrayList<String> names = new ArrayList<>();
        
//        ====================================================================================
//        Starting the playlist
//        ====================================================================================

        startPlaylist(playList);
    }

    public static void printMenu() {
        System.out.println("\n\t Enter:\n" +
                "Q: to stop the playlist\n" +
                "N: to play next song\n" +
                "P: to play previous song\n" +
                "R: to replay current song\n" +
                "RM to remove a song\n" +
                "LS to list songs in your playlist");
    }

    public static void addAlbum(Album album) {
        for (int i = 0; i < albums.size(); i++) {
            Album currentAlbum = albums.get(i);
            if (album.getAlbumName().equals(currentAlbum.getAlbumName())) {
                System.out.println("Error adding: album already exists.");
                return;
            }
        }

        albums.add(album);
        System.out.println("Added album: " + album.getAlbumName() + " by ->" + album.getArtist() + " your album" +
                " collection");
    }

    public static void showAlbums() {
        System.out.println("\n\nALBUMS: \n");
        for (int i = 0; i < albums.size(); i++) {
            Album currentAlbum = albums.get(i);
            System.out.println("[*] - " + currentAlbum.getAlbumName() + " -> " + currentAlbum.getArtist());
        }
    }

    public static void showPlaylistSongs() {
        System.out.println("Your playlist songs: ");
        for (int i = 0; i < playList.size(); i++) {
            Song song = playList.get(i);
            System.out.println("[*] - " + song.getTitle() + ": " + song.getDuration());
        }
    }

    public static void addSongToPlayList(String songName, Album from) {
        if (from != null) {
            Song songToAdd = from.addToPlayList(songName);
            if (songToAdd != null) {
                System.out.println("Added song " + "'" + songName + "'" + " to your playlist");
                playList.add(songToAdd);
            } else {
                System.out.println("Failed adding song to your playlist, " + "'" + songName + "'" + ": not found");
            }
        }
    }

    public static void removePlaylistSong() {
        System.out.println("Enter name of the song you want to remove from your playlist.");
        String name = scanner.nextLine();

        for (int i = 0; i < playList.size(); i++) {
            Song song = playList.get(i);
            if (song.getTitle().equals(name)) {
                playList.remove(song);
                System.out.println("Successfully remove song: " + name + " from your playlist");
                return;
            }
        }

        System.out.println("Song " + name + " is not in your playlist");
    }

    public static void startPlaylist(LinkedList<Song> playList) {
        boolean quit = false;
        boolean isForward = true;
        printMenu();
        ListIterator<Song> songIter = playList.listIterator();

        System.out.println("\t\n Now playing: " + songIter.next().getTitle());
        while (!quit) {
            System.out.println("Enter option (Press P to see menu options) ");
            String option = scanner.nextLine();
            switch (option) {
                case "Q":
                    System.out.println("Terminating the playlist, Goodbye!");
                    quit = true;
                    break;
                case "N":
                    if(!isForward){
                        if(songIter.hasNext()){
                            songIter.next();
                            isForward = true;
                        }
                    }
                    if(songIter.hasNext()) {
                        System.out.println("Now playing: " + songIter.next().getTitle());
                    }
                    else{
                        System.out.println("You are the end of the playlist");
                    }
                    break;
                case "P":
                    if (isForward) {
                        if (songIter.hasPrevious()) {
                            songIter.previous();
                            isForward = false;
                        }
                    }
                    if (songIter.hasPrevious()) {
                        System.out.println("Now playing: " + songIter.previous().getTitle());
                    }
                    else{
                        System.out.println("You are at the start of the list");
                    }
                    break;
                case "R":
                    if(isForward){
                        System.out.println("Replaying song: " + songIter.previous().getTitle());
                        isForward = false;
                    }
                    else{
                        System.out.println("Replaying song: " + songIter.next().getTitle());
                        isForward = true;
                    }

                    break;
                case "PR":
                    printMenu();
                    break;
                case "RM":
                    removePlaylistSong();
                    break;
                case "LS":
                    showPlaylistSongs();
                    break;

            }
        }
    }

    public static void underline() {
        System.out.println("=================================================================");
    }

}
