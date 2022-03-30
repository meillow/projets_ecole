package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.jeu.Jeu;
import fr.iutfbleau.jeu.ChangerJeu;

import java.awt.event.ActionEvent;


/**
 * La classe <code>AfficherDonjon</code> est le contrôleur qui ordonne au jeu d'afficher
 * le donjon.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class AfficherDonjon extends ChangerJeu
{

  /**
   * Le constructeur.
   *
   * @param j le jeu
   */
  public AfficherDonjon(Jeu j)
  {
    super(j);
  }



  /**
   * Ordonne au jeu d'afficher le donjon.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.jeu.afficher(Jeu.DONJON);
  }
}
