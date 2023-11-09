-- Wypełnianie tabeli statusów
INSERT INTO TASK_STATUSES (id, name) VALUES (1, 'Nowy');
INSERT INTO TASK_STATUSES (id, name) VALUES (2, 'W trakcie');
INSERT INTO TASK_STATUSES (id, name) VALUES (3, 'Zakończony');
INSERT INTO TASK_STATUSES (id, name) VALUES (4, 'Wstrzymany');

-- Wypełnianie tabeli użytkowników
INSERT INTO USERS (name, surname, email) VALUES ('Anna', 'Kowalska', 'anna.kowalska@example.com');
INSERT INTO USERS (name, surname, email) VALUES ('Jan', 'Nowak', 'jan.nowak@example.com');
INSERT INTO USERS (name, surname, email) VALUES ('Piotr', 'Wiśniewski', 'piotr.wisniewski@example.com');

-- Wypełnianie tabeli zadań (zakładam, że statusy są już wypełnione i używam ich ID)
INSERT INTO TASKS (title, description, status_id, execute_time) VALUES ('Zadanie 1', 'Opis zadania 1', 1, '2023-11-06T12:00:00');
INSERT INTO TASKS (title, description, status_id, execute_time) VALUES ('Zadanie 2', 'Opis zadania 2', 2, '2023-11-07T15:00:00');
INSERT INTO TASKS (title, description, status_id, execute_time) VALUES ('Zadanie 3', 'Opis zadania 3', 1, '2023-11-08T18:00:00');

-- Przypisanie użytkowników do zadań (zakładam, że relacyjna tabela 'task_user' istnieje)
-- Anna Kowalska jest przypisana do Zadania 1
INSERT INTO task_user (task_id, user_id) VALUES (1, 1);
-- Jan Nowak jest przypisany do Zadania 2 i 3
INSERT INTO task_user (task_id, user_id) VALUES (2, 2);
INSERT INTO task_user (task_id, user_id) VALUES (3, 2);
-- Piotr Wiśniewski jest przypisany do Zadania 1 i 3
INSERT INTO task_user (task_id, user_id) VALUES (1, 3);
INSERT INTO task_user (task_id, user_id) VALUES (3, 3);
