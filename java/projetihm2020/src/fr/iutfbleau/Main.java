package fr.iutfbleau;

import fr.iutfbleau.fenetre.Fenetre;


/**
 * La classe <code>Main</code> est le contrôleur qui ouvre la fenêtre.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Main
{

  /**
   * La fonction principale.
   *
   * @param args les arguments passés en ligne de commande
   */
  public static void main(String[] args)
  {
    Fenetre fenetre = new Fenetre("Donjon");
    fenetre.setVisible(true);
  }
}
