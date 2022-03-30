package fr.iutfbleau.menu;

import fr.iutfbleau.fenetre.Fenetre;

import fr.iutfbleau.menu.MenuPrincipal;

import fr.iutfbleau.menu.jouer.MenuJouer;

import fr.iutfbleau.menu.partie.MenuPartie;

import fr.iutfbleau.jeu.Jeu;

import javax.swing.JPanel;

import java.awt.CardLayout;


/**
 * La classe <code>Menu</code> répertorie et affiche les différents menus.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Menu extends JPanel
{

  /**
   * Le menu principal.
   */
  public static final String PRINCIPAL = "principal";

  /**
   * Le menu pour jouer.
   */
  public static final String JOUER = "jouer";

  /**
   * Le menu d'une partie.
   */
  public static final String PARTIE = "partie";

  /**
   * Le deck de menus.
   */
  private CardLayout deck;

  /**
   * La fenêtre.
   */
  private Fenetre fenetre;

  /**
   * Le jeu.
   */
  private Jeu jeu;


  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   * @param j le jeu
   */
  public Menu(Fenetre f, Jeu j)
  {
    super();

    this.deck = new CardLayout();
    this.setLayout(this.deck);

    this.fenetre = f;
    this.jeu     = j;

    this.add(new MenuPrincipal(this), Menu.PRINCIPAL);

    this.rafraichir();
  }



  public Fenetre getFenetre()
  {
    return this.fenetre;
  }



  public Jeu getJeu()
  {
    return this.jeu;
  }



  /**
   * Affiche un menu.
   *
   * @param menu le menu
   */
  public void afficher(String menu)
  {
    this.deck.show(this, menu);
  }



  /**
   * Affiche le menu précédent.
   */
  public void afficherMenuPrecedent()
  {
    this.deck.previous(this);
  }



  /**
   * Rafraichit l'affichage des menus.
   */
  public void rafraichir()
  {
    MenuPartie menuPartie = new MenuPartie(this);

    this.add(new MenuJouer(this, menuPartie), Menu.JOUER);
    this.add(menuPartie, Menu.PARTIE);
  }
}
