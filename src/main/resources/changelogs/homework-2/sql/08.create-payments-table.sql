
CREATE TABLE IF NOT EXISTS public.payments
(
    id serial PRIMARY KEY,
    user_id integer,
    session_id integer,
    payment_status_id integer,
    url character varying(255),
    order_id integer,
    FOREIGN KEY (user_id) REFERENCES public.users (id),
    FOREIGN KEY (session_id) REFERENCES public.sessions (id),
    FOREIGN KEY (payment_status_id) REFERENCES public.payments_status (id),
    FOREIGN KEY (order_id) REFERENCES public.orders (id)
);

