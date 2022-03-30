package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;

import fr.iutfbleau.MP.ContientTrucsP;
import fr.iutfbleau.MP.PassageP;

import java.util.Objects;
import java.util.Iterator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>PieceP</code> est l'implémentation persistante
 * de l'interface Piece.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class PieceP extends ContientTrucsP implements Piece
{

  /**
   * L'identifiant dans la base de données.
   */
  private int identifiant;


  /**
   * Le constructeur.
   *
   * @param connexion la connexion à la base de données
   * @param partie    le nom de la partie
   */
  public PieceP(Connection connexion, String partie)
  {
    super(connexion);

    try
    {
      PreparedStatement requete = connexion.prepareStatement(
        "insert into `PIECE` (`Conteneur`, `Partie`) values (?, ?);");

      requete.setInt(1, super.getIdentifiant());
      requete.setString(2, partie);
      requete.executeUpdate();
      requete.close();

      requete = connexion.prepareStatement(
        "select `AUTO_INCREMENT` from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'blanche' and TABLE_NAME = 'PIECE';");

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        this.identifiant = resultat.getInt(1) - 1;
      }

      resultat.close();
      requete.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }
  }



  /**
   * Le constructeur.
   *
   * @param i l'identifiant
   */
  public PieceP(int i)
  {
    super(i);

    this.identifiant = i;
  }



  /**
   * Retourne l'identifiant.
   *
   * @return l'identifiant
   */
  @Override
  public int getIdentifiant()
  {
    return this.identifiant;
  }



  /**
   * Ajoute un passage à une pièce.
   *
   * @param  p le passage
   * @param  d la direction
   * @throws NullPointerException si un argument est null
   */
  @Override
  public void setPassage(Direction d, Passage p)
  {
    Objects.requireNonNull(d, "On ne peut pas ajouter un passage dans une direction null.");
    Objects.requireNonNull(p, "On ne peut pas ajouter un passage null.");

    try
    {
      PassageP pp = (PassageP) p;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Piece1`, `Piece2` from `PASSAGE` where `Piece1` = ? and `Piece2` = ?;");

      Iterator<Piece> pieces = p.getPieces();

      for (int i = 1; pieces.hasNext(); i++)
      {
        PieceP pi = (PieceP) pieces.next();
        requete.setInt(i, pi.getIdentifiant());
      }

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        int p1 = resultat.getInt(1);
        int p2 = resultat.getInt(2);

        resultat.close();
        requete.close();

        if (p1 == this.identifiant)
        {
          requete = connexion.prepareStatement(
            "update `PASSAGE` set `DirectionP1` = ? where `Piece1` = ? and `Piece2` = ?;");
        }
        else // if (p2 == this.identifiant)
        {
          requete = connexion.prepareStatement(
            "update `PASSAGE` set `DirectionP2` = ? where `Piece1` = ? and `Piece2` = ?;");
        }

        requete.setString(1, d.name());
        requete.setInt(2, p1);
        requete.setInt(3, p2);
        requete.executeUpdate();
      }

      resultat.close();
      requete.close();
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }
  }



  /**
   * Supprime un passage d'une pièce.
   *
   * @param d la direction
   */
  @Override
  public void removePassage(Direction d)
  {
    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "delete from `PASSAGE` where `Piece1` = ? and `DirectionP1` = ? or `Piece2` = ? and `DirectionP2` = ?;");

      requete.setInt(1, this.identifiant);
      requete.setString(2 ,d.name());
      requete.setInt(3, this.identifiant);
      requete.setString(4, d.name());
      requete.executeUpdate();

      requete.close();
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }
  }



  /**
   * Retourne le passage d'une direction donnée.
   *
   * @param  d la direction
   * @return le passage
   */
  @Override
  public Passage getPassage(Direction d)
  {
    PassageP retour = null;

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Piece1`, `Piece2` from `PASSAGE` where `Piece1` = ? and `DirectionP1` = ? or `Piece2` = ? and `DirectionP2` = ?;");

      requete.setInt(1, this.identifiant);
      requete.setString(2 ,d.name());
      requete.setInt(3, this.identifiant);
      requete.setString(4, d.name());

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = new PassageP(new PieceP(resultat.getInt(1)),
                              new PieceP(resultat.getInt(2)));
      }

      resultat.close();
      requete.close();
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return retour;
  }



  /**
   * Retourne le nombre de passage.
   *
   * @return le nombre de passage
   * @see Direction
   */
  @Override
  public int combienPassages()
  {
    int retour = 0;

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select count(*) from `PASSAGE` where `Piece1` = ? or `Piece2` = ?;");

      requete.setInt(1, this.identifiant);
      requete.setInt(2, this.identifiant);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = resultat.getInt(1);
      }

      resultat.close();
      requete.close();
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return retour;
  }



  /**
   * Retourne la description de la pièce.
   *
   * @return la description de la pièce
   */
  @Override
  public String getDescription()
  {
    StringBuilder sb = new StringBuilder("");
    int passages     = this.combienPassages();

    if (passages == 0)
    {
      sb.append("La pièce n'a pas de sortie. ");
    }
    else if (passages == 1)
    {
      sb.append("La pièce a une seule sortie. ");
    }
    else
    {
      sb.append("La pièce a " + passages + " sorties. ");
    }

    for (Direction d : Direction.values())
    {
      Passage p = this.getPassage(d);

      if (p != null)
      {
        if ((d == Direction.EST) || (d == Direction.OUEST))
        {
          sb.append("À l'" + d + ", un " + p.getDescription() + ". ");
        }
        else
        {
          sb.append("Au " + d + ", un " + p.getDescription() + ". ");
        }
      }
    }

    int goodies = super.combienTrucs();

    if (goodies == 0)
    {
      sb.append("\n" + "La pièce ne contient pas d'objet. ");
    }
    else if (goodies == 1)
    {
      sb.append("\n" + "La pièce contient un objet : ");
    }
    else
    {
      sb.append("\n" + "La pièce contient " + goodies + " objets : ");
    }

    Iterator<Truc> goods = this.getTrucs();

    while (goods.hasNext())
    {
      Truc t = goods.next();
      sb.append("\n _ " + t.getDescription() +".");
    }

    return sb.toString();
  }



  public boolean egale(Piece pp)
  {
    return this.identifiant == ((PieceP) pp).identifiant;
  }
}
