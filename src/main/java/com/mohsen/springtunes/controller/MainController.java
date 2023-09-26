package com.mohsen.springtunes.controller;

import com.mohsen.springtunes.entity.Artist;
import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.exception.ArtistNotFoundException;
import com.mohsen.springtunes.exception.DuplicateArtistException;
import com.mohsen.springtunes.exception.DuplicateSongException;
import com.mohsen.springtunes.exception.SongNotFoundException;
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

        if (foundSong == null) {
            throw new SongNotFoundException("Song not found: " + songId);
        }

        return new ResponseEntity<>(foundSong, HttpStatus.OK);
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> newSong(@RequestBody Song song) {
        if (song.getArtist() != null) {
            String artistName = song.getArtist().getName();
            Artist tempArtist = artistService.findByName(artistName);
            if (tempArtist == null) {
                tempArtist = new Artist(artistName);
                tempArtist = artistService.save(tempArtist);
            }
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

    @PutMapping("/songs")
    public ResponseEntity<Song> updateSong(@RequestBody Song song) {
        Song updatedSong = songService.updateSong(song) ;
        return new ResponseEntity<>(updatedSong, HttpStatus.OK);
    }

    @DeleteMapping("/songs/{songId}")
    public ResponseEntity<Song> deleteSong(@PathVariable("songId") Long songId) {
        Song song = songService.findById(songId);
        if (song == null) {
            throw new SongNotFoundException("Song does not exist: " + songId);
        }
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

        if (foundArtist == null) {
            throw new ArtistNotFoundException("Artist not found: " + artistId);
        }

        return new ResponseEntity<>(foundArtist, HttpStatus.OK);
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

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable("artistId") Long artistId) {
        Artist artist = artistService.findById(artistId);
        if (artist == null) {
            throw new ArtistNotFoundException("Artist does not exist: " + artistId);
        }
        artistService.deleteById(artistId);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

}
