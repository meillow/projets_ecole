package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.TypeTruc;
import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.TrucFactory;

import fr.iutfbleau.MP.TrucP;

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
 * La classe <code>TrucFactoryP</code> est l'implémentation persistante
 * de l'interface TrucFactory.
 *
 * @see Joueur, Piece, Truc
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class TrucFactoryP implements TrucFactory
{

  /**
   * La connexion à la base de données.
   */
  private Connection connexion;


  /**
   * Le constructeur.
   */
  public TrucFactoryP()
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
  }



  /**
   * Retourne tous les trucs.
   *
   * @return tous les trucs
   */
  @Override
  public Iterator<Truc> getTrucs()
  {
    List<Truc> liste = new ArrayList<>();

    try
    {
      PreparedStatement requete = this.connexion.prepareStatement(
        "select `Identifiant` from `TRUC`;");

      ResultSet resultat = requete.executeQuery();

      while (resultat.next())
      {
        liste.add(new TrucP(resultat.getInt(1)));
      }

      resultat.close();
      requete.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return liste.iterator();
  }



  /**
   * Ajoute un truc.
   *
   * @param  t le truc
   * @return vrai si le truc a été ajouté
   */
  @Override
  public boolean addTruc(Truc t)
  {
    try
    {
      TrucP tp = (TrucP) t;

      PreparedStatement requete = this.connexion.prepareStatement(
        "update `TRUC` set `Conteneur` = 1 where `Identifiant` = ?;");

      requete.setInt(1, tp.getIdentifiant());
      requete.executeUpdate();

      requete.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return true;
  }



  /**
   * Supprime un truc.
   *
   * @param  t le truc
   * @return vrai si le truc a été supprimé
   */
  @Override
  public boolean removeTruc(Truc t)
  {
    try
    {
      TrucP tp = (TrucP) t;

      PreparedStatement requete = this.connexion.prepareStatement(
        "delete from `TRUC` where `Identifiant` = ?;");

      requete.setInt(1, tp.getIdentifiant());
      requete.executeUpdate();

      requete.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return true;
  }



  /**
   * Vérifie si un truc appartient au contenu.
   *
   * @param  t le truc
   * @return vrai si le truc est présent dans le contenu
   */
  @Override
  public boolean containsTruc(Truc t)
  {
    boolean retour = false;

    try
    {
      TrucP tp = (TrucP) t;

      PreparedStatement requete = this.connexion.prepareStatement(
        "select `Identifiant` from `TRUC` where `Identifiant` = ?;");

      requete.setInt(1, tp.getIdentifiant());

      ResultSet resultat = requete.executeQuery();
      retour = resultat.next();

      resultat.close();
      requete.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return retour;
  }



  /**
   * Retourne le nombre de trucs.
   *
   * @return le nombre de truc
   */
  @Override
  public int combienTrucs()
  {
    int retour = 0;

    try
    {
      PreparedStatement requete = this.connexion.prepareStatement(
        "select count(`Identifiant`) from `TRUC`;");

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = resultat.getInt(1);
      }

      resultat.close();
      requete.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return retour;
  }



  /**
   * Crée un nouveau truc.
   *
   * @param  tt le type du truc
   * @param  d  la description
   * @return le nouveau truc
   */
  @Override
  public Truc newTruc(TypeTruc tt, String description)
  {
    Truc t = new TrucP(this.connexion, tt, description);

    return t;
  }



  /**
   * Ferme l'usine.
   */
  public void fermer()
  {
    try
    {
      this.connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }
  }
}
