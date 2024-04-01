
CREATE TABLE IF NOT EXISTS public.reviews
(
    id serial PRIMARY KEY,
    session_id integer UNIQUE,
    rating integer,
    comment_reviews text,
    FOREIGN KEY (session_id) REFERENCES public.sessions (id)
);


