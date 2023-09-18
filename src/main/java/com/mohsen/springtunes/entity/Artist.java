package com.mohsen.springtunes.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Song> songs;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {

        if (songs == null) {
            songs = new ArrayList<>();
        }

        this.songs.add(song);
        song.setArtist(this);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }

}
