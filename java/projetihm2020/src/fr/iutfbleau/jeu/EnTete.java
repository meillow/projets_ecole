package fr.iutfbleau.jeu;

import fr.iutfbleau.fenetre.Titre;

import fr.iutfbleau.boutons.BoutonJeu;

import fr.iutfbleau.jeu.Jeu;

import fr.iutfbleau.jeu.donjon.AfficherDonjon;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;


/**
 * La classe <code>EnTete</code> est la vue d'une en-tête.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class EnTete extends JPanel
{

  /**
   * Le constructeur.
   *
   * @param jeu le jeu
   * @param t   le titre
   */
  public EnTete(Jeu jeu, String t)
  {
    super();

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.BLACK);

    GridBagConstraints gbc = new GridBagConstraints();

    BoutonJeu retour = new BoutonJeu("Retour",
      Thread.currentThread().getContextClassLoader()
        .getResource("img/boutons/croix.png"));

    retour.addActionListener(new AfficherDonjon(jeu));

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.EAST;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(5, 5, 5, 5);
    this.add(retour, gbc);

    Titre titre = new Titre(t);

    titre.setHorizontalAlignment(Titre.CENTER);

    gbc.gridx      = 1;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.BOTH;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(5, 5, 5, 5);
    this.add(titre, gbc);
  }
}
