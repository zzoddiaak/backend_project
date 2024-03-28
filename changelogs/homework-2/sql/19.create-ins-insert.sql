-- Вставки для таблицы roles
INSERT INTO public.roles (id, role_name) VALUES
(1, 'Admin'),
(2, 'User'),
(3, 'Manager'),
(4, 'Guest'),
(5, 'Support');

-- Вставки для таблицы permissions
INSERT INTO public.permissions (id, permission_name) VALUES
(1, 'Read'),
(2, 'Write'),
(3, 'Delete'),
(4, 'Update'),
(5, 'Execute');

-- Вставки для таблицы users
INSERT INTO public.users (id, firstname, secondname, user_password, email, role_id) VALUES
(1, 'John', 'Doe', 'password123', 'john@example.com', 1),
(2, 'Jane', 'Smith', 'abc123', 'jane@example.com', 2),
(3, 'Alice', 'Johnson', 'passw0rd', 'alice@example.com', 2),
(4, 'Bob', 'Brown', 'test123', 'bob@example.com', 3),
(5, 'Eva', 'Williams', 'qwerty', 'eva@example.com', 4);

-- Вставки для таблицы psychologists
INSERT INTO public.psychologists (id, user_id, experience, hourly_rate, rating_value) VALUES
(1, 1, 5, 50.00, 4),
(2, 2, 3, 40.00, 3),
(3, 3, 7, 60.00, 5),
(4, 4, 2, 30.00, 2),
(5, 5, 4, 45.00, 3);

-- Вставки для таблицы clients_cards
INSERT INTO public.clients_cards (id, psychologist_id, diagnos, recommendations) VALUES
(1001, 1, 'Диагноз 1', 'Рекомендации 1'),
(1002, 2, 'Диагноз 2', 'Рекомендации 2'),
(1003, 3, 'Диагноз 3', 'Рекомендации 3'),
(1004, 4, 'Диагноз 4', 'Рекомендации 4'),
(1005, 5, 'Диагноз 5', 'Рекомендации 5');

-- Вставки для таблицы clients
INSERT INTO public.clients (id, user_id, client_card_id) VALUES
(1, 2, 1001),
(2, 3, 1002),
(3, 4, 1003),
(4, 5, 1004);

