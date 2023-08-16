package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.SongDAO;
import com.mohsen.springtunes.entity.Song;
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
    public Song findById(int id) {
        Optional<Song> optionalSong = songDAO.findById(id);
        return optionalSong.orElse(null);
    }

    @Override
    public Song save(Song song) {
        return songDAO.save(song);
    }

    @Override
    public void deleteById(int id) {
        songDAO.deleteById(id);
    }

}