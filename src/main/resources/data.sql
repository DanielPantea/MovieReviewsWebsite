INSERT INTO TAG(TAG_KEY) VALUES
    ('trending'), ('action'), ('adventure'), ('animated'), ('comedy'), ('crime'), ('docu'), ('drama'), ('fantasy'), ('history'), ('horror'), ('musical'), ('romance'), ('satire'), ('scifi'), ('thriller'), ('war'), ('western');

INSERT INTO MOVIE(MOVIE_TITLE, LENGTH_MINUTES, RELEASE_DATE, TRAILER_URL, POSTER_IMG_URL, MOVIE_DESC) VALUES
    ('Avengers', 143, '2012-05-04', 'https://youtu.be/eOrNdBpGMv8', 'assets/avengers_poster.jpg', 'Earth''s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.'),
    ('Titanic', 194, '1997-11-18', 'https://youtu.be/kVrqfYjkTdQ', 'assets/titanic_poster.jpg', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.'),
    ('Harry Potter', 152, '2001-11-04', 'https://youtu.be/VyHV0BRtdxo', 'assets/harry_potter_poster.jpg', 'An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.');

INSERT INTO MOVIES_TAGS (MOVIE_ID, TAG_KEY) VALUES
    (1, 'trending'),
    (1, 'action'),
    (1, 'fantasy'),
    (2, 'drama'),
    (2, 'romance'),
    (3, 'action'),
    (3, 'adventure'),
    (3, 'fantasy');
