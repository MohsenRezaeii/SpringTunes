package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.ArtistDAO;
import com.mohsen.springtunes.dao.SongDAO;
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

    private final SongDAO songDAO;
    ArtistDAO artistDAO;
    SongService songService;

    @Autowired
    @Lazy
    public ArtistServiceImpl(ArtistDAO artistDAO, SongService songService,
                             SongDAO songDAO) {
        this.artistDAO = artistDAO;
        this.songService = songService;
        this.songDAO = songDAO;
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
        Artist foundArtist = artistDAO.findByName(artist.getName());
        if (foundArtist == null) {
            foundArtist = new Artist(artist.getName());
            if (artist.getSongs() != null) {
                for (Song song : artist.getSongs()) {
                    foundArtist.addSong(song);
                }
            }
            return artistDAO.save(foundArtist);
        } else {
            if (artist.getSongs() != null) {
                for (Song song : artist.getSongs()) {
                    Song foundSong = songDAO.findByTitleAndAlbum(song.getTitle(), song.getAlbum());
                    if (foundSong == null) {
                        foundArtist.addSong(song);
                        songService.save(song);
                    }
                }
            }
            throw new DuplicateArtistException("Artist already exists: " + foundArtist);
        }
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
