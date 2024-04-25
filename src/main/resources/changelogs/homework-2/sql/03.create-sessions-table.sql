
CREATE TABLE IF NOT EXISTS public.sessions
(
    id serial PRIMARY KEY,
    psychologist_id integer,
    client_id integer,
    start_session timestamp,
    final_session timestamp,
    course_id integer,
    order_id integer,
    FOREIGN KEY (psychologist_id) REFERENCES public.psychologists (id),
    FOREIGN KEY (client_id) REFERENCES public.clients (id),
    FOREIGN KEY (course_id) REFERENCES public.courses (id),
    FOREIGN KEY (order_id) REFERENCES public.orders (id)
);


