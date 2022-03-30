package fr.iutfbleau.fenetre;

import fr.iutfbleau.fenetre.Fenetre;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.BorderLayout;


/**
 * La classe <code>Chargement</code> est la vue d'une fenêtre de chargement.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Chargement extends JDialog
{

  /**
   * L'information.
   */
  private JLabel information;


  /**
   * Le constructeur.
   *
   * @param fenetre la fenêtre
   * @param texte   l'information
   */
  public Chargement(Fenetre fenetre, String texte)
  {
    super(fenetre, "Chargement...");

    this.setLayout(new BorderLayout());

    this.information = new JLabel(texte);

    this.information.setHorizontalAlignment(JLabel.CENTER);
    this.information.setVerticalAlignment(JLabel.BOTTOM);

    this.add(this.information, BorderLayout.SOUTH);

    this.pack();
    /* Position centrée par rapport à la fenêtre. */
    this.setLocation(((fenetre.getWidth() / 2) - (this.getHeight() / 2)),
                     ((fenetre.getHeight() / 2) - (this.getWidth() / 2)));
  }



  /**
   * Met à jour l'information.
   *
   * @param texte l'information
   */
  public void setTexte(String texte)
  {
    this.information.setText(texte);
  }
}
