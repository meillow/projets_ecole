package fr.iutfbleau.jeu.carte;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.jeu.Jeu;
import fr.iutfbleau.jeu.EnTete;

import fr.iutfbleau.jeu.carte.CartePiece;

import fr.iutfbleau.MP.PieceP;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

import java.util.List;
import java.util.ArrayList;


/**
 * La classe <code>Carte</code> est la vue de la carte.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Carte extends JPanel
{

  /**
   * La coordonnée en x de la pièce du joueur.
   */
  private int xPiece;

  /**
   * La coordonnée en y de la pièce du joueur.
   */
  private int yPiece;

  /**
   * Le joueur.
   */
  private Joueur joueur;

  /**
   * La pièce du joueur.
   */
  private Piece pieceJoueur;

  /**
   * Le jeu.
   */
  private Jeu jeu;


  /**
   * Le constructeur.
   *
   * @param joueur le joueur
   * @param jeu    le jeu
   */
  public Carte(Joueur joueur, Jeu jeu)
  {
    super();

    this.xPiece = 0;
    this.yPiece = 0;
    this.joueur = joueur;
    this.jeu    = jeu;

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.BLACK);
    this.rafraichir();
  }



  /**
   * Met à jour la position de la pièce du joueur sur la carte.
   *
   * @param visitees les pièces visitées
   * @param piece    la pièce
   * @param x        la coordonnée en x
   * @param y        la coordonnée en y
   */
  private void setPosition(List<Piece> visitees, Piece piece, int x, int y)
  {
    if (!this.joueur.isVisited(piece) && (piece != this.pieceJoueur))
    {
      return;
    }

    // if (visitees.contains(piece))
    // {
    //   return;
    // }
    // else
    // {
    //   visitees.add(piece);
    // }

    for (Piece pp: visitees)
    {
      if (((PieceP) pp).egale(piece))
      {
        return;
      }
    }

    visitees.add(piece);

    if (x > this.xPiece)
    {
      this.xPiece = x;
    }

    if (y > this.yPiece)
    {
      this.yPiece = y;
    }

    Passage p = piece.getPassage(Direction.NORD);

    if (p != null)
    {
      this.setPosition(visitees, p.getAutrePiece(piece), x, y + 1);
    }

    p = piece.getPassage(Direction.EST);

    if (p != null)
    {
      this.setPosition(visitees, p.getAutrePiece(piece), x - 1, y);
    }

    p = piece.getPassage(Direction.SUD);

    if (p != null)
    {
      this.setPosition(visitees, p.getAutrePiece(piece), x, y - 1);
    }

    p = piece.getPassage(Direction.OUEST);

    if (p != null)
    {
      this.setPosition(visitees, p.getAutrePiece(piece), x + 1, y);
    }
  }



  /**
   * Affiche chaque pièce sur la carte.
   *
   * @param visitees les pièces visitées
   * @param piece    la pièce
   * @param x        la coordonnée en x
   * @param y        la coordonnée en y
   */
  private void afficher(List<Piece> visitees, Piece piece, int x, int y)
  {
    // if (visitees.contains(piece))
    // {
    //   visitees.remove(piece);
    // }
    // else
    // {
    //   return;
    // }

    boolean suppression = false;

    for (Piece pp: visitees)
    {
      if (((PieceP) pp).egale(piece))
      {
        visitees.remove(pp);
        suppression = true;
        break; /* Pardon, mais je n'ai pas vraiment d'autre choix. */
      }
    }

    if (!suppression)
    {
      return;
    }

    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx      = x;
    gbc.gridy      = y;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.BOTH;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 1.0;
    gbc.insets     = new Insets(0, 0, 0, 0);
    this.add(new CartePiece(piece, this.joueur), gbc);

    Passage p = piece.getPassage(Direction.NORD);

    if (p != null)
    {
      this.afficher(visitees, p.getAutrePiece(piece), x, y - 1);
    }

    p = piece.getPassage(Direction.EST);

    if (p != null)
    {
      this.afficher(visitees, p.getAutrePiece(piece), x + 1, y);
    }

    p = piece.getPassage(Direction.SUD);

    if (p != null)
    {
      this.afficher(visitees, p.getAutrePiece(piece), x, y + 1);
    }

    p = piece.getPassage(Direction.OUEST);

    if (p != null)
    {
      this.afficher(visitees, p.getAutrePiece(piece), x - 1, y);
    }
  }



  /**
   * Rafraichit l'affichage de la carte.
   */
  public void rafraichir()
  {
    this.removeAll();

    this.pieceJoueur = this.joueur.getPiece();

    List<Piece> visitees = new ArrayList<>();

    this.setPosition(visitees, this.pieceJoueur, 0, 0);

    this.yPiece++;

    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 0;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.HORIZONTAL;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(0, 0, 0, 0);
    this.add(new EnTete(this.jeu, "Carte"), gbc);

    this.afficher(visitees, this.pieceJoueur, this.xPiece, this.yPiece);
  }
}
