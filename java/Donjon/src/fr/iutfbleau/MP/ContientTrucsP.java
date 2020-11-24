package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.ContientTrucs;

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
 * La classe <code>ContientTrucsP</code> est l'implémentation persistante
 * de l'interface ContientTrucs.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class ContientTrucsP implements ContientTrucs
{

  /**
   * L'identifiant dans la base de données.
   */
  private int identifiant;


  /**
   * Le constructeur.
   */
  public ContientTrucsP()
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
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "insert into `CONTIENT_TRUCS` values ();");

      requete.executeUpdate();
      requete.close();

      requete = connexion.prepareStatement(
        "select `AUTO_INCREMENT` from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'blanche' and TABLE_NAME = 'CONTIENT_TRUCS';");

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        this.identifiant = resultat.getInt(1) - 1;
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
   * Le constructeur.
   *
   * @param connexion la connexion à la base de données
   */
  public ContientTrucsP(Connection connexion)
  {
    try
    {
      PreparedStatement requete = connexion.prepareStatement(
        "insert into `CONTIENT_TRUCS` values ();");

      requete.executeUpdate();
      requete.close();

      requete = connexion.prepareStatement(
        "select `AUTO_INCREMENT` from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'blanche' and TABLE_NAME = 'CONTIENT_TRUCS';");

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
   * @param i l'identifiant d'une pièce
   */
  public ContientTrucsP(int i)
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
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Conteneur` from `PIECE` where `Identifiant` = ?;");

      requete.setInt(1, i);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        this.identifiant = resultat.getInt(1);
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
   * Le constructeur.
   *
   * @param nom le nom de la partie
   */
  public ContientTrucsP(String nom)
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
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Conteneur` from `JOUEUR` where `Nom` = ?;");

      requete.setString(1, nom);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        this.identifiant = resultat.getInt(1);
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
   * Retourne l'identifiant.
   *
   * @return l'identifiant
   */
  public int getIdentifiant()
  {
    return this.identifiant;
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
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Identifiant` from `TRUC` where `Conteneur` = ?;");

      requete.setInt(1, this.identifiant);

      ResultSet resultat = requete.executeQuery();

      while (resultat.next())
      {
        liste.add(new TrucP(resultat.getInt(1)));
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

    return liste.iterator();
  }



  /**
   * Ajoute un truc.
   *
   * @param  t le truc
   * @throws NullPointerException si le truc est null
   * @return vrai si le truc a été ajouté, faux sinon
   */
  @Override
  public boolean addTruc(Truc t)
  {
    Objects.requireNonNull(t, "On ne peut pas ajouter un truc null.");

    try
    {
      TrucP tp = (TrucP) t;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "update `TRUC` set `Conteneur` = ? where `Identifiant` = ?;");

      requete.setInt(1, this.identifiant);
      requete.setInt(2, tp.getIdentifiant());
      requete.executeUpdate();

      requete.close();
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    return true;
  }



  /**
   * Retire un truc.
   *
   * @param  t le truc
   * @throws NullPointerException si le truc est null
   * @return vrai si le truc a été retiré, faux sinon
   */
  @Override
  public boolean removeTruc(Truc t)
  {
    Objects.requireNonNull(t, "On ne peut pas enlever un truc null.");

    try
    {
      TrucP tp = (TrucP) t;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "delete from `TRUC` where `Identifiant` = ?;");

      requete.setInt(1, tp.getIdentifiant());
      requete.executeUpdate();

      requete.close();
      connexion.close();
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
   * @throws NullPointerException si le truc null
   * @return vrai si le truc est présent dans le contenu
   */
  @Override
  public boolean containsTruc(Truc t)
  {
    Objects.requireNonNull(t, "On ne peut pas contenir un truc null");

    boolean retour = false;

    try
    {
      TrucP tp = (TrucP) t;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Identifiant` from `TRUC` where `Identifiant` = ? and `Conteneur` = ?;");

      requete.setInt(1, tp.getIdentifiant());
      requete.setInt(2, this.identifiant);

      ResultSet resultat = requete.executeQuery();
      retour = resultat.next();

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
   * Retourne le nombre de trucs.
   *
   * @return le nombre de trucs
   */
  @Override
  public int combienTrucs()
  {
    int retour = 0;

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select count(`Identifiant`) from `TRUC` where `Conteneur` = ?;");

      requete.setInt(1, this.identifiant);

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
}
