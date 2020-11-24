create table `CONTIENT_TRUCS` (
  Identifiant int primary key auto_increment
);

insert into `CONTIENT_TRUCS` values ();

update `TRUC` set `Conteneur` = ? where `Identifiant` = ?;

select `Identifiant` from `TRUC` where `Identifiant` = ? and `Conteneur` = ?;

select count(`Identifiant`) from `TRUC` where `Conteneur` = ?;
