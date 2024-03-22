

CREATE TABLE IF NOT EXISTS public.clients_cards
(
    id serial PRIMARY KEY,
    psychologist_id integer,
    diagnos character varying(255),
    recommendations text,
    FOREIGN KEY (psychologist_id) REFERENCES public.psychologists (id)
);


