package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.projetIHM2020FI2.API.EtatPassage;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.Chargement;

import fr.iutfbleau.jeu.donjon.Donjon;

import javax.swing.JOptionPane;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


/**
 * La classe <code>ChangerPiece</code> est le contrôleur pour changer de pièce.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class ChangerPiece implements MouseListener
{

  /**
   * Le passage.
   */
  private Passage passage;

  /**
   * Le joueur.
   */
  private Joueur joueur;

  /**
   * La fenêtre.
   */
  private Fenetre fenetre;

  /**
   * Le donjon.
   */
  private Donjon donjon;


  /**
   * Le constructeur.
   *
   * @param j le joueur
   * @param f la fenêtre
   * @param d le donjon
   */
  public ChangerPiece(Joueur j, Fenetre f, Donjon d)
  {
    this.passage = null;
    this.joueur  = j;
    this.fenetre = f;
    this.donjon  = d;
  }



  /**
   * Met à jour le passage.
   *
   * @param p le passage
   */
  public void setPassage(Passage p)
  {
    this.passage = p;
  }



  /**
   * Ordonne au donjon d'afficher la nouvelle pièce.
   *
   * @param e l'événement
   */
  @Override
  public void mouseClicked(MouseEvent e)
  {
    if (this.passage == null)
    {
      return;
    }

    if (this.passage.getEtatPassage() == EtatPassage.OPEN)
    {
      Chargement chargement = new Chargement(this.fenetre, "Chargement de la pièce...");
      chargement.setVisible(true);

      Piece p = this.passage.getAutrePiece(this.joueur.getPiece());

      this.joueur.setPiece(p);
      this.donjon.afficher(p);

      chargement.dispose();
    }
    else if (this.passage.agir(null))
    {
      this.donjon.getSalle().getMur(this.donjon.getSalle().getActuelle())
        .getPorte().setPassage(this.passage);
    }
    else
    {
      JOptionPane.showMessageDialog(this.fenetre,
        "Cette porte est vérrouillée. Une clef peut sûrement aider à l'ouvrir...");
    }
  }



  /**
   * Inutile ici.
   *
   * @param e l'événement
   */
  @Override
  public void mouseEntered(MouseEvent e) {}



  /**
   * Inutile ici.
   *
   * @param e l'événement
   */
  @Override
  public void mouseExited(MouseEvent e) {}



  /**
   * Inutile ici.
   *
   * @param e l'événement
   */
  @Override
  public void mousePressed(MouseEvent e) {}



  /**
   * Inutile ici.
   *
   * @param e l'événement
   */
  @Override
  public void mouseReleased(MouseEvent e) {}
}
