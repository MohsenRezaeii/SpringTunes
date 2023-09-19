package com.mohsen.springtunes.controller;

import com.mohsen.springtunes.entity.Artist;
import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.exception.DuplicateArtistError;
import com.mohsen.springtunes.exception.DuplicateArtistException;
import com.mohsen.springtunes.exception.DuplicateSongError;
import com.mohsen.springtunes.exception.DuplicateSongException;
import com.mohsen.springtunes.service.ArtistService;
import com.mohsen.springtunes.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
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

    @PostMapping("/songs")
    public ResponseEntity<Song> newSong(@RequestBody Song song) {
        if (song.getArtist() != null) {
            String artistName = song.getArtist().getName();
            Artist tempArtist = artistService.findByName(artistName);
            tempArtist.addSong(song);
        }

        Song foundSong = songService.findByTitle(song.getTitle());
        if (foundSong != null && song.getArtist() != null &&
                song.getArtist().getName().equals(foundSong.getArtist().getName())) {
            throw new DuplicateSongException("Song already exists: " + foundSong);
        }

        Song savedSong = songService.save(song);
        return new ResponseEntity<>(savedSong, HttpStatus.OK);
    }

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> findAllArtists() {
        List<Artist> artists = artistService.findAll();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> newArtist(@RequestBody Artist artist) {
        if (artist.getSongs() != null) {
            for (Song song : artist.getSongs()) {
                songService.save(song);
                song.setArtist(artist);
            }
        }

        String artistName = artist.getName();
        Artist foundArtist = artistService.findByName(artistName);
        if (foundArtist != null) {
            throw new DuplicateArtistException("Artist already exists: " + foundArtist);
        }
        Artist savedArtist = artistService.save(artist);
        return new ResponseEntity<>(savedArtist, HttpStatus.OK);
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

    @ExceptionHandler
    public ResponseEntity<DuplicateSongError> handleException(DuplicateSongException exception) {
        DuplicateSongError error = new DuplicateSongError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<DuplicateArtistError> handleException(DuplicateArtistException exception) {
        DuplicateArtistError error = new DuplicateArtistError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
