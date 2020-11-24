package fr.iutfbleau.menu.jouer;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.ChangerMenu;

import java.awt.event.ActionEvent;


/**
 * La classe <code>Jouer</code> est le contrôleur qui ordonne à un menu d'afficher
 * le menu pour jouer.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Jouer extends ChangerMenu
{

  /**
   * Le constructeur.
   *
   * @param m le menu
   */
  public Jouer(Menu m)
  {
    super(m);
  }



  /**
   * Ordonne au menu d'afficher le menu pour jouer.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.menu.afficher(Menu.JOUER);
  }
}
