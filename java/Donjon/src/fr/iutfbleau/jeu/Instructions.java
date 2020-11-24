package fr.iutfbleau.jeu;

import fr.iutfbleau.fenetre.Fenetre;
import fr.iutfbleau.fenetre.Titre;
import fr.iutfbleau.fenetre.ChargerMenu;

import fr.iutfbleau.boutons.BoutonJeu;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;


/**
 * La classe <code>Jeu</code> est la vue des instructions.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Instructions extends JPanel
{

  /**
   * Le constructeur.
   *
   * @param fenetre la fenêtre
   */
  public Instructions(Fenetre fenetre)
  {
    super();

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.BLACK);

    GridBagConstraints gbc = new GridBagConstraints();

    BoutonJeu retour = new BoutonJeu("Retour",
      Thread.currentThread().getContextClassLoader()
        .getResource("img/boutons/croix.png"));

    retour.addActionListener(new ChargerMenu(fenetre));

    gbc.gridx      = 0;
    gbc.gridy      = 0;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.NORTHWEST;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(5, 5, 5, 5);
    this.add(retour, gbc);

    Titre titre = new Titre("Instructions");

    titre.setHorizontalAlignment(Titre.CENTER);

    gbc.gridx      = 1;
    gbc.gridy      = 0;
    gbc.gridwidth  = 2;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.HORIZONTAL;
    gbc.anchor     = GridBagConstraints.NORTH;
    gbc.weightx    = 1.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(5, 5, 5, 5);
    this.add(titre, gbc);

    this.ajouter("Tourner à gauche", "gauche", 1);
    this.ajouter("Tourner à droite", "droite", 2);
    this.ajouter("Inventaire", "sac", 3);
    this.ajouter("Carte", "carte", 4);
    this.ajouter("Prendre/Agir", "agir", 5);
  }



  /**
   * Ajoute une instruction.
   *
   * @param t     le texte
   * @param image l'image
   * @param y     la coordonnée en y
   */
  private void ajouter(String t, String image, int y)
  {
    GridBagConstraints gbc = new GridBagConstraints();

    JLabel texte = new JLabel(t);

    texte.setHorizontalAlignment(JLabel.CENTER);
    texte.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
    texte.setForeground(Color.WHITE);

    gbc.gridx      = 0;
    gbc.gridy      = y;
    gbc.gridwidth  = 2;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.BOTH;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 1.0;
    gbc.weighty    = 1.0;
    gbc.insets     = new Insets(0, 0, 0, 0);
    this.add(texte, gbc);

    gbc.gridx      = 2;
    gbc.gridy      = y;
    gbc.gridwidth  = 1;
    gbc.gridheight = 1;
    gbc.fill       = GridBagConstraints.NONE;
    gbc.anchor     = GridBagConstraints.CENTER;
    gbc.weightx    = 0.0;
    gbc.weighty    = 0.0;
    gbc.insets     = new Insets(0, 0, 0, 0);
    this.add(new BoutonJeu(t,
      Thread.currentThread().getContextClassLoader()
        .getResource("img/boutons/" + image + ".png")), gbc);
  }
}
