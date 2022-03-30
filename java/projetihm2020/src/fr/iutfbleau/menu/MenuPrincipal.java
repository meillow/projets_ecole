package fr.iutfbleau.menu;

import fr.iutfbleau.fenetre.ChargerInstructions;
import fr.iutfbleau.fenetre.Quitter;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.MenuAbstrait;

import fr.iutfbleau.menu.jouer.Jouer;


/**
 * La classe <code>MenuPrincipal</code> est la vue du menu principal.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class MenuPrincipal extends MenuAbstrait
{

  /**
   * Le nombre de boutons.
   */
  public static final byte NOMBRE_BOUTONS = 3;


  /**
   * Le constructeur.
   *
   * @param menu le menu
   */
  public MenuPrincipal(Menu menu)
  {
    super("Menu Principal", MenuPrincipal.NOMBRE_BOUTONS);

    this.boutons[0].setText("Jouer");
    this.boutons[0].addActionListener(new Jouer(menu));

    this.boutons[1].setText("Instructions");
    this.boutons[1].addActionListener(new ChargerInstructions(menu.getFenetre()));

    this.boutons[2].setText("Quitter");
    this.boutons[2].addActionListener(new Quitter(menu.getFenetre()));
  }
}
