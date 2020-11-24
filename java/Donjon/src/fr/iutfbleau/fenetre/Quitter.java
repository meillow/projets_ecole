package fr.iutfbleau.fenetre;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.ChangerFenetre;

import java.awt.event.ActionEvent;


/**
 * La classe <code>Quitter</code> est le contrôleur qui fait quitter le jeu.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Quitter extends ChangerFenetre
{

  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   */
  public Quitter(Fenetre f)
  {
    super(f);
  }



  /**
   * Fait quitter le jeu.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.fenetre.dispose();
    System.exit(0);
  }
}
