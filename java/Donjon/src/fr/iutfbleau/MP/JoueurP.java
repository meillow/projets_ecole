package fr.iutfbleau.MP;

import fr.iutfbleau.projetIHM2020FI2.API.TypeTruc;
import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.MP.ContientTrucsP;
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
 * La classe <code>JoueurP</code> est l'implémentation persistante
 * de l'interface Joueur.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class JoueurP extends ContientTrucsP implements Joueur
{

  /**
   * Si un joueur existe.
   */
  public static final int EXISTANT = 1;

  /**
   * Le nom.
   */
  private String nom;


  /**
   * Le constructeur.
   *
   * Le joueur n'est pas dans une pièce à sa création.
   * @see setPiece()
   *
   * @param n le nom
   */
  public JoueurP(String n)
  {
    super();

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
        "insert into `JOUEUR` (`Nom`, `Conteneur`) values (?, ?);");

      requete.setString(1, n);
      requete.setInt(2, super.getIdentifiant());
      requete.executeUpdate();

      requete.close();
      connexion.close();
    }
    catch (SQLException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    this.nom = n;
  }



  /**
   * Le constructeur.
   *
   * Le joueur n'est pas dans une pièce à sa création.
   * @see setPiece()
   *
   * @param n      le nom
   * @param existe si le joueur existe
   */
  public JoueurP(String n, int existe)
  {
    super(n);

    if (existe != JoueurP.EXISTANT)
    {
      throw new IllegalArgumentException("L'option " + existe + " n'est pas reconnue.");
    }

    try
    {
      Class.forName("org.mariadb.jdbc.Driver");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println(e);
      System.exit(1);
    }

    this.nom = n;
  }



  /**
   * Retourne la pièce dans laquelle se trouve le joueur.
   *
   * @return la piece dans laquelle se trouve le joueur
   */
  @Override
  public Piece getPiece()
  {
    Piece retour = null;

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Position` from `JOUEUR` where `Nom` = ?;");

      requete.setString(1, this.nom);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = new PieceP(resultat.getInt(1));
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
   * Met à jour la piece actuelle.
   * L'ancienne pièce est ajoutée au cerveau.
   *
   * @param next la pièce
   */
 @Override
  public void setPiece(Piece next)
  {
    if (this.getPiece() != null)
    {
      this.addVisited(this.getPiece());
    }

    try
    {
      PieceP pp = (PieceP) next;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "update `JOUEUR` set `Position` = ? where `Nom` = ?;");

      requete.setInt(1, pp.getIdentifiant());
      requete.setString(2, this.nom);
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
   * Retourne les pièces visitées.
   *
   * @return les pièces visitées
   */
  @Override
  public Iterator<Piece> getVisited()
  {
    List<Piece> liste = new ArrayList<>();

    try
    {
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Identifiant` from `Piece` where `Partie` = ?;");

      requete.setString(1, this.nom);

      ResultSet resultat = requete.executeQuery();

      while (resultat.next())
      {
        liste.add(new PieceP(resultat.getInt(1)));
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
   * Ajoute une pièce au cerveau.
   *
   * @param  p la pièce
   * @throws NullPointerException si la pièce est null
   * @return vrai si la pièce a été ajoutée, faux sinon
   */
  @Override
  public boolean addVisited(Piece p)
  {
    Objects.requireNonNull(p, "On ne peut pas ajouter une pièce null.");

    try
    {
      PieceP pp = (PieceP) p;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "update `PIECE` set `Visitee` = 1 where `Identifiant` = ?;");

      requete.setInt(1, pp.getIdentifiant());
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
  * Vérifie si une pièce a été visitée.
  *
  * @param  p la pièce
  * @throws NullPointerException si la pièce est null
  * @return vrai si la pièce est présente dans le cerveau
  */
  @Override
  public boolean isVisited(Piece p)
  {
    Objects.requireNonNull(p, "On ne peut pas connaître une pièce null.");

    boolean retour = false;

    try
    {
      PieceP pp = (PieceP) p;

      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Visitee` from `PIECE` where `Identifiant` = ?;");

        requete.setInt(1, pp.getIdentifiant());

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        retour = resultat.getBoolean(1);
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
   * Retourne la description du sac à dos.
   *
   * @return la description du sac à dos
   */
  @Override
  public String getDescription()
  {
    StringBuilder sb = new StringBuilder("");
    int goodies = super.combienTrucs();

    if (goodies == 0)
    {
      sb.append("\n" + "Le sac à dos ne contient pas d'objet.");
    }
    else if (goodies == 1)
    {
      sb.append("\n" + "Le sac à dos contient un objet : ");
    }
    else
    {
      sb.append("\n" + "Le sac à dos contient " + super.combienTrucs() + " objets : ");
    }

    Iterator<Truc> goods = super.getTrucs();

    while (goods.hasNext())
    {
      Truc t = goods.next();
      sb.append("\n _ " + t.getDescription() + ".");
    }

    sb.append("\n");

    return sb.toString();
  }



  /**
   * Le joueur non persistent n'est pas tout à fait comme le buveur du petit prince.
   * Il ne boit pas du rhum pour oublier qu'il en boit
   * mais pour oublier les pièces qu'il connaît.
   *
   * @param  t le truc à utiliser sur le joueur
   * @throws IllegalStateException si le joueur ne posséde pas le truc
   * @return vrai si le truc a un effet sur le joueur
   */
  @Override
  public boolean agir(Truc t)
  {
    if (t == null)
    {
      return false;
    }
    else if (!super.containsTruc(t))
    {
      throw new IllegalStateException("Le joueur ne porte pas l'objet.");
    }
    else
    {
      TypeTruc type = t.getTypeTruc();

      if (Objects.equals(type, TypeTruc.ALCOOL))
      {
        super.removeTruc(t);

        try
        {
          Connection connexion = DriverManager.getConnection(
            "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

          PreparedStatement requete = connexion.prepareStatement(
            "update `PIECE` set `Visitee` = 0 where Partie = ?;");

          requete.setString(1, this.nom);
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
      else if (Objects.equals(type, TypeTruc.EAU))
      {
        super.removeTruc(t);
        return true;
      }
      else
      {
        return false;
      }
    }
  }
}
