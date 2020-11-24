package fr.iutfbleau.menu.jouer;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.MenuAbstrait;
import fr.iutfbleau.menu.Retour;

import fr.iutfbleau.menu.partie.MenuPartie;
import fr.iutfbleau.menu.partie.Partie;
import fr.iutfbleau.menu.partie.ChoisirPartie;


/**
 * La classe <code>MenuJouer</code> est la vue du menu pour jouer.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class MenuJouer extends MenuAbstrait
{

  /**
   * Le nombre de boutons.
   */
  public static final byte NOMBRE_BOUTONS = 4;


  /**
   * Le constructeur.
   *
   * @param menu       le menu
   * @param menuPartie le menu des parties
   */
  public MenuJouer(Menu menu, MenuPartie menuPartie)
  {
    super("Menu Jouer", MenuJouer.NOMBRE_BOUTONS);

    ChoisirPartie controleur = new ChoisirPartie(menu, menuPartie);

    for (byte i = 0; i < Partie.DISPONIBLES; i++)
    {
      this.boutons[i].setText(menuPartie.getPartie(i));
      this.boutons[i].addActionListener(controleur);
    }

    this.boutons[MenuJouer.NOMBRE_BOUTONS-1].setText("Retour");
    this.boutons[MenuJouer.NOMBRE_BOUTONS-1].addActionListener(new Retour(menu));
  }
}
