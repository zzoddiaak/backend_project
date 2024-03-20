
CREATE TABLE IF NOT EXISTS public.reviews
(
    id integer PRIMARY KEY,
    session_id integer UNIQUE,
    rating integer,
    comment_reviews text,
    FOREIGN KEY (session_id) REFERENCES public.sessions (id)
);


SELECT *
FROM reviews rv
INNER JOIN sessions s ON rv.session_id = s.id;
