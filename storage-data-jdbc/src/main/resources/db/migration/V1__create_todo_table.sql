CREATE TABLE todo
(
    id          LONG      NOT NULL PRIMARY KEY,
    title       TEXT      NOT NULL,
    status      TEXT      NOT NULL,
    description TEXT      NULL,
    assignee    TEXT      NULL,
    priority    TEXT      NULL,
    due_date    TIMESTAMP NULL
);
