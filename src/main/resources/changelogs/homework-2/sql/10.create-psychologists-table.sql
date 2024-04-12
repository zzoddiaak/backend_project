

CREATE TABLE IF NOT EXISTS public.psychologists
(
    id serial PRIMARY KEY,
    user_id integer UNIQUE,
    experience integer,
    hourly_rate numeric(5, 2),
    rating_value integer,
    FOREIGN KEY (user_id) REFERENCES public.users (id)
);



