CREATE TABLE europe.CITY
(
    ID         numeric PRIMARY KEY,
    NAME       char varying(255) NOT NULL,
    POPULATION numeric           NOT NULL
);

GRANT usage on schema europe TO europe_user;
GRANT select on europe.CITY TO europe_user;