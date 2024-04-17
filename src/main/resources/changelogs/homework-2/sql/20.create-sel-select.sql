

SELECT *
FROM reviews rv
INNER JOIN sessions s ON rv.session_id = s.id;

SELECT *
FROM psychologists p
INNER JOIN users u ON p.user_id = u.id;

SELECT *
FROM payments pm
LEFT JOIN users u ON pm.user_id = u.id
LEFT JOIN sessions s ON pm.session_id = s.id
LEFT JOIN payments_status ps ON pm.payment_status_id = ps.id
LEFT JOIN orders o ON pm.order_id = o.id;

SELECT *
FROM sessions s
LEFT JOIN psychologists p ON s.psychologist_id = p.id
LEFT JOIN clients c ON s.client_id = c.id
LEFT JOIN courses cr ON s.course_id = cr.id
LEFT JOIN orders o ON s.order_id = o.id;

SELECT *
FROM users u
INNER JOIN roles r ON u.role_id = r.id;
