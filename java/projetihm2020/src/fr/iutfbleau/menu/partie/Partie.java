package fr.iutfbleau.menu.partie;

import fr.iutfbleau.fenetre.ChargerPartie;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.MenuAbstrait;
import fr.iutfbleau.menu.Retour;

import fr.iutfbleau.menu.partie.SupprimerPartie;


/**
 * La classe <code>Partie</code> est la vue du menu d'une partie.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Partie extends MenuAbstrait
{

  /**
   * Le nombre de parties disponibles.
   */
  public static final byte DISPONIBLES = 3;

  /**
   * Le nombre de boutons pour une partie existante.
   */
  public static final byte NOMBRE_BOUTONS = 3;


  /**
   * Le constructeur.
   *
   * @param memu le menu
   * @param nom  le nom
   */
  public Partie(Menu menu, String nom)
  {
    super(nom, Partie.NOMBRE_BOUTONS);

    this.boutons[0].setText("Charger la partie");
    this.boutons[0].addActionListener(new ChargerPartie(menu.getFenetre(),
                                                        menu.getJeu(), nom));

    this.boutons[1].setText("Supprimer la partie");
    this.boutons[1].addActionListener(new SupprimerPartie(menu, nom));

    this.boutons[2].setText("Retour");
    this.boutons[2].addActionListener(new Retour(menu));
  }
}
