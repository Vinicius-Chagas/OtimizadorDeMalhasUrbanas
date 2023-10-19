create table Problema (
    id bigint not null auto_increment,
    cep varchar(9) not null,
    tipoProblema varchar (100) not null,
    foto text,
    descricao varchar(100),
    primary key(id)
);
