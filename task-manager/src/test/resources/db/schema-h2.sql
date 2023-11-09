CREATE TABLE TASK_STATUSES (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE USERS (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE TASKS (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    status_id BIGINT,
    execute_time TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (status_id) REFERENCES TASK_STATUSES(id)
);

CREATE TABLE task_user (
    task_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (task_id, user_id),
    FOREIGN KEY (task_id) REFERENCES TASKS(id),
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);


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