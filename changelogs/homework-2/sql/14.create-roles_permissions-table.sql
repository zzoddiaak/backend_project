

CREATE TABLE IF NOT EXISTS public.roles_permissions
(
    role_id integer,
    permission_id integer,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES public.roles (id),
    FOREIGN KEY (permission_id) REFERENCES public.permissions (id)
);

