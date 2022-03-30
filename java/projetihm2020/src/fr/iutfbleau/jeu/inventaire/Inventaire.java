package fr.iutfbleau.jeu.inventaire;

import fr.iutfbleau.projetIHM2020FI2.API.Truc;
import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;

import fr.iutfbleau.jeu.Jeu;
import fr.iutfbleau.jeu.EnTete;

import fr.iutfbleau.jeu.donjon.Salle;

import fr.iutfbleau.jeu.inventaire.Poche;

import javax.swing.JPanel;

import java.awt.GridLayout;

import java.util.Iterator;


/**
 * La classe <code>Inventaire</code> est la vue de l'inventaire.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Inventaire extends JPanel
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
   * Le jeu.
   */
  private Jeu jeu;

  /**
   * La salle.
   */
  private Salle salle;


  /**
   * Le constructeur.
   *
   * @param joueur le joueur
   * @param f      la fenêtre
   * @param jeu    le jeu
   * @param s      la salle
   */
  public Inventaire(Joueur joueur, Fenetre f, Jeu jeu, Salle s)
  {
    super();

    this.joueur  = joueur;
    this.fenetre = f;
    this.jeu     = jeu;
    this.salle   = s;

    this.rafraichir();
  }



  /**
   * Retourne le joueur.
   *
   * @return le joueur
   */
  public Joueur getJoueur()
  {
    return this.joueur;
  }



  /**
   * Retourne la fenêtre.
   *
   * @return la fenêtre
   */
  public Fenetre getFenetre()
  {
    return this.fenetre;
  }



  /**
   * Retourne le jeu.
   *
   * @return le jeu
   */
  public Jeu getJeu()
  {
    return this.jeu;
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
   * Rafraichit l'affichage de l'inventaire.
   */
  public void rafraichir()
  {
    this.removeAll();

    this.setLayout(new GridLayout(this.joueur.combienTrucs() + 1, 1));

    this.add(new EnTete(this.jeu, "Inventaire"));

    for (Iterator<Truc> i = this.joueur.getTrucs(); i.hasNext();)
    {
      this.add(new Poche(i.next(), this));
    }

    this.revalidate();
    this.repaint();
  }
}
