CREATE TABLE IF NOT EXISTS worker (
    worker_id IDENTITY PRIMARY KEY,
    wname VARCHAR(1000) NOT NULL,
    birthday DATE,
    wlevel VARCHAR(10) NOT NULL,
    salary INTEGER
);

ALTER TABLE worker
    ADD CONSTRAINT name_values CHECK (LENGTH(wname) >= 2 AND LENGTH(wname) <= 1000);

ALTER TABLE worker
    ADD CONSTRAINT birthday_values CHECK (birthday >= '1900-01-01');

ALTER TABLE worker
    ADD CONSTRAINT level_values CHECK (wlevel IN ('Trainee', 'Junior', 'Middle', 'Senior'));

ALTER TABLE worker
    ADD CONSTRAINT salary_values CHECK (salary >=100 AND salary <=100000);

CREATE TABLE IF NOT EXISTS client (
    client_id IDENTITY PRIMARY KEY,
    cname VARCHAR(1000) NOT NULL
);

ALTER TABLE client
    ADD CONSTRAINT cname_values CHECK (LENGTH(cname) >= 2 AND LENGTH(cname) <= 1000);

CREATE TABLE IF NOT EXISTS project (
    project_id IDENTITY PRIMARY KEY,
    pname VARCHAR(300),
    client_id BIGINT,
    start_date DATE,
    finish_date DATE,
    FOREIGN KEY(client_id) REFERENCES client(client_id)
);

CREATE TABLE IF NOT EXISTS project_worker (
    project_id BIGINT,
    worker_id BIGINT,
    PRIMARY KEY(project_id, worker_id),
    FOREIGN KEY(project_id) REFERENCES project(project_id),
    FOREIGN KEY(worker_id) REFERENCES worker(worker_id)
);






