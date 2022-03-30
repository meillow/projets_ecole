package fr.iutfbleau.fenetre;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.EtatPassage;
import fr.iutfbleau.projetIHM2020FI2.API.TypeTruc;
import fr.iutfbleau.projetIHM2020FI2.API.Truc;
// import fr.iutfbleau.projetIHM2020FI2.API.TrucFactory;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;
// import fr.iutfbleau.projetIHM2020FI2.API.PassagePieceFactory;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.Chargement;
import fr.iutfbleau.fenetre.ChangerFenetre;

import fr.iutfbleau.jeu.Jeu;

import fr.iutfbleau.MP.TrucFactoryP;
import fr.iutfbleau.MP.PassagePieceFactoryP;
import fr.iutfbleau.MP.JoueurP;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;


/**
 * La classe <code>CreerPartie</code> est le contrôleur qui crée une nouvelle partie et
 * ordonne à une fenêtre de l'afficher.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class CreerPartie extends ChangerFenetre
{

  /**
   * Le jeu.
   */
  private Jeu jeu;


  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   * @param j le jeu
   */
  public CreerPartie(Fenetre f, Jeu j)
  {
    super(f);

    this.jeu = j;
  }



  /**
   * Crée une nouvelle partie et ordonne à la fenêtre de l'afficher.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String partie = e.getActionCommand();

    if (partie.equals(""))
    {
      JOptionPane.showMessageDialog(this.fenetre,
        "Veuillez saisir un nom avant de commencer.");
      return;
    }

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
      Connection connexion = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/blanche", "blanche", "antbla01");

      PreparedStatement requete = connexion.prepareStatement(
        "select `Nom` from `JOUEUR` where `Nom` = ?;");

      requete.setString(1, partie);

      ResultSet resultat = requete.executeQuery();

      if (resultat.next())
      {
        resultat.close();
        requete.close();
        connexion.close();

        JOptionPane.showMessageDialog(this.fenetre,
          "Ce nom existe déjà.");
        return;
      }

      resultat.close();
      requete.close();
      connexion.close();
    }
    catch (SQLException ex)
    {
      System.err.println(ex);
      System.exit(1);
    }

    // TODO générer un donjon aléatoirement.

    Joueur j = new JoueurP(partie);

    Chargement chargement = new Chargement(this.fenetre, "Création du donjon...");
    chargement.setVisible(true);

		PassagePieceFactoryP usinePassagePiece = new PassagePieceFactoryP(partie);

		Piece p0 = usinePassagePiece.newPiece();
		Piece p1 = usinePassagePiece.newPiece();
		Piece p2 = usinePassagePiece.newPiece();
		Piece p3 = usinePassagePiece.newPiece();
		Piece p4 = usinePassagePiece.newPiece();
		Piece p5 = usinePassagePiece.newPiece();
		Piece p6 = usinePassagePiece.newPiece();
		Piece p7 = usinePassagePiece.newPiece();
		Piece p8 = usinePassagePiece.newPiece();
		Piece p9 = usinePassagePiece.newPiece();

		Passage p0p1 = usinePassagePiece.newPassage(Direction.OUEST, p0, Direction.EST, p1);
		// p0p3.setEtatPassage(EtatPassage.OPEN);
		Passage p0p2 = usinePassagePiece.newPassage(Direction.NORD, p0, Direction.SUD, p2);
		Passage p0p3 = usinePassagePiece.newPassage(Direction.EST, p0, Direction.OUEST, p3);
		p0p3.setEtatPassage(EtatPassage.LOCKED);
		Passage p1p5 = usinePassagePiece.newPassage(Direction.SUD, p1, Direction.NORD, p5);
		Passage p2p4 = usinePassagePiece.newPassage(Direction.EST, p2, Direction.OUEST, p4);
		Passage p3p9 = usinePassagePiece.newPassage(Direction.EST, p3, Direction.OUEST, p9);
		Passage p4p3 = usinePassagePiece.newPassage(Direction.SUD, p4, Direction.NORD, p3);
		Passage p5p6 = usinePassagePiece.newPassage(Direction.EST, p5, Direction.OUEST, p6);
		Passage p6p7 = usinePassagePiece.newPassage(Direction.EST, p6, Direction.OUEST, p7);
		Passage p7p8 = usinePassagePiece.newPassage(Direction.EST, p7, Direction.OUEST, p8);
		Passage p8p9 = usinePassagePiece.newPassage(Direction.NORD, p8, Direction.SUD, p9);

    usinePassagePiece.fermer();

		j.setPiece(p0);

    chargement.setTexte("Création des trucs...");

		TrucFactoryP usineTruc = new TrucFactoryP();

		Truc t0 = usineTruc.newTruc(TypeTruc.ALCOOL, "une bouteille de rhum hors d'âge");
		Truc t1 = usineTruc.newTruc(TypeTruc.CLE, "une clé en bronze");
		Truc t2 = usineTruc.newTruc(TypeTruc.EAU, "une cruche d'eau");
		Truc t3 = usineTruc.newTruc(TypeTruc.EAU, "une gourde d'eau");
		Truc t4 = usineTruc.newTruc(TypeTruc.GOODIES, "une bourse en cuir avec 10 euros");
		Truc t5 = usineTruc.newTruc(TypeTruc.GOODIES, "une bourse en cuir avec 10 euros");

    usineTruc.fermer();

		p0.addTruc(t0);
    p0.addTruc(t1);
    p0.addTruc(t2);
    p1.addTruc(t3);
    p2.addTruc(t4);
    p3.addTruc(t5);

    chargement.dispose();

    this.jeu.setJoueur(j);
    this.fenetre.afficher(Fenetre.JEU);
  }
}
