

CREATE TABLE IF NOT EXISTS public.courses
(
    id integer PRIMARY KEY,
    course_name character varying(255),
    course_price numeric(5, 2),
    order_id integer,
    FOREIGN KEY (order_id) REFERENCES public.orders (id)
);

