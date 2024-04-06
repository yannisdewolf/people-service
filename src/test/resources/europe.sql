CREATE TABLE public.CITY
(
    ID         numeric PRIMARY KEY,
    NAME       char varying(255) NOT NULL,
    POPULATION numeric           NOT NULL
);

insert into public.city(id, name, population) values (1, 'Brussels', 1209000 );
insert into public.city(id, name, population) values (2, 'Dendermonde', 45800 );