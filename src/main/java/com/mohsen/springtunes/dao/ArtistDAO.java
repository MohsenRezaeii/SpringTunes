package com.mohsen.springtunes.dao;

import com.mohsen.springtunes.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistDAO extends JpaRepository<Artist, Integer> {
}