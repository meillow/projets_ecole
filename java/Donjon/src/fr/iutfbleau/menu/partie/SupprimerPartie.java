package fr.iutfbleau.menu.partie;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.ChangerMenu;

import java.awt.event.ActionEvent;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>SupprimerPartie</code> est le contrôleur qui supprime une partie.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class SupprimerPartie extends ChangerMenu
{

  /**
   * La partie.
   */
  private String partie;


  /**
   * Le constructeur.
   *
   * @param m le menu
   * @param p la partie
   */
  public SupprimerPartie(Menu m, String p)
  {
    super(m);

    this.partie = p;
  }



  /**
   * Supprime la partie.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      Class.forName("org.mariadb.jdbc.Driver");
    }
    catch (ClassNotFoundException ex)
    {
      System.err.println(e);
      System.exit(1);
    }

    try
    {
      List<Integer> pieces     = new ArrayList<>();
      List<Integer> conteneurs = new ArrayList<>();

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Identifiant`, `Conteneur` from `PIECE` where `Partie` = ?;");

      requete.setString(1, this.partie);

      ResultSet resultat = requete.executeQuery();

      while (resultat.next())
      {
        pieces.add(resultat.getInt(1));
        conteneurs.add(resultat.getInt(2));
      }

      resultat.close();
      requete.close();

      requete = connexion.prepareStatement(
        "select `Conteneur` from `JOUEUR` where `Nom` = ?;");

      requete.setString(1, this.partie);

      resultat = requete.executeQuery();

      if (resultat.next())
      {
        conteneurs.add(resultat.getInt(1));
      }

      resultat.close();
      requete.close();

      requete = connexion.prepareStatement(
        "delete from `PASSAGE` where `Piece1` = ? or `Piece2` = ?;");

      for (Integer i: pieces)
      {
        requete.setInt(1, i);
        requete.setInt(2, i);
        requete.executeUpdate();
      }

      requete.close();

      requete = connexion.prepareStatement(
        "delete from `TRUC` where `Conteneur` = ?;");

      for (Integer i: conteneurs)
      {
        requete.setInt(1, i);
        requete.executeUpdate();
      }

      requete.close();

      requete = connexion.prepareStatement(
        "update `JOUEUR` set `Position` = null where `Nom` = ?;");

      requete.setString(1, this.partie);
      requete.executeUpdate();

      requete.close();

      requete = connexion.prepareStatement(
        "delete from `PIECE` where `Identifiant` = ?;");

      for (Integer i: pieces)
      {
        requete.setInt(1, i);
        requete.executeUpdate();
      }

      requete.close();

      requete = connexion.prepareStatement(
        "delete from `JOUEUR` where `Nom` = ?;");

      requete.setString(1, this.partie);
      requete.executeUpdate();

      requete.close();

      requete = connexion.prepareStatement(
        "delete from `CONTIENT_TRUCS` where `Identifiant` = ?;");

      for (Integer i: conteneurs)
      {
        requete.setInt(1, i);
        requete.executeUpdate();
      }

      requete.close();

      requete.close();
      connexion.close();
    }
    catch (SQLException ex)
    {
      System.err.println(ex);
      System.exit(1);
    }

    this.menu.rafraichir();
    this.menu.afficher(Menu.JOUER);
  }
}
