

CREATE TABLE IF NOT EXISTS public.sessions_requests
(
    id serial PRIMARY KEY,
    psychologist_id integer,
    client_id integer,
    request_date date,
    request_status_id integer,
    problem text,
    session_id integer UNIQUE,
    FOREIGN KEY (psychologist_id) REFERENCES public.psychologists (id),
    FOREIGN KEY (client_id) REFERENCES public.clients (id),
    FOREIGN KEY (session_id) REFERENCES public.sessions (id),
    FOREIGN KEY (request_status_id) REFERENCES public.requests_status (id)
);


