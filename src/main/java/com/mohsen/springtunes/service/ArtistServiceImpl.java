package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.ArtistDAO;
import com.mohsen.springtunes.entity.Artist;
import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.exception.ArtistNotFoundException;
import com.mohsen.springtunes.exception.DuplicateArtistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    ArtistDAO artistDAO;
    SongService songService;

    @Autowired
    @Lazy
    public ArtistServiceImpl(ArtistDAO artistDAO, SongService songService) {
        this.artistDAO = artistDAO;
        this.songService = songService;
    }

    @Override
    public List<Artist> findAll() {
        return artistDAO.findAll();
    }

    @Override
    public Artist findById(Long id) {
        Optional<Artist> optionalArtist = artistDAO.findById(id);
        return optionalArtist.orElseThrow(() -> new ArtistNotFoundException("Artist not found: " + id));
    }

    @Override
    public Artist save(Artist artist) {
        if (artist.getSongs() != null) {
            for (Song song : artist.getSongs()) {
                songService.save(song);
                song.setArtist(artist);
            }
        }

        String artistName = artist.getName();
        Artist foundArtist = findByName(artistName);
        if (foundArtist != null) {
            throw new DuplicateArtistException("Artist already exists: " + foundArtist);
        }
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
