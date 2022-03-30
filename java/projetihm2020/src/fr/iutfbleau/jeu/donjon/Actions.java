package fr.iutfbleau.jeu.donjon;

import fr.iutfbleau.projetIHM2020FI2.API.Joueur;

import fr.iutfbleau.fenetre.Fenetre;

import fr.iutfbleau.boutons.BoutonJeu;

import fr.iutfbleau.jeu.Jeu;

import fr.iutfbleau.jeu.donjon.Salle;
import fr.iutfbleau.jeu.donjon.TournerGauche;
import fr.iutfbleau.jeu.donjon.TournerDroite;

import fr.iutfbleau.jeu.inventaire.Ramasser;
import fr.iutfbleau.jeu.inventaire.AfficherInventaire;

import fr.iutfbleau.jeu.carte.AfficherCarte;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.border.CompoundBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;


/**
 * La classe <code>Actions</code> est la vue du menu d'actions.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Actions extends JPanel
{

  /**
   * Le constructeur.
   *
   * @param joueur  le joueur
   * @param fenetre la fenêtre
   * @param jeu     le jeu
   * @param salle   la salle
   */
  public Actions(Joueur joueur, Fenetre fenetre, Jeu jeu, Salle salle)
  {
    super();

    this.setBorder(new CompoundBorder(
      BorderFactory.createMatteBorder(2, 0, 0, 0, Color.gray),
      BorderFactory.createEmptyBorder(20, 0, 40,0)));
    this.setBackground(Color.black);

    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    BoutonJeu boutonGauche = new BoutonJeu("Tourner à gauche",
      Thread.currentThread().getContextClassLoader()
        .getResource("img/boutons/gauche.png"));

    boutonGauche.addActionListener(new TournerGauche(salle));

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 10;
    gbc.ipady      = 10;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(0, 5, 0, 40);
    this.add(boutonGauche, gbc);

    BoutonJeu inventaire = new BoutonJeu("Inventaire",
      Thread.currentThread().getContextClassLoader().getResource("img/boutons/sac.png"));

    inventaire.addActionListener(new AfficherInventaire(jeu));

    gbc.gridx      = 1;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 40;
    gbc.ipady      = 10;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets = new Insets(0, 0, 0, 40);
    this.add(inventaire, gbc);

    BoutonJeu carte = new BoutonJeu("Carte",
      Thread.currentThread().getContextClassLoader().getResource("img/boutons/carte.png"));

    carte.addActionListener(new AfficherCarte(jeu));

    gbc.gridx      = 2;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 40;
    gbc.ipady      = 10;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets = new Insets(0, 0, 0, 40);
    this.add(carte,gbc);

    BoutonJeu prendre = new BoutonJeu("Prendre",
      Thread.currentThread().getContextClassLoader().getResource("img/boutons/agir.png"));

    prendre.addActionListener(new Ramasser(joueur, fenetre));

    gbc.gridx      = 3;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 40;
    gbc.ipady      = 10;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets = new Insets(0, 0, 0, 40);
    this.add(prendre, gbc);

    BoutonJeu boutonDroite = new BoutonJeu("Tourner à droite",
      Thread.currentThread().getContextClassLoader().getResource("img/boutons/droite.png"));

    boutonDroite.addActionListener(new TournerDroite(salle));

    gbc.gridx      = 4;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.ipadx      = 10;
    gbc.ipady      = 10;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(0, 0, 0, 5);
    this.add(boutonDroite, gbc);
  }
}
