package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.projetIHM2020FI2.API.Piece;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;

import fr.iutfbleau.jeu.Jeu;

import fr.iutfbleau.jeu.donjon.Salle;
import fr.iutfbleau.jeu.donjon.Actions;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * La classe <code>Donjon</code> est la vue du donjon.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Donjon extends JPanel
{

  /**
   * La salle.
   */
  private Salle salle;


  /**
   * Le constructeur.
   *
   * @param joueur  le joueur
   * @param fenetre la fenêtre
   * @param jeu     le jeu
   */
  public Donjon(Joueur joueur, Fenetre fenetre, Jeu jeu)
  {
    super();

    this.salle = new Salle(joueur, fenetre, this);

    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 0;
    gbc.ipady      = 0;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 1.0;
    gbc.insets     = new Insets(0, 0, 0, 0);
    this.add(salle, gbc);

    gbc.gridx      = 0;
    gbc.gridy      = 1;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 0;
    gbc.ipady      = 0;
    gbc.fill       = GridBagConstraints.HORIZONTAL;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 1.0;
    gbc.insets     = new Insets(0, 0, 0, 0);
    this.add(new Actions(joueur, fenetre, jeu, this.salle), gbc);
  }



  /**
   * Retourne la salle.
   *
   * @return la salle
   */
  public Salle getSalle()
  {
    return this.salle;
  }



  /**
   * Affiche une pièce.
   *
   * @param p la pièce
   */
  public void afficher(Piece p)
  {
    this.salle.setPiece(p);
  }
}
