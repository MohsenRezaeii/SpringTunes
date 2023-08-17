package com.mohsen.springtunes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "album")
    private String album;

    public Song() {
    }

    public Song(int id, String title, String album) {
        this.id = id;
        this.title = title;
        this.album = album;
    }

    public Song(String title, String album) {
        this.title = title;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", album='" + album + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;

        if (getId() != song.getId()) return false;
        if (!getTitle().equals(song.getTitle())) return false;
        if (!getArtist().equals(song.getArtist())) return false;
        return getAlbum().equals(song.getAlbum());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getArtist().hashCode();
        result = 31 * result + getAlbum().hashCode();
        return result;
    }
}
