create table polos (
    numero             integer not null primary key,
    cod_municipio_sede integer
);

create table municipios (
    cod_tse  integer      not null primary key,
    nome     varchar(255) not null,
    num_polo integer
        constraint fk_polo references polos
);

alter table polos
    add constraint fk_polo_sede foreign key (cod_municipio_sede) references municipios;

create table usuarios (
    cpf   varchar(255) not null primary key,
    email varchar(255),
    nome  varchar(255)
);

alter table usuarios add column ativo boolean;
update usuarios set ativo = 1 where ativo is null;

create table zonas (
    numero             integer not null primary key,
    cod_municipio_sede integer
);

create table secoes (
    id       integer generated always as identity primary key,
    numero   integer not null,
    cod_tse  integer
        constraint fk_sec_municipio references municipios,
    num_polo integer
        constraint fk_sec_polo references polos,
    num_zona integer not null
        constraint fk_sec_zona references zonas
);

create table municipio_zona (
    cod_tse  integer not null
        constraint fk_mz_municipio references municipios,
    num_zona integer not null
        constraint fk_mz_zona references zonas,
    primary key (cod_tse, num_zona)
);