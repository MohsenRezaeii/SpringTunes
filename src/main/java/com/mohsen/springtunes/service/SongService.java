package com.mohsen.springtunes.service;

import com.mohsen.springtunes.entity.Song;
import java.util.List;

public interface SongService {

    List<Song> findAll();

    Song findById(int id);

    Song save(Song song);

    void deleteById(int id);

}
