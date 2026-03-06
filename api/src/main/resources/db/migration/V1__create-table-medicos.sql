create table medicos(

    id bigint not null auto_increment, 
    nombre varchar(100) not null, 
    email varchar(100) not null unique, 
    documento varchar(14) not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    barrio varchar(100) not null,
    numero varchar(100), 
    complemento varchar(100),
    ciudad varchar(100) not null, 
    provincia varchar(100) not null,
    codigo_postal varchar(9) not null, 

    primary key(id) 
);