CREATE TABlE IF NOT EXISTS songs (
    song_id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    artist varchar(255) NOT NULL,
    album varchar(255) NOT NULL
);

INSERT INTO songs (song_id, title, artist, album) VALUES (1, 'That Was Just Your Life', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (2, 'The End Of The Line', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (3, 'Broken, Beat & Scarred', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (4, 'The Day That Never Comes', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (5, 'All Nightmare Long', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (6, 'Cyanide', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (7, 'The Unforgiven III', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (8, 'The Judas Kiss', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (9, 'Suicide & Redemption', 'Metallica', 'Death Magnetic');
INSERT INTO songs (song_id, title, artist, album) VALUES (10, 'My Apocalypse', 'Metallica', 'Death Magnetic');