package fr.iutfbleau.menu.partie;

import fr.iutfbleau.menu.Menu;

import fr.iutfbleau.menu.partie.Partie;
import fr.iutfbleau.menu.partie.PartieVide;

import javax.swing.JPanel;

import java.awt.CardLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>MenuPartie</code> répertorie et affiche les menus de chaque partie.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class MenuPartie extends JPanel
{

  /**
   * Le deck des parties.
   */
  private CardLayout deck;

  /**
   * Les parties.
   */
  private String[] parties =
  {
    PartieVide.NOM,
    PartieVide.NOM,
    PartieVide.NOM
  };


  /**
   * Le constructeur.
   *
   * @param menu le menu
   */
  public MenuPartie(Menu menu)
  {
    super();

    this.deck = new CardLayout();
    this.setLayout(this.deck);

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
        "select `Nom` from `JOUEUR`;");

      ResultSet resultat = requete.executeQuery();

      for (int i = 0; resultat.next(); i++)
      {
        this.parties[i] = resultat.getString(1);
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

    for (byte i = 0; i < Partie.DISPONIBLES; i++)
    {
      if (this.parties[i] != PartieVide.NOM)
      {
        this.add(new Partie(menu, this.parties[i]), this.parties[i]);
      }
    }

    this.add(new PartieVide(menu), PartieVide.NOM);
  }



  /**
   * Affiche le menu d'une partie.
   *
   * @param partie la partie
   */
  public void afficher(String partie)
  {
    this.deck.show(this, partie);
  }



  /**
   * Retourne une partie.
   *
   * @param  i le numéro de la partie
   * @return la partie
   */
  public String getPartie(byte i)
  {
    return this.parties[i];
  }
}
