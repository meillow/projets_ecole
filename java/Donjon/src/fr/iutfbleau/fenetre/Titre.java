package fr.iutfbleau.fenetre;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;


/**
 * La classe <code>Titre</code> est la vue d' un titre.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Titre extends JLabel
{

  /**
   * Le constructeur.
   *
   * @param t le titre
   */
  public Titre(String t)
  {
    super(t);

    this.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 80));
    this.setForeground(Color.WHITE);
  }
}
