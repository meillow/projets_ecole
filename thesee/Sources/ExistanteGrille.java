import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * La classe <code>Commencer</code> va permettre de modifier l'apparence du formulaire en fonction de si l'utilisateur a choisi de creer une grille existante.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class ExistanteGrille implements ActionListener
{
	/**
	 * Bouton radio de choix de la grille vide ou generee aleatoirement.
	 */
     private JRadioButton vide, alea;
     /**
      * Titre de la zone de saisie de texte de la taille de la grille.
      */
     private JLabel txt_taille;
     /**
      * Zone de saisie de texte de la taille de la grille.
      */
     private JTextField taille;
     /**
 	 * Ce constructeur recupere certains boutons de la boite de dialogue.
 	 * 
 	 * @param sz zone de texte qui contient forcement un entier (int entre 1 et 100)
 	 * @param vd bouton radio selection generation grille vide (true ou false)
 	 * @param al bouton radio selection generation grille aleatoire (true ou false)
	 * @param textt zone texte 
 	 */
     public ExistanteGrille(JLabel textt, JTextField sz, JRadioButton vd,JRadioButton al)
     { 
    	 this.txt_taille=textt;
    	 this.taille=sz; 
    	 this.vide=vd; 
    	 this.alea=al;  
     }
     @Override
     /**
 	 * Reecriture de la methode actionPerformed dans le but que lorsque on clic sur un composant dont la chaine de caractere correspond a celle attendu, execute l'action demandee.
 	 * L'action ici est de rendre non utilisable la zone de texte et le choix de creation de grille lorsqu'il s'agit d'une grille existante.
 	 */
	 public void actionPerformed(ActionEvent arg0) 
     {
    	 if(arg0.getActionCommand().equals("Existante"))
    	 { 
    		 txt_taille.setEnabled(false);
    		 taille.setEnabled(false);
    		 alea.setEnabled(false);
    		 vide.setEnabled(false);
    	 }
     }   
}

          
