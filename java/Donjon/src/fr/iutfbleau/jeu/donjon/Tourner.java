package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.jeu.donjon.Salle;

import java.awt.event.ActionListener;


/**
 * La classe <code>Tourner</code> représente un contrôleur pour tourner.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public abstract class Tourner implements ActionListener
{

  /**
   * La salle.
   */
  protected Salle salle;


  /**
   * Le constructeur.
   *
   * @param s la salle
   */
  public Tourner(Salle s)
  {
    this.salle = s;
  }
}
