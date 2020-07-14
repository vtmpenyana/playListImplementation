package com.vincentmpenyana;

import java.util.LinkedList;
import java.util.ListIterator;

public class Album {
    private String albumName;
    private String artist;
    private LinkedList<Song> songs;

    public String getArtist() {
        return artist;
    }

    public Album(String albumName, String artist) {
        this.albumName = albumName;
        this.artist = artist;
        this.songs  = new LinkedList<>();
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean addSong(String name, String duration){
        if(findSong(name) == null){
            songs.add(new Song(name, duration));
            return true;
        }

        return false;
    }

    private Song findSong(String name){
        for(int i = 0; i < this.songs.size(); i++){
            Song currentSong = this.songs.get(i);
            if(currentSong.getTitle().equals(name)){
                return currentSong;
            }
        }

        return null;
    }

    public Song addToPlayList(String songName){
        return findSong(songName);
    }

    public void showSongs(){
        ListIterator<Song> songIterator = this.songs.listIterator();
        System.out.println("Song list for: " +this.getAlbumName());
        while(songIterator.hasNext()){
            Song currentSong = songIterator.next();
            System.out.println("[*] - " + currentSong.getTitle() + " : " + currentSong.getDuration());
        }
    }
}
