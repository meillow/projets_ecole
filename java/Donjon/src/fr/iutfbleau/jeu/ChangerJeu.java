package fr.iutfbleau.jeu;

import fr.iutfbleau.jeu.Jeu;

import java.awt.event.ActionListener;


/**
 * La classe <code>ChangerJeu</code> représente un gestionnaire d'affichage du jeu.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public abstract class ChangerJeu implements ActionListener
{

  /**
   * Le jeu.
   */
  protected Jeu jeu;


  /**
   * Le constructeur.
   *
   * @param j le jeu
   */
  public ChangerJeu(Jeu j)
  {
    this.jeu = j;
  }
}
