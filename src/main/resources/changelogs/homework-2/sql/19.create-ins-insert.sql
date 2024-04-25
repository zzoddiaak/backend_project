-- Вставки для таблицы roles
INSERT INTO public.roles ( role_name) VALUES
( 'ADMIN'),
( 'CLIENT'),
( 'PSYCHOLOGIST');

-- Вставки для таблицы users
INSERT INTO public.users ( firstname, secondname, user_password, email, role_id) VALUES
( 'John', 'Doe', 'password123', 'john@example.com', 1),
( 'Jane', 'Smith', 'abc123', 'jane@example.com', 2),
( 'Alice', 'Johnson', 'passw0rd', 'alice@example.com', 2),
( 'Bob', 'Brown', 'test123', 'bob@example.com', 3),
( 'Eva', 'Williams', 'qwerty', 'eva@example.com', 3);

-- Вставки для таблицы psychologists
INSERT INTO public.psychologists (id, user_id, experience, hourly_rate, rating_value) VALUES
(4, 4, 2, 30.00, 2),
(5, 5, 4, 45.00, 3);

-- Вставки для таблицы clients_cards
INSERT INTO public.clients_cards (id, psychologist_id, diagnos, recommendations) VALUES
(1001, 4, 'Диагноз 1', 'Рекомендации 1'),
(1002, 5, 'Диагноз 2', 'Рекомендации 2'),
(1003, 4, 'Диагноз 3', 'Рекомендации 3'),
(1004, 5, 'Диагноз 4', 'Рекомендации 4'),
(1005, 5, 'Диагноз 5', 'Рекомендации 5');

-- Вставки для таблицы clients
INSERT INTO public.clients (id, user_id, client_card_id) VALUES
(2, 2, 1002),
(3, 3, 1003);

INSERT INTO public.orders (total_price, discounts) VALUES
( 2.22, 100.78),
( 3.77, 100.98),
( 2.98, 100.56),
( 3.45, 100.34),
( 3.34, 100.67);

-- Вставки для таблицы courses
INSERT INTO public.courses ( course_name, course_price, order_id) VALUES
( 'course1', 100.78, 1),
( 'course2', 100.98, 2),
( 'course3', 100.56, 3),
( 'course4', 100.34, 4),
( 'course5', 100.67, 5);

INSERT INTO public.sessions (psychologist_id, client_id, start_session, final_session, course_id, order_id) VALUES
(4, 2, '2024-04-10 10:00:00+00', '2024-04-10 12:00:00+00', 1, 1),
(5, 3, '2024-04-11 09:00:00+00', '2024-04-11 11:00:00+00', 2, 2),
(4, 3, '2024-04-12 10:00:00+00', '2024-04-12 12:00:00+00', 3, 3),
(5, 3, '2024-04-13 11:00:00+00', '2024-04-13 13:00:00+00', 4, 4),
(4, 3, '2024-04-14 12:00:00+00', '2024-04-14 14:00:00+00', 5, 5);
