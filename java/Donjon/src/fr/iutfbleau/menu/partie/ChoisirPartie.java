package fr.iutfbleau.menu.partie;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.ChangerMenu;

import fr.iutfbleau.menu.partie.MenuPartie;

import java.awt.event.ActionEvent;


/**
 * La classe <code>ChoisirPartie</code> est le contrôleur qui ordonne à un menu
 * d'afficher le menu de la partie.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class ChoisirPartie extends ChangerMenu
{

  /**
   * Le menu des parties.
   */
  private MenuPartie menuPartie;


  /**
   * Le constructeur.
   *
   * @param m  le menu
   * @param mp le menu des parties
   */
  public ChoisirPartie(Menu m, MenuPartie mp)
  {
    super(m);

    this.menuPartie = mp;
  }



  /**
   * Ordonne au menu d'afficher le menu de la partie.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String partie = e.getActionCommand();

    this.menu.afficher(Menu.PARTIE);
    this.menuPartie.afficher(partie);
  }
}
