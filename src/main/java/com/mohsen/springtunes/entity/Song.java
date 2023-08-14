package com.mohsen.springtunes.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Song {
    private String title;
    private String artist;
    private String album;


}
