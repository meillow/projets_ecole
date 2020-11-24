package fr.iutfbleau.menu;

import fr.iutfbleau.fenetre.Titre;

import fr.iutfbleau.boutons.BoutonMenu;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * La classe <code>MenuAbstrait</code> représente un menu.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public abstract class MenuAbstrait extends JLabel
{

  /**
   * Les boutons.
   */
  protected BoutonMenu[] boutons;

  /**
   * Le titre.
   */
  protected Titre titre;


  /**
   * Le constructeur.
   *
   * @param t             le titre
   * @param nombreBoutons le nombre de boutons
   */
  public MenuAbstrait(String t, byte nombreBoutons)
  {
    super(new ImageIcon(Thread.currentThread().getContextClassLoader()
                        .getResource("img/fonds/menu.jpg")));

    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    this.titre = new Titre(t);

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.PAGE_START;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(15, 15, 100, 15);
    this.add(this.titre, gbc);

    this.boutons = new BoutonMenu[nombreBoutons];

    for (byte i = 0; i < nombreBoutons; i++)
    {
      this.boutons[i] = new BoutonMenu("Bouton " + i);

      gbc.gridx      = 0;
      gbc.gridy      = (int) i + 1;
      gbc.gridwidth  = 1;
      gbc.gridheight = 1;
      gbc.ipadx      = 155;
      gbc.ipady      = 55;
      gbc.fill       = GridBagConstraints.NONE;
      gbc.anchor     = GridBagConstraints.CENTER;
      gbc.weightx    = 0.0;
      gbc.weighty    = 0.0;
      gbc.insets     = new Insets(15, 15, 15, 15);
      this.add(this.boutons[i], gbc);
    }
  }
}
