package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;

import fr.iutfbleau.jeu.donjon.Donjon;
import fr.iutfbleau.jeu.donjon.Porte;
import fr.iutfbleau.jeu.donjon.ChangerPiece;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;


/**
 * La classe <code>Mur</code> est la vue d'un mur.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Mur extends JLabel
{

  /**
   * L'orientation.
   */
  private Direction orientation;

  /**
   * La porte en face.
   */
  private Porte enFace;

  /**
   * La porte à gauche.
   */
  private Porte aGauche;

  /**
   * La porte à droite.
   */
  private Porte aDroite;

  /**
   * Le contrôleur de la porte.
   */
  private ChangerPiece controleur;


  /**
   * Le constructeur.
   *
   * @param joueur  le joueur
   * @param fenetre la fenêtre
   * @param donjon  le donjon
   * @param o       l'orientation
   */
  public Mur(Joueur joueur, Fenetre fenetre, Donjon donjon, Direction o)
  {
    super(new ImageIcon(Thread.currentThread().getContextClassLoader()
                        .getResource("img/fonds/piece.jpg")));


    this.orientation = o;
    this.enFace      = new Porte(Porte.EN_FACE);
    this.aGauche     = new Porte(Porte.GAUCHE);
    this.aDroite     = new Porte(Porte.DROITE);

    this.setLayout(new GridLayout(1, 3));

    this.aGauche.setHorizontalAlignment(JLabel.LEFT);
    this.add(this.aGauche);

    this.enFace.setHorizontalAlignment(JLabel.CENTER);
    this.add(this.enFace);

    this.aDroite.setHorizontalAlignment(JLabel.RIGHT);
    this.add(this.aDroite);

    this.controleur = new ChangerPiece(joueur, fenetre, donjon);
    this.enFace.addMouseListener(this.controleur);
  }



  /**
   * Retourne la porte en face.
   *
   * @return la porte en face
   */
  public Porte getPorte()
  {
    return this.enFace;
  }



  /**
   * Met à jour la pièce.
   *
   * @param p le pièce
   */
  public void setPiece(Piece p)
  {
    Passage ps = p.getPassage(this.orientation);

    this.enFace.setPassage(ps);

    if (this.orientation.ordinal() == 0)
    {
      this.aGauche.setPassage(p.getPassage(Direction.values()
                              [Direction.values().length-1]));
    }
    else
    {
      this.aGauche.setPassage(p.getPassage(Direction.values()
                              [this.orientation.ordinal()-1]));
    }

    this.aDroite.setPassage(p.getPassage(Direction.values()
                            [(this.orientation.ordinal()+1)%Direction.values().length]));

    this.controleur.setPassage(ps);
  }
}
