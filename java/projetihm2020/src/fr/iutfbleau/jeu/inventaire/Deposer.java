package fr.iutfbleau.jeu.inventaire;

import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.jeu.inventaire.Inventaire;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * La classe <code>Deposer</code> est le contrôleur pour déposer un truc.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Deposer implements ActionListener
{

  /**
   * Le truc.
   */
  private Truc truc;

  /**
   * L'inventaire.
   */
  private Inventaire inventaire;


  /**
   * Le constructeur.
   *
   * @param t le truc
   * @param i l'inventaire
   */
  public Deposer(Truc t, Inventaire i)
  {
    this.truc       = t;
    this.inventaire = i;
  }


  /**
   * Dépose le truc. Déposer un truc consiste à le faire passer de l'inventaire du joueur
   * au conteneur de la pièce.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // this.inventaire.getJoueur().removeTruc(this.truc);
    this.inventaire.getJoueur().getPiece().addTruc(this.truc);

    this.inventaire.rafraichir();
  }
}
