package fr.iutfbleau.jeu;

import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.Chargement;

import fr.iutfbleau.jeu.donjon.Donjon;

import fr.iutfbleau.jeu.inventaire.Inventaire;

import fr.iutfbleau.jeu.carte.Carte;

import javax.swing.JPanel;

import java.awt.CardLayout;


/**
 * La classe <code>Jeu</code> répertorie et affiche les différentes parties du jeu.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Jeu extends JPanel
{

  /**
   * Le donjon.
   */
  public static final String DONJON = "donjon";

  /**
   * L'inventaire.
   */
  public static final String INVENTAIRE = "inventaire";

  /**
   * La carte.
   */
  public static final String CARTE = "carte";

  /**
   * Le deck de composants.
   */
  private CardLayout deck;

  /**
   * La fenêtre.
   */
  private Fenetre fenetre;

  /**
   * L'inventaire.
   */
  private Inventaire inventaire;

  /**
   * La carte.
   */
  private Carte carte;

  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   */
  public Jeu(Fenetre f)
  {
    super();

    this.fenetre = f;

    this.deck = new CardLayout();
    this.setLayout(this.deck);
  }



  /**
   * Prépare la partie du joueur.
   *
   * @param joueur le joueur
   */
  public void setJoueur(Joueur joueur)
  {
    Chargement chargement = new Chargement(this.fenetre, "Chargement du donjon...");
    chargement.setVisible(true);

    Donjon donjon = new Donjon(joueur, this.fenetre, this);

    chargement.setTexte("Chargement de l'inventaire...");
    this.inventaire = new Inventaire(joueur, this.fenetre, this, donjon.getSalle());

    chargement.setTexte("Chargement de la carte...");
    this.carte = new Carte(joueur, this);

    chargement.dispose();

    this.add(donjon, Jeu.DONJON);
    this.add(this.inventaire, Jeu.INVENTAIRE);
    this.add(this.carte, Jeu.CARTE);
  }



  /**
   * Affiche une partie du jeu.
   *
   * @param jeu la partie
   */
  public void afficher(String jeu)
  {
    this.deck.show(this, jeu);

    if (jeu == Jeu.INVENTAIRE)
    {
      this.inventaire.rafraichir();
    }

    if (jeu == Jeu.CARTE)
    {
      Chargement chargement = new Chargement(this.fenetre, "Chargement de la carte...");
      chargement.setVisible(true);
      this.carte.rafraichir();
      chargement.dispose();
    }
  }
}
