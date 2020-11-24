package fr.iutfbleau.jeu.carte;

import fr.iutfbleau.jeu.Jeu;
import fr.iutfbleau.jeu.ChangerJeu;

import java.awt.event.ActionEvent;


/**
 * La classe <code>AfficherCarte</code> est le contrôleur qui ordonne au jeu d'afficher
 * la carte
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class AfficherCarte extends ChangerJeu
{

  /**
   * Le constructeur.
   *
   * @param j le jeu
   */
  public AfficherCarte(Jeu j)
  {
    super(j);
  }



  /**
   * Ordonne au jeu d'afficher la carte.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.jeu.afficher(Jeu.CARTE);
  }
}
