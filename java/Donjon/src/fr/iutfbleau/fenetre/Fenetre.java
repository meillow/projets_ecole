package fr.iutfbleau.fenetre;

import fr.iutfbleau.menu.Menu;

import fr.iutfbleau.jeu.Jeu;
import fr.iutfbleau.jeu.Instructions;

import javax.swing.JFrame;

import java.awt.CardLayout;


/**
 * La classe <code>Fenetre</code> répertorie et affiche les différentes fenêtres.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Fenetre extends JFrame
{

  /**
   * La fenêtre des menus.
   */
  public static final String MENU = "menu";

  /**
   * La fenêtre du jeu.
   */
  public static final String JEU = "jeu";

  /**
   * La fenêtre des instructions.
   */
  public static final String INSTRUCTIONS = "instructions";

  /**
   * Le deck de fenêtres.
   */
  private CardLayout deck;


  /**
   * Le constructeur.
   *
   * @param titre le titre
   */
  public Fenetre(String titre)
  {
    super(titre);

    this.deck = new CardLayout();
    this.setLayout(this.deck);

    this.setSize(1600, 936);
    // this.setMinimumSize(this.getSize());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Jeu jeu = new Jeu(this);

    this.add(new Menu(this, jeu), Fenetre.MENU);
    this.add(jeu, Fenetre.JEU);
    this.add(new Instructions(this), Fenetre.INSTRUCTIONS);
  }



  /**
   * Affiche une fenêtre.
   *
   * @param fenetre la fenêtre
   */
  public void afficher(String fenetre)
  {
    this.deck.show(this.getContentPane(), fenetre);
  }
}
