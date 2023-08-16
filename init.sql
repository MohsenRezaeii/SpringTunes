DROP TABLE IF EXISTS songs;

CREATE TABlE IF NOT EXISTS songs (
    song_id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    artist varchar(255) NOT NULL,
    album varchar(255) NOT NULL
);