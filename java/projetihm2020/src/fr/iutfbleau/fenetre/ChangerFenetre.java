package fr.iutfbleau.fenetre;

import fr.iutfbleau.fenetre.Fenetre;

import java.awt.event.ActionListener;


/**
 * La classe <code>ChangerFenetre</code> représente un gestionnaire d'affichage
 * d'une fenêtre.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public abstract class ChangerFenetre implements ActionListener
{

  /**
   * La fenêtre.
   */
  protected Fenetre fenetre;


  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   */
  public ChangerFenetre(Fenetre f)
  {
    this.fenetre = f;
  }
}
