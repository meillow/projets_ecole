package fr.iutfbleau.jeu.inventaire;

import fr.iutfbleau.projetIHM2020FI2.API.Truc;

import fr.iutfbleau.jeu.inventaire.Inventaire;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * La classe <code>Utiliser</code> est le contrôleur pour utiliser un truc.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Utiliser implements ActionListener
{

  /**
   * Les choix possibles.
   */
  private static Object[] CHOIX_POSSIBLES = {"Sur vous", "Sur le passage"};

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
  public Utiliser(Truc t, Inventaire i)
  {
    this.truc       = t;
    this.inventaire = i;
  }



  /**
   * Demande au joueur sur qui ou quoi utiliser le truc.
   *
   * @param e l'événement
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object choix = JOptionPane.showInputDialog(this.inventaire.getFenetre(),
                     "Sur qui l'utiliser ?", "Utiliser", JOptionPane.INFORMATION_MESSAGE,
                     new ImageIcon(Thread.currentThread().getContextClassLoader()
                                   .getResource("img/trucs/"
                                   + this.truc.getTypeTruc().toString() + ".png")),
                     Utiliser.CHOIX_POSSIBLES, Utiliser.CHOIX_POSSIBLES[0]);

    if (choix == null)
    {
      return;
    }

    boolean effet;

    if (choix == Utiliser.CHOIX_POSSIBLES[0])
    {
      effet = this.inventaire.getJoueur().agir(this.truc);
    }
    else // if (choix == possibleValues[1])
    {
      if (this.inventaire.getJoueur().getPiece().getPassage(
            this.inventaire.getSalle().getActuelle()) == null)
      {
        effet = false;
      }
      else
      {
        effet = this.inventaire.getJoueur().getPiece().getPassage(
                  this.inventaire.getSalle().getActuelle()).agir(this.truc);
      }
    }

    if (effet)
    {
      this.inventaire.getSalle().setPiece(this.inventaire.getJoueur().getPiece());
      this.inventaire.getJoueur().removeTruc(this.truc);
      this.inventaire.rafraichir();
    }
    else
    {
      /* Cf Pokémon ROSA. */
      JOptionPane.showMessageDialog(this.inventaire.getFenetre(),
        "Si papa était là, il dirait : « Voyons fiston, ce n'est pas le moment ! »");
    }
  }
}
