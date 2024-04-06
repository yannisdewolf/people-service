CREATE TABLE public.CITY
(
    ID         numeric PRIMARY KEY,
    NAME       char varying(255) NOT NULL,
    POPULATION numeric           NOT NULL
);

insert into public.city(id, name, population) values (1, 'Austin', 974000 );
insert into public.city(id, name, population) values (2, 'New York', 8260000 );
insert into public.city(id, name, population) values (3, 'Washington', 671000 );