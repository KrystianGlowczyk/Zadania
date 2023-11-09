-- Tworzenie widoku V_TASKS poprawic zeby uzytkownikow bral z deleted = false

CREATE VIEW V_TASKS AS
SELECT
    t.id,
    t.title,
    t.description,
    ts.id AS status_id,
    ts.name AS status_name,
    t.execute_time,
    GROUP_CONCAT(u.name || ' ' || u.surname SEPARATOR ', ') AS assigned_users
FROM TASKS t
JOIN TASK_STATUSES ts ON t.status_id = ts.id
LEFT JOIN task_user tu ON t.id = tu.task_id
LEFT JOIN USERS u ON tu.user_id = u.id
WHERE t.deleted = FALSE
GROUP BY t.id, t.title, t.description, ts.id, ts.name, t.execute_time;


-- Tworzenie widoku V_USERS

CREATE VIEW V_USERS AS
SELECT
    u.id,
    u.name,
    u.surname,
    u.email
FROM USERS u
WHERE u.deleted = FALSE;