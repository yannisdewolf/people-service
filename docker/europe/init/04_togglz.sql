create table if not exists europe.togglz
(
    feature_name    varchar(100) not null
        primary key,
    feature_enabled integer,
    strategy_id     varchar(200),
    strategy_params varchar(2000)
);

alter table europe.togglz
    owner to europe_user;