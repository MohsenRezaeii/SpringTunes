--CREATE TABlE IF NOT EXISTS songs (
--    song_id SERIAL PRIMARY KEY,
--    title varchar(255) NOT NULL,
--    artistOld varchar(255) NOT NULL,
--    album varchar(255) NOT NULL
--);

CREATE TABLE artists (
    artist_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO artists (artist_id, name) VALUES (1, 'Metallica');

CREATE TABLE songs (
    song_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artist_id INT REFERENCES artists(artist_id),
    album VARCHAR(255),
    CONSTRAINT fk_artist FOREIGN KEY (artist_id) REFERENCES artists(artist_id)
);


INSERT INTO songs (song_id, title, artist_id, album) VALUES (1, 'That Was Just Your Life', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (2, 'The End Of The Line', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (3, 'Broken, Beat & Scarred', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (4, 'The Day That Never Comes', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (5, 'All Nightmare Long', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (6, 'Cyanide', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (7, 'The Unforgiven III', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (8, 'The Judas Kiss', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (9, 'Suicide & Redemption', 1, 'Death Magnetic');
INSERT INTO songs (song_id, title, artist_id, album) VALUES (10, 'My Apocalypse', 1, 'Death Magnetic');