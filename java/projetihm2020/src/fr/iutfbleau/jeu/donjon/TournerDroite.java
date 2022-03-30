package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.jeu.donjon.Salle;
import fr.iutfbleau.jeu.donjon.Tourner;

import java.awt.event.ActionEvent;


/**
 * La classe <code>TournerDroite</code> est le contrôleur pour tourner à droite.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class TournerDroite extends Tourner
{

  /**
   * Le constructeur.
   *
   * @param s la salle
   */
  public TournerDroite(Salle s)
  {
    super(s);
  }



  /**
   * Ordonne à la salle d'afficher le mur à droite.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.salle.afficherMurDroite();
  }
}
