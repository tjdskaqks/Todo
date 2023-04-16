ALTER TABLE TodoDB.Todo
    ADD COLUMN user_id bigint,
    ADD FOREIGN KEY (user_id) REFERENCES TodoDB.Users(id);
