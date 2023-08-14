package com.mohsen.springtunes.controller;

import com.mohsen.springtunes.entity.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @GetMapping("/")
    public ResponseEntity<Song> index() {
        Song song = new Song("Suicide & Redemption", "Metallica", "Death Magnetic");
        return ResponseEntity.status(HttpStatus.OK).body(song);
    }
}
