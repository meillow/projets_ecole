package fr.iutfbleau.jeu.inventaire;

import fr.iutfbleau.jeu.Jeu;
import fr.iutfbleau.jeu.ChangerJeu;

import java.awt.event.ActionEvent;


/**
 * La classe <code>AfficherInventaire</code> est le contrôleur qui ordonne au jeu
 * d'afficher l'inventaire.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class AfficherInventaire extends ChangerJeu
{

  /**
   * Le constructeur.
   *
   * @param j le jeu
   */
  public AfficherInventaire(Jeu j)
  {
    super(j);
  }



  /**
   * Ordonne au jeu d'afficher l'inventaire.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.jeu.afficher(Jeu.INVENTAIRE);
  }
}
