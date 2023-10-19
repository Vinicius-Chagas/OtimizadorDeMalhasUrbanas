alter table Problema add inativo TINYINT default 0;
update Problema set inativo = 0;