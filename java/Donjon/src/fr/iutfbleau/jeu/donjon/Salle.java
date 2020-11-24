package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;

import fr.iutfbleau.jeu.donjon.Donjon;
import fr.iutfbleau.jeu.donjon.Mur;

import javax.swing.JPanel;

import java.awt.CardLayout;


/**
 * La classe <code>Salle</code> est la vue d'une pièce.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Salle extends JPanel
{

  /**
   * Le nombre de murs.
   */
  public static final byte NOMBRE_MURS = 4;

  /**
   * Le deck de murs.
   */
  private CardLayout deck;

  /**
   * La direction.
   */
  private Direction actuelle;

  /**
   * Les murs.
   */
  private Mur[] murs;


  /**
   * Le constructeur.
   *
   * @param joueur  le joueur
   * @param fenetre la fenêtre
   * @param donjon  le donjon
   */
  public Salle(Joueur joueur, Fenetre fenetre, Donjon donjon)
  {
    super();

    this.deck = new CardLayout();
    this.setLayout(this.deck);

    this.actuelle = Direction.NORD;
    this.murs     = new Mur[Salle.NOMBRE_MURS];

    for (byte i = 0; i < Salle.NOMBRE_MURS; i++)
    {
      this.murs[i] = new Mur(joueur, fenetre, donjon, Direction.values()[i]);
      this.add(this.murs[i]);
    }

    this.setPiece(joueur.getPiece());
  }



  /**
   * Retourne la direction actuelle.
   *
   * @return la direction actuelle
   */
  public Direction getActuelle()
  {
    return this.actuelle;
  }



  /**
   * Retourne un mur.
   *
   * @param  o l'orientation
   * @return le mur
   */
  public Mur getMur(Direction o)
  {
    return this.murs[o.ordinal()];
  }



  /**
   * Met à jour la pièce.
   *
   * @param p la pièce
   */
  public void setPiece(Piece p)
  {
    for (byte i = 0; i < Salle.NOMBRE_MURS; i++)
    {
      this.murs[i].setPiece(p);
    }
  }



  /**
   * Affiche le mur à gauche.
   */
  public void afficherMurGauche()
  {
    if (this.actuelle.ordinal() == 0)
    {
      this.actuelle = Direction.values()[Direction.values().length-1];
    }
    else
    {
      this.actuelle = Direction.values()[this.actuelle.ordinal()-1];
    }

    this.deck.previous(this);
  }



  /**
   * Affiche le mur à droite.
   */
  public void afficherMurDroite()
  {
    this.actuelle = Direction.values()[(this.actuelle.ordinal()+1)%Direction.values().length];

    this.deck.next(this);
  }
}
