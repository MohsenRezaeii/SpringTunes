package com.mohsen.springtunes.service;

import com.mohsen.springtunes.entity.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> findAll();

    Artist findById(int id);

    Artist save(Artist artist);

    void deleteById(int id);

}
