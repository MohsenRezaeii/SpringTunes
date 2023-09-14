package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.SongDAO;
import com.mohsen.springtunes.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return optionalSong.orElse(null);
    }

    @Transactional
    @Override
    public Song save(Song song) {
        return songDAO.save(song);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        songDAO.deleteById(id);
    }

}
