package fr.iutfbleau.jeu.inventaire;

import fr.iutfbleau.projetIHM2020FI2.API.Truc;

import fr.iutfbleau.boutons.BoutonJeu;

import fr.iutfbleau.jeu.inventaire.Inventaire;
import fr.iutfbleau.jeu.inventaire.Utiliser;
import fr.iutfbleau.jeu.inventaire.Deposer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;


/**
 * La classe <code>Poche</code> est la vue d'un truc dans l'inventaire.
 *
 * @version 1.0
 * @author Marie Pellier, Antoni Blanche
 */
public class Poche extends JPanel
{

  /**
   * Le constructeur.
   *
   * @param truc       le truc
   * @param inventaire l'inventaire
   */
  public Poche(Truc truc, Inventaire inventaire)
  {
    super();

    this.setLayout(new GridLayout(1, 4));
    this.setBackground(Color.BLACK);

    BoutonJeu joueur  = new BoutonJeu("Utiliser",
      Thread.currentThread().getContextClassLoader().getResource("img/boutons/agir.png"));
    BoutonJeu deposer = new BoutonJeu("Jeter",
      Thread.currentThread().getContextClassLoader().getResource("img/boutons/croix.png"));

    joueur.addActionListener(new Utiliser(truc, inventaire));
    deposer.addActionListener(new Deposer(truc, inventaire));

    this.add(new JLabel(new ImageIcon(Thread.currentThread().getContextClassLoader()
                                      .getResource("img/trucs/"
                                      + truc.getTypeTruc().toString() + ".png"))));

    JLabel description = new JLabel(truc.getDescription());
    description.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));
    description.setForeground(Color.WHITE);
    this.add(description);

    this.add(joueur);
    this.add(deposer);
  }
}
