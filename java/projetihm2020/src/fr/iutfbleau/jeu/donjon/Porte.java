package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.projetIHM2020FI2.API.EtatPassage;
import fr.iutfbleau.projetIHM2020FI2.API.Passage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;


/**
 * La classe <code>Porte</code> est la vue d'une porte.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Porte extends JLabel
{

  /**
   * La porte en face.
   */
  public static final String EN_FACE = "porte.png";

  /**
   * La porte à droite.
   */
  public static final String GAUCHE = "porte_gauche.png";

  /**
   * La porte à gauche.
   */
  public static final String DROITE = "porte_droite.png";

  /**
   * L'orientation.
   */
  private String orientation;


  /**
   * Le constructeur.
   *
   * @param o l'orientation
   */
  public Porte(String o)
  {
    super();

    this.orientation = o;
  }



  /**
   * Met à jour le passage en face.
   *
   * @param p le passage
   */
  public void setPassage(Passage p)
  {
    if (p == null)
    {
      this.setIcon(null);
    }
    else
    {
      if (p.getEtatPassage() == EtatPassage.OPEN)
      {
        this.setIcon(
          new ImageIcon(Thread.currentThread().getContextClassLoader()
                        .getResource("img/portes/ouvertes/" + this.orientation)));
      }
      else
      {
        this.setIcon(
          new ImageIcon(Thread.currentThread().getContextClassLoader()
                        .getResource("img/portes/fermees/" + this.orientation)));
      }
    }
  }
}
