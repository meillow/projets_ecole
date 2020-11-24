package fr.iutfbleau.fenetre;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.ChangerFenetre;

import fr.iutfbleau.jeu.Jeu;

import fr.iutfbleau.MP.JoueurP;

import java.awt.event.ActionEvent;


/**
 * La classe <code>ChargerPartie</code> est le contrôleur qui charge une partie et
 * ordonne à une fenêtre de l'afficher.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class ChargerPartie extends ChangerFenetre
{

  /**
   * Le jeu.
   */
  private Jeu jeu;

  /**
   * La partie.
   */
  private String partie;


  /**
   * Le constructeur.
   *
   * @param f la fenêtre
   * @param j le jeu
   * @param p la partie
   */
  public ChargerPartie(Fenetre f, Jeu j, String p)
  {
    super(f);

    this.jeu    = j;
    this.partie = p;
  }



  /**
   * Charge une partie et ordonne à la fenêtre de l'afficher.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.jeu.setJoueur(new JoueurP(this.partie, JoueurP.EXISTANT));
    this.fenetre.afficher(Fenetre.JEU);
  }
}
