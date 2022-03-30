package fr.iutfbleau.menu;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.ChangerMenu;

import java.awt.event.ActionEvent;


/**
 * La classe <code>Retour</code> est le contrôleur qui ordonne à un menu d'afficher le menu précédent.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Retour extends ChangerMenu
{

  /**
   * Le constructeur.
   *
   * @param m le menu
   */
  public Retour(Menu m)
  {
    super(m);
  }



  /**
   * Ordonne au menu d'afficher le menu précédent.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.menu.afficher(Menu.PRINCIPAL);
  }
}
