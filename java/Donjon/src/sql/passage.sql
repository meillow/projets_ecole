create table `PASSAGE` (
  Piece1 int not null references PIECE(Identifiant),
  Piece2 int not null references PIECE(Identifiant),
  primary key(Piece1, Piece2),
  Etat varchar(6) not null default 'CLOSED',
  DirectionP1 varchar(5),
  DirectionP2 varchar(5)
);

insert into `PASSAGE` (`Piece1`, `Piece2`, `DirectionP1`, `DirectionP2`) values (?, ?, ?, ?);

select `Etat` from `PASSAGE` where `Piece1` = ? and `Piece2` = ?;

update `PASSAGE` set `Etat` = ? where `Piece1` = ? and `Piece2` = ?;
