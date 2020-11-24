package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;
import fr.iutfbleau.projetIHM2020FI2.API.PassagePieceFactory;

import fr.iutfbleau.MP.PieceP;
import fr.iutfbleau.MP.PassageP;

import java.util.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>PassagePieceFactoryP</code> est l'implémentation persistante
 * de l'interface PassagePieceFactory.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class PassagePieceFactoryP implements PassagePieceFactory
{

  /**
   * La connexion à la base de données.
   */
  private Connection connexion;

  /**
   * Le nom de la partie.
   */
  private String partie;


  /**
   * Le constructeur.
   *
   * @param p le nom de la partie
   */
  public PassagePieceFactoryP(String p)
  {
    try
    {
      Class.forName("org.mariadb.jdbc.Driver");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    try
    {
      this.connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    this.partie = p;
  }



  /**
   * Crée une nouvellle pièce.
   *
   * @return la nouvelle pièce
   */
  @Override
  public Piece newPiece()
  {
    return new PieceP(this.connexion, this.partie);
  }



  /**
   * Supprime une pièce.
   *
   * @param  p1 la pièce
   * @return vrai si la pièce a été supprimée, faux sinon
   */
  @Override
  public boolean removePiece(Piece p1)
  {
    Objects.requireNonNull(p1, "On ne peut pas enlever une pièce null.");

    try
    {
      PieceP pp = (PieceP) p1;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "delete from `PASSAGE` where `Piece1` = ? or `Piece2` = ?;");

      requete.setInt(1, pp.getIdentifiant());
      requete.setInt(2, pp.getIdentifiant());
      requete.executeUpdate();

      requete.close();

      requete = connexion.prepareStatement(
        "delete from `PIECE` where `Identifiant` = ?;");

      requete.setInt(1, pp.getIdentifiant());
      requete.executeUpdate();

      requete.close();
      connexion.close();
    }
    catch (SQLException ex)
    {
      System.err.println(ex);
      System.exit(1);
    }

    return true;
  }



  /**
   * Supprime un passage.
   *
   * @param  p le passage
   * @return vrai si le passage a été supprimé
   */
  @Override
  public boolean removePassage(Passage p)
  {
    /* M. Madelaine ne l'a pas faite non plus. */
    throw new UnsupportedOperationException("Désolé, fonction non supportée.");
  }



  /**
   * Crée un nouveau passage.
   *
   * @param  d1 la première direction
   * @param  p1 la première pièce
   * @param  d2 la seconde direction
   * @param  p2 la seconde pièce
   * @throws IllegalArgumentException si les deux pièces sont identiques
   * @throws NullPointerException si une des deux pièces est null
   */
  @Override
  public Passage newPassage(Direction d1, Piece p1, Direction d2, Piece p2)
  {
    Objects.requireNonNull(p1, "La pièce p1 ne peut être null.");
    Objects.requireNonNull(d1, "La direction d1 ne peut être null.");
    Objects.requireNonNull(p2, "La pièce p2 ne peut être null.");
    Objects.requireNonNull(d2, "La direction d2 ne peut être null.");

    if (p1.getPassage(d1) != null)
    {
      throw new IllegalArgumentException("La direction d1 n'est pas libre dans la pièce p1");
    }

    if (p2.getPassage(d2) != null)
    {
      throw new IllegalArgumentException("La direction d2 n'est pas libre dans la pièce p2");
    }

    Passage p = new PassageP(this.connexion, p1, p2);

    p1.setPassage(d1, p);
    p2.setPassage(d2, p);

    return p;
  }



  /**
   * Ferme l'usine.
   */
  public void fermer()
  {
    try
    {
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }
  }
}
