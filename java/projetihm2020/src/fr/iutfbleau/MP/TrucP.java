package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.TypeTruc;
import fr.iutfbleau.projetIHM2020FI2.API.Truc;

import java.util.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>TrucP</code> est l'implémentation persistante
 * de l'interface Truc.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class TrucP implements Truc
{

  /**
   * L'identifiant dans la base de données.
   */
  private int identifiant;


  /**
   * Le constructeur.
   *
   * @param connexion la connexion à la base de données
   * @param  tt       le type du truc
   * @param  d        la description
   * @throws NullPointerException si un paramètre est null
   */
  public TrucP(Connection connexion, TypeTruc tt, String d)
  {
    Objects.requireNonNull(tt, "On ne peut pas créer un truc avec un TypeTruc null.");
    Objects.requireNonNull(d, "On ne peut pas créer un truc avec une description null.");

    try
    {
      PreparedStatement requete = connexion.prepareStatement(
        "insert into `TRUC` (`Description`, `Type`) values (?, ?);");

      requete.setString(1, d);
      requete.setString(2, tt.name());
      requete.executeUpdate();
      requete.close();

      requete = connexion.prepareStatement(
        "select `AUTO_INCREMENT` from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'blanche' and TABLE_NAME = 'TRUC';");

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
  public TrucP(int i)
  {
    this.identifiant = i;
  }



  /**
   * Retourne l'identifiant.
   *
   * @return l'identifiant
   */
  public int getIdentifiant()
  {
    return this.identifiant;
  }



  /**
   * Retourne le type du truc.
   *
   * @return le type du truc
   */
  public TypeTruc getTypeTruc()
  {
    TypeTruc retour = null;

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Type` from `TRUC` where `Identifiant` = ?;");

      requete.setInt(1, this.identifiant);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = TypeTruc.valueOf(resultat.getString(1));
      }

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
   * Retourne la description du truc.
   *
   * @return la description du truc
   */
  public String getDescription()
  {
    String retour = "";

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Description` from `TRUC` where `Identifiant` = ?;");

      requete.setInt(1, this.identifiant);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = resultat.getString(1);
      }

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
}
