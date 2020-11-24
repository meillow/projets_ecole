package fr.iutfbleau.jeu.inventaire;

import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;
import fr.iutfbleau.fenetre.Fenetre;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;


/**
 * La classe <code>Ramasser</code> est le contrôleur pour ramasser des trucs.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Ramasser implements ActionListener
{

  /**
   * Le joueur.
   */
  private Joueur joueur;

  /**
   * La fenêtre.
   */
  private Fenetre fenetre;


  /**
   * Le constructeur.
   *
   * @param j le joueur
   * @param f la fenêtre
   */
  public Ramasser(Joueur j, Fenetre f)
  {
    this.joueur  = j;
    this.fenetre = f;
  }



  /**
   * Ramasse tous les trucs. Ramasser un truc consiste à le faire passer du conteneur de
   * la pièce à l'inventaire du joueur.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    int compteur = 0;

    for (Iterator<Truc> i = this.joueur.getPiece().getTrucs(); i.hasNext();)
    {
      Truc t = i.next();
      this.joueur.addTruc(t);
      compteur++;
    }

    // for (Iterator<Truc> i = this.joueur.getTrucs(); i.hasNext();)
    // {
    //   this.joueur.getPiece().removeTruc(i.next());
    // }

    JOptionPane.showMessageDialog(this.fenetre,
      "Vous avez ramassé " + Integer.toString(compteur) + " truc(s).");
  }
}
