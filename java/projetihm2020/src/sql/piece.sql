create table `PIECE` (
  Identifiant int primary key auto_increment,
  Visitee boolean not null default false,
  Conteneur int not null references CONTIENT_TRUCS(Identifiant),
  Partie varchar(30)
);

alter table PIECE add Partie varchar(30) not null references JOUEUR(Nom);

insert into `PIECE` (`Conteneur`, `Partie`) values (?, ?);

select `Piece1`, `Piece2` from `PASSAGE` where `Piece1` = ? and `DirectionP1` = ? or `Piece2` = ? and `DirectionP2` = ?;

select count(*) from `PASSAGE` where `Piece1` = ? or `Piece2` = ?;
