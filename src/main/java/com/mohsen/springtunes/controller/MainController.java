package com.mohsen.springtunes.controller;

import com.mohsen.springtunes.entity.Artist;
import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.service.ArtistService;
import com.mohsen.springtunes.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    SongService songService;
    ArtistService artistService;

    @Autowired
    public MainController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> findAllSongs() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping("/newSong")
    public ResponseEntity<Song> newSong(@RequestBody Song song) {
        if (song.getArtist() != null) {
            String artistName = song.getArtist().getName();
            Artist tempArtist = artistService.findByName(artistName);
            tempArtist.addSong(song);
        }
        Song savedSong = songService.save(song);
        return new ResponseEntity<>(savedSong, HttpStatus.OK);
    }

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> findAllArtists() {
        List<Artist> artists = artistService.findAll();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @PostMapping("/newArtist")
    public ResponseEntity<String> newArtist(@RequestBody Artist artist) {
        if (artist.getSongs() != null) {
            for (Song song : artist.getSongs()) {
                songService.save(song);
                song.setArtist(artist);
            }
        }
        try {
            Artist tempArtist = artistService.save(artist);
            return new ResponseEntity<>("Artist saved: " + tempArtist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/songs/{songId}")
    public ResponseEntity<Song> deleteSong(@PathVariable("songId") Long songId) {
        Song song = songService.findById(songId);
        songService.deleteById(songId);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable("artistId") Long artistId) {
        Artist artist = artistService.findById(artistId);
        artistService.deleteById(artistId);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

}
