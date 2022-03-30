package fr.iutfbleau.menu.partie;

import fr.iutfbleau.fenetre.Titre;
import fr.iutfbleau.fenetre.CreerPartie;

import fr.iutfbleau.boutons.BoutonMenu;

import fr.iutfbleau.menu.Menu;
import fr.iutfbleau.menu.Retour;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * La classe <code>PartieVue</code> est la vue du menu d'une nouvelle partie.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class PartieVide extends JLabel
{

  /**
   * Le nom.
   */
  public static final String NOM = "Vide";


  /**
   * Le constructeur.
   *
   * @param menu le menu
   */
  public PartieVide(Menu menu)
  {
    super(new ImageIcon(Thread.currentThread().getContextClassLoader()
                        .getResource("img/fonds/menu.jpg")));

    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    Titre titre = new Titre("Nouvelle partie");

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.PAGE_START;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(15, 15, 100, 15);
    this.add(titre, gbc);

    JTextField champ  = new JTextField();

    champ.addActionListener(new CreerPartie(menu.getFenetre(), menu.getJeu()));

    gbc.gridx      = 0;
    gbc.gridy      = 1;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 155;
    gbc.ipady      = 55;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(15, 15, 15, 15);
    this.add(champ, gbc);

    BoutonMenu retour = new BoutonMenu("Retour");

    retour.addActionListener(new Retour(menu));

    gbc.gridx      = 0;
    gbc.gridy      = 2;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 155;
    gbc.ipady      = 55;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(15, 15, 15, 15);
    this.add(retour, gbc);
  }
}
