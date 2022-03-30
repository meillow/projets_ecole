package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.EtatPassage;
import fr.iutfbleau.projetIHM2020FI2.API.TypeTruc;
import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;

import fr.iutfbleau.MP.PieceP;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>PassageP</code> est l'implémentation persistante
 * de l'interface Passage.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class PassageP implements Passage
{

  /**
   * La première pièce.
   */
  private PieceP pp1;

  /**
   * La seconde pièce.
   */
  private PieceP pp2;


  /**
   * Le constructeur.
   * Les deux pièces doivent être différentes et non null.
   *
   * @param connexion la connexion à la base de données
   * @param  p1       la première pièce
   * @param  p2       la seconde pièce
   * @throws IllegalArgumentException si les deux pièces sont identiques
   * @throws NullPointerException si une des deux pièces est null
   */
  public PassageP(Connection connexion, Piece p1, Piece p2)
  {
    Objects.requireNonNull(p1, "Un passage ne peut pas relier une pièce null.");
    Objects.requireNonNull(p1, "Un passage ne peut pas relier une pièce null.");

    if (p1.equals(p2))
    {
      throw new IllegalArgumentException("Les pièces ne peuvent pas être identiques");
    }

    this.pp1 = (PieceP) p1;
    this.pp2 = (PieceP) p2;

    try
    {
      PreparedStatement requete = connexion.prepareStatement(
        "insert into `PASSAGE` (`Piece1`, `Piece2`) values (?, ?);");

      requete.setInt(1, this.pp1.getIdentifiant());
      requete.setInt(2, this.pp2.getIdentifiant());
      requete.executeUpdate();

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
   * Les deux pièces doivent être différentes et non null.
   *
   * @param  p1 la première pièce
   * @param  p2 la seconde pièce
   * @throws IllegalArgumentException si les deux pièces sont identiques
   * @throws NullPointerException si une des deux pièces est null
   */
  public PassageP(Piece p1, Piece p2)
  {
    Objects.requireNonNull(p1, "Un passage ne peut pas relier une pièce null.");
    Objects.requireNonNull(p1, "Un passage ne peut pas relier une pièce null.");

    if (p1.equals(p2))
    {
      throw new IllegalArgumentException("Les pièces ne peuvent pas être identiques");
    }

    this.pp1 = (PieceP) p1;
    this.pp2 = (PieceP) p2;

    try
    {
      Class.forName("org.mariadb.jdbc.Driver");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println(e);
      System.exit(1);
    }
  }



  /**
   * Retourne l'autre pièce que le passage relie.
   *
   * @param  p une pièce
   * @throws IllegalArgumentException si le passage ne relie pas deux pièces
   * @return l'autre pièce
   */
  @Override
  public Piece getAutrePiece(Piece p)
  {
    if (!this.reliePiece(p))
    {
      throw new IllegalArgumentException("La pièce p ne relie pas ce passage.");
    }
    else
    {
      if (this.pp1.egale(p))
      {
        return this.pp2;
      }
      else
      {
        return this.pp1;
      }
    }
  }



  /**
   * Retourne les deux pièces.
   *
   * @return les deux pièces
   */
  @Override
  public Iterator<Piece> getPieces()
  {
    List<Piece> liste = new ArrayList<>();

    liste.add(this.pp1);
    liste.add(this.pp2);

    return liste.iterator();
  }



  /**
   * Vérifie si une pièce est reliée par le passage.
   *
   * @param  p la pièce
   * @return vrai si la pièce est reliée par le passage, faux sinon
   */
  @Override
  public boolean reliePiece(Piece p)
  {
    PieceP pp = (PieceP) p;

    return (pp.egale(this.pp1)) || (pp.egale(this.pp2));
  }



  /**
   * Retourne l'état du passage.
   *
   * @return l'état du passage
   */
  @Override
  public EtatPassage getEtatPassage()
  {
    EtatPassage retour = null;

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Etat` from `PASSAGE` where `Piece1` = ? and `Piece2` = ?;");

      requete.setInt(1, this.pp1.getIdentifiant());
      requete.setInt(2, this.pp2.getIdentifiant());

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = EtatPassage.valueOf(resultat.getString(1));
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
   * Met à jour l'état du passage.
   *
   * @param  e l'état
   * @throws NullPointerException si l'état est null
   */
  @Override
  public void setEtatPassage(EtatPassage e)
  {
    Objects.requireNonNull(e, "On ne peut pas avoir un état null.");

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "update `PASSAGE` set `Etat` = ? where `Piece1` = ? and `Piece2` = ?;");

      requete.setString(1, e.name());
      requete.setInt(2, this.pp1.getIdentifiant());
      requete.setInt(3, this.pp2.getIdentifiant());
      requete.executeUpdate();

      requete.close();
      connexion.close();
    }
    catch (SQLException ex)
    {
      System.err.println(ex);
      System.exit(1);
    }
  }



  /**
   * Applique l'effet d'un truc sur le passage.
   *
   * @param  t le truc
   * @return vrai si le truc a un effet sur le passage
   */
  @Override
  public boolean agir(Truc t)
  {
    EtatPassage etat = this.getEtatPassage();

    if ((t == null) && (etat == EtatPassage.CLOSED))
    {
       this.setEtatPassage(EtatPassage.OPEN);
       return true;
    }
    else if ((t == null) && (etat == EtatPassage.OPEN))
    {
      this.setEtatPassage(EtatPassage.CLOSED);
      return true;
    }
    else if (t == null)
    {
      return false;
    }
    else
    {
      TypeTruc type = t.getTypeTruc();

      if ((Objects.equals(type, TypeTruc.CLE))
          && (etat == EtatPassage.LOCKED))
      {
        this.setEtatPassage(EtatPassage.OPEN);
        return true;
      }
      else if (Objects.equals(type,TypeTruc.CLE))
      {
        this.setEtatPassage(EtatPassage.LOCKED);
        return true;
      }
      else
      {
        return false;
      }
    }
  }
}
