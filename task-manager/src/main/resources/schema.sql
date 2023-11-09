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
