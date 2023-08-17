package com.mohsen.springtunes.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private List<Song> songs;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;

        if (getId() != artist.getId()) return false;
        if (!getName().equals(artist.getName())) return false;
        return getSongs().equals(artist.getSongs());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSongs().hashCode();
        return result;
    }
}
