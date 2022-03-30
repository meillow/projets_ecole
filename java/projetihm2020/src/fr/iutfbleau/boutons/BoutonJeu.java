package fr.iutfbleau.boutons;

import fr.iutfbleau.boutons.BoutonAbstrait;

import javax.swing.ImageIcon;

import java.net.URL;


/**
 * La classe <code>BoutonJeu</code> est la vue d'un bouton de jeu.
 * Un bouton de jeu est un bouton avec une image.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class BoutonJeu extends BoutonAbstrait
{

  /**
   * Le constructeur.
   *
   * @param texte le texte
   * @param image l'image
   */
  public BoutonJeu (String texte, String image)
  {
    super(texte);

    this.setIcon(new ImageIcon(image));
  }


  /**
   * Le constructeur.
   *
   * @param texte le texte
   * @param image l'image
   */
  public BoutonJeu (String texte, URL image)
  {
    super(texte);

    this.setIcon(new ImageIcon(image));
  }
}
