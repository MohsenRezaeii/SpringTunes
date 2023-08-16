package com.mohsen.springtunes.controller;

import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BasicController {

    SongService songService;

    @Autowired
    public BasicController(SongService songService) {
        this.songService = songService;
    }
    @PostMapping("/newSong")
    public ResponseEntity<Song> newSong(@RequestBody Song song) {
        songService.save(song);
        return ResponseEntity.status(HttpStatus.OK).body(song);
    }

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(songService.findAll());
    }

}
