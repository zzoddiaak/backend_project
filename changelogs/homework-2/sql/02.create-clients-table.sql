

CREATE TABLE IF NOT EXISTS public.clients
(
    id serial PRIMARY KEY,
    user_id integer UNIQUE,
    client_card_id integer UNIQUE,
    CONSTRAINT client FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT card FOREIGN KEY (client_card_id) REFERENCES public.clients_cards (id)
);

