package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.SongDAO;
import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.exception.SongNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    SongDAO songDAO;

    @Autowired
    public SongServiceImpl(SongDAO songDAO) {
        this.songDAO = songDAO;
    }
    @Override
    public List<Song> findAll() {
        return songDAO.findAll();
    }

    @Override
    public Song findById(Long id) {
        Optional<Song> optionalSong = songDAO.findById(id);
        return optionalSong.orElseThrow(() -> new SongNotFoundException("Song not found: " + id));
    }

    @Override
    public Song save(Song song) {
        return songDAO.save(song);
    }

    @Override
    public void deleteById(Long id) {
        songDAO.deleteById(id);
    }

    @Override
    public Song findByTitle(String title) {
        return songDAO.findByTitle(title);
    }

}
