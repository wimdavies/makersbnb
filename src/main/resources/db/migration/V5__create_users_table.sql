CREATE TABLE users (
    id bigserial PRIMARY KEY,
    name text NOT NULL,
    email text UNIQUE NULLS NOT DISTINCT,
    password text,
    password_confirmation text,
    github_id int UNIQUE NULLS NOT DISTINCT
);