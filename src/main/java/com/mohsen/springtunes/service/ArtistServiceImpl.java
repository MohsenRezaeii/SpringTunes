package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.ArtistDAO;
import com.mohsen.springtunes.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Artist save(Artist artist) throws Exception {
        String artistName = artist.getName();
        Artist tempArtist = artistDAO.findByName(artistName);
        if (tempArtist != null) {
            throw new Exception("Artist already exists: " + tempArtist);
        }
        return artistDAO.save(artist);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        artistDAO.deleteById(id);
    }

    @Override
    public Artist findByName(String name) {
        Artist artist = artistDAO.findByName(name);
        if (artist == null) {
            artist = new Artist(name);
        }
        return artist;
    }

}
