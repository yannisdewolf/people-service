create schema europe;

create user europe_user with password 'pw1';

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA europe TO europe_user;
-- GRANT USAGE ON SCHEMA europe TO europe_user;

-- allow creation of objects within the schema europe
-- disable since we create it via a script
-- GRANT CREATE on SCHEMA europe TO europe_user;