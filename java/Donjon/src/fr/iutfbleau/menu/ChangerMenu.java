package fr.iutfbleau.menu;

import fr.iutfbleau.menu.Menu;

import java.awt.event.ActionListener;


/**
 * La classe <code>ChangerMenu</code> représente un gestionnaire d'affichage de menus.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public abstract class ChangerMenu implements ActionListener
{

  /**
   * Le menu.
   */
  protected Menu menu;


  /**
   * Le constructeur.
   *
   * @param m le menu
   */
  public ChangerMenu(Menu m)
  {
    this.menu = m;
  }
}
