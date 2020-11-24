package fr.iutfbleau.boutons;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Color;


/**
 * La classe <code>BoutonAbstrait</code> représente un bouton.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public abstract class BoutonAbstrait extends JButton
{

  /**
   * Le constructeur.
   *
   * @param survol le texte au survol
   */
  public BoutonAbstrait(String survol)
  {
    super();

    this.setToolTipText(survol);

    this.setBorder(new BevelBorder(BevelBorder.RAISED));
    this.setBorder(new BevelBorder(BevelBorder.LOWERED));

    this.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));

    this.setBackground(new Color(64,64,64));
    this.setForeground(Color.WHITE);
  }



  /**
   * Met à jour le texte d'un bouton. Le texte affiché au survol est aussi modifié.
   *
   * @param t le texte
   */
  @Override
  public void setText(String t)
  {
    super.setText(t);
    this.setToolTipText(t);
  }
}
