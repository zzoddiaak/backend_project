

CREATE TABLE IF NOT EXISTS public.users
(
    id serial PRIMARY KEY,
    firstname character varying(20),
    secondname character varying(20),
    user_password character varying(30),
    email character varying(30),
    role_id integer,
    FOREIGN KEY (role_id) REFERENCES public.roles (id)
);

SELECT *
FROM users u
INNER JOIN roles r ON u.role_id = r.id;
