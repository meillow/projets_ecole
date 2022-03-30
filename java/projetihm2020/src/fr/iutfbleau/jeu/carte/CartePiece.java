package fr.iutfbleau.jeu.carte;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Color;



/**
 * La classe <code>CartePiece</code> est la vue d'une pièce sur la carte.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class CartePiece extends JComponent
{

  /**
   * La pièce.
   */
  Piece piece;

  /**
   * Le joueur.
   */
  Joueur joueur;


  /**
   * Le constructeur.
   *
   * @param p la pièce
   * @param j le joueur
   */
  public CartePiece(Piece p, Joueur j)
  {
    super();

    this.piece  = p;
    this.joueur = j;
  }



  /**
   * Dessine le composant.
   *
   * @param g le pinceau
   */
  @Override
  protected void paintComponent(Graphics g)
  {
    Graphics pinceau = g.create();

    /* Ne fonctionne qu'avec les modèles non persistants. */
    if (this.joueur.getPiece() == this.piece)
    {
      pinceau.setColor(Color.BLUE);
    }
    else
    {
      pinceau.setColor(Color.GRAY);
    }

    pinceau.fillRect(0, 0, this.getWidth(), this.getHeight());

    pinceau.setColor(Color.WHITE);

    for (int i = 0; i < 10; i++)
    {
      pinceau.drawRect(i, i, this.getWidth() - 2 * i, this.getHeight() - 2 * i);
    }

    pinceau.setColor(Color.GRAY);

    if (this.piece.getPassage(Direction.NORD) != null)
    {
      pinceau.fillRect(this.getWidth() / 3, 0, this.getWidth() / 3, 10);
    }


    if (this.piece.getPassage(Direction.EST) != null)
    {
      pinceau.fillRect(this.getWidth() - 10, this.getHeight() / 3,
                       10, this.getHeight() / 3);
    }


    if (this.piece.getPassage(Direction.SUD) != null)
    {
      pinceau.fillRect(this.getWidth() / 3, this.getHeight() - 10,
                       this.getWidth() / 3, 10);
    }

    if (this.piece.getPassage(Direction.OUEST) != null)
    {
      pinceau.fillRect(0, this.getHeight() / 3, 10, this.getHeight() / 3);
    }
  }
}
