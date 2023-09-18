package com.mohsen.springtunes.service;

import com.mohsen.springtunes.entity.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> findAll();

    Artist findById(Long id);

    Artist save(Artist artist) throws Exception;

    void deleteById(Long id);

    Artist findByName(String name);


}
