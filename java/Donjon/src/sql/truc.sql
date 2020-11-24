create table `TRUC` (
  Identifiant int primary key auto_increment,
  Description varchar(50) not null,
  Type varchar(7) not null,
  Conteneur int default 1 references CONTIENT_TRUCS(Identifiant)
);

insert into `TRUC` (`Description`, `Type`) values (?, ?);

select `Type` from `TRUC` where `Identifiant` = ?;

select `Description` from `TRUC` where `Identifiant` = ?;
