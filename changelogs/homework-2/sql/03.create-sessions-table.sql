
CREATE TABLE IF NOT EXISTS public.sessions
(
    id integer PRIMARY KEY,
    psychologist_id integer,
    client_id integer,
    start_session timestamp with time zone,
    final_session timestamp without time zone,
    course_id integer,
    order_id integer,
    FOREIGN KEY (psychologist_id) REFERENCES public.psychologists (id),
    FOREIGN KEY (client_id) REFERENCES public.clients (id),
    FOREIGN KEY (course_id) REFERENCES public.courses (id),
    FOREIGN KEY (order_id) REFERENCES public.orders (id)
);

SELECT *
FROM sessions s
LEFT JOIN psychologists p ON s.psychologist_id = p.id
LEFT JOIN clients c ON s.client_id = c.id
LEFT JOIN courses cr ON s.course_id = cr.id
LEFT JOIN orders o ON s.order_id = o.id;


