INSERT INTO TAG(TAG_NAME) VALUES
    ('Trending'), ('Action'), ('Comedy'), ('Romantic'), ('Adventure'), ('Musical'), ('Drama'), ('Animated'), ('Horror'), ('Sci-Fi'), ('War');

INSERT INTO MOVIE(MOVIE_TITLE, POSTER_IMG_URL, MOVIE_DESC) VALUES
    ('Avengers', 'assets/avengers_poster.jpg', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.'),
    ('Titanic', 'assets/titanic_poster.jpg', 'Earth''s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.'),
    ('Harry Potter', 'assets/harry_potter_poster.jpg', 'An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.');

INSERT INTO MOVIES_TAGS (MOVIE_ID, TAG_ID) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 7),
    (3, 2),
    (3, 5),
    (3, 10);