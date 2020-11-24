package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.jeu.donjon.Salle;
import fr.iutfbleau.jeu.donjon.Tourner;

import java.awt.event.ActionEvent;


/**
 * La classe <code>TournerGauche</code> est le contrôleur pour tourner à gauche.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class TournerGauche extends Tourner
{

  /**
   * Le constructeur.
   *
   * @param s la salle
   */
  public TournerGauche(Salle s)
  {
    super(s);
  }



  /**
   * Ordonne à la salle d'afficher le mur à gauche.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.salle.afficherMurGauche();
  }
}
