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
@RequestMapping("/api/v1")
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

    @GetMapping("/songs/{songId}")
    public ResponseEntity<Song> findSongById(@PathVariable Long songId) {
        Song foundSong = songService.findById(songId);
        return new ResponseEntity<>(foundSong, HttpStatus.OK);
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> newSong(@RequestBody Song song) {
        Song savedSong = songService.save(song);
        return new ResponseEntity<>(savedSong, HttpStatus.OK);
    }

    @DeleteMapping("/songs/{songId}")
    public ResponseEntity<Song> deleteSong(@PathVariable("songId") Long songId) {
        Song song = songService.findById(songId);
        songService.deleteById(songId);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> findAllArtists() {
        List<Artist> artists = artistService.findAll();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping("/artists/{artistId}")
    public ResponseEntity<Artist> findArtistById(@PathVariable Long artistId) {
        Artist foundArtist = artistService.findById(artistId);
        return new ResponseEntity<>(foundArtist, HttpStatus.OK);
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> newArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistService.save(artist);
        return new ResponseEntity<>(savedArtist, HttpStatus.OK);
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable("artistId") Long artistId) {
        Artist artist = artistService.findById(artistId);
        artistService.deleteById(artistId);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

}
