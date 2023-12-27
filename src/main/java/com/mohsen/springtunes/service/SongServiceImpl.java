package com.mohsen.springtunes.service;

import com.mohsen.springtunes.dao.SongDAO;
import com.mohsen.springtunes.entity.Artist;
import com.mohsen.springtunes.entity.Song;
import com.mohsen.springtunes.exception.DuplicateSongException;
import com.mohsen.springtunes.exception.SongNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    SongDAO songDAO;
    ArtistService artistService;

    @Autowired
    public SongServiceImpl(SongDAO songDAO, ArtistService artistService) {
        this.songDAO = songDAO;
        this.artistService = artistService;
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
        if (song.getArtist() != null) {
            String artistName = song.getArtist().getName();
            Artist tempArtist = artistService.findByName(artistName);
            if (tempArtist == null) {
                tempArtist = new Artist(artistName);
                tempArtist = artistService.save(tempArtist);
            }
            tempArtist.addSong(song);
        }

        Song foundSong = findByTitle(song.getTitle());
        if (foundSong != null && song.getArtist() != null &&
                song.getArtist().getName().equals(foundSong.getArtist().getName())) {
            throw new DuplicateSongException("Song already exists: " + foundSong);
        }
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
