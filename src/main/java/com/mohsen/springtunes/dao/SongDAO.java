package com.mohsen.springtunes.dao;

import com.mohsen.springtunes.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongDAO extends JpaRepository<Song, Long> {
}