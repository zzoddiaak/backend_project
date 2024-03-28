
CREATE TABLE IF NOT EXISTS public.orders
(
    id serial PRIMARY KEY,
    total_price numeric(5, 2),
    discounts numeric(5, 2),
    psychologists_id integer,
    FOREIGN KEY (psychologists_id) REFERENCES public.psychologists (id)
);

