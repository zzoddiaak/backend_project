

CREATE TABLE IF NOT EXISTS public.psychologists
(
    id integer PRIMARY KEY,
    user_id integer UNIQUE,
    experience integer,
    hourly_rate numeric(5, 2),
    rating_value integer,
    FOREIGN KEY (user_id) REFERENCES public.users (id)
);

SELECT *
FROM psychologists p
INNER JOIN users u ON p.user_id = u.id;


