package fr.iutfbleau.fenetre;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.ChangerFenetre;

import java.awt.event.ActionEvent;


/**
 * La classe <code>ChargerInstructions</code> est le contrôleur qui ordonne à une fenêtre
 * d'afficher le menu.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class ChargerMenu extends ChangerFenetre
{

  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   */
  public ChargerMenu(Fenetre f)
  {
    super(f);
  }



  /**
   * Ordonne à la fenêtre d'afficher les instructions.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.fenetre.afficher(Fenetre.MENU);
  }
}
