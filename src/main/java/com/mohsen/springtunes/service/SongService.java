package com.mohsen.springtunes.service;

import com.mohsen.springtunes.entity.Song;
import java.util.List;

public interface SongService {

    List<Song> findAll();

    Song findById(Long id);

    Song save(Song song);

    void deleteById(Long id);

    Song findByTitle(String title);

}
