package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.ArtistDAO;
import com.mohsen.springtunes.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    ArtistDAO artistDAO;

    @Autowired
    public ArtistServiceImpl(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @Override
    public List<Artist> findAll() {
        return artistDAO.findAll();
    }

    @Override
    public Artist findById(Long id) {
        Optional<Artist> optionalArtist = artistDAO.findById(id);
        return optionalArtist.orElse(null);
    }

    @Override
    public Artist save(Artist artist) {
        return artistDAO.save(artist);
    }

    @Override
    public void deleteById(Long id) {
        artistDAO.deleteById(id);
    }

    @Override
    public Artist findByName(String name) {
        return artistDAO.findByName(name);
    }

}
