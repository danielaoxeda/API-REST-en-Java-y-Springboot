create table pacientes(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    telefono varchar(100) not null unique,
    documento varchar(14) not null unique,
    calle varchar(100) not null,
    barrio varchar(100) not null,
    numero varchar(100),
    complemento varchar(100),
    ciudad varchar(100) not null,
    provincia varchar(100) not null,
    codigo_postal varchar(9) not null,
    activo tinyint default 1,

    primary key(id)
);