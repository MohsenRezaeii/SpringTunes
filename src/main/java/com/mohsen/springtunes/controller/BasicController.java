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
public class BasicController {

    SongService songService;
    ArtistService artistService;

    @Autowired
    public BasicController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> findAllSongs() {
        return ResponseEntity.status(HttpStatus.OK).body(songService.findAll());
    }

    @PostMapping("/newSong")
    public ResponseEntity<Song> newSong(@RequestBody Song song) {
        Artist artist = song.getArtist();
        artist.addSong(song);
        songService.save(song);
        return ResponseEntity.status(HttpStatus.OK).body(song);
    }

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> findAllArtists() {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.findAll());
    }

    @PostMapping("/newArtist")
    public ResponseEntity<Artist> newArtist(@RequestBody Artist artist) {
        artistService.save(artist);
        return ResponseEntity.status(HttpStatus.OK).body(artist);
    }


    @PostMapping("/addSong")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        try {
            // Set the artist for the song (assuming artistId is provided in the request)
            Artist artist = song.getArtist();
            if (artist != null && artist.getId() != null) {
                artist = artistService.findById(artist.getId());
                song.setArtist(artist);
            }

            // Save the song to the database
            songService.save(song);

            return new ResponseEntity<>(song, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
