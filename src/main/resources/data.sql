INSERT INTO TAG(TAG_NAME) VALUES
    ('Trending'), ('Action'), ('Comedy'), ('Romantic'), ('Adventure'), ('Musical'), ('Drama'), ('Animated'), ('Horror'), ('Sci-Fi'), ('War');

INSERT INTO MOVIE(MOVIE_TITLE) VALUES
    ('Avengers'),
    ('Titanic'),
    ('Harry Potter');

INSERT INTO MOVIES_TAGS (MOVIE_ID, TAG_ID) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 7),
    (3, 2),
    (3, 5),
    (3, 10);