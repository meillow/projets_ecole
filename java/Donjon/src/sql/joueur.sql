create table `JOUEUR` (
  Nom varchar(30) primary key,
  Position int default null references PIECE(Identifiant),
  Conteneur int not null references CONTIENT_TRUCS(Identifiant)
);

insert into `JOUEUR` (`Nom`, `Conteneur`) values (?, ?);

select `Position` from `JOUEUR` where `Nom` = ?;

update `JOUEUR` set `Position` = ? where `Nom` = ?;

update `PIECE` set `Visitees` = ? where `Identifiant` = ?;

select `Identifiant` from `PIECE` where `Identifiant` = ?;
