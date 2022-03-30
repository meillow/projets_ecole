package fr.iutfbleau.boutons;

import fr.iutfbleau.boutons.BoutonAbstrait;


/**
 * La classe <code>BoutonMenu</code> est la vue d'un bouton de menu.
 * Un bouton de menu est un bouton avec du texte.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class BoutonMenu extends BoutonAbstrait
{

  /**
   * Le constructeur.
   *
   * @param texte le texte
   */
  public BoutonMenu(String texte)
  {
    super(texte);

    this.setText(texte);
  }
}
