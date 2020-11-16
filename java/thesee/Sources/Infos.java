import javax.swing.*;
import java.awt.event.*;

/**
 * La classe <code>Commencer</code> est utilisee pour ouvrir la fenetre de dialogue pour afficher les touhes utilisees pour la modifications de la grille generee.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */

public class Infos implements ActionListener
{

	/**
	 * Reecriture de la methode actionPerformed dans le but que lorsque on clic sur un composant dont la chaine de caractere correspond a celle attendu, execute l'action demandee.
	 * L'action ici est d'afficher une fenetre de dialogue d'un message indiquant l'utilite de chaque touche pour le programme.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getActionCommand().equals("Commandes"))
			{	 
			   JOptionPane assistoligne = new JOptionPane();
	           assistoligne.showMessageDialog(null,"\nUtilisation du mode creation :\n\nVous commencez de la premiere case en haut a gauche.\nAttention il doit y avoir 1 Thesee et 1 sortie pas plus, pas moins.\n\n\nFleche Directionnelle = Permet de vous deplacer dans la grille\nECHAP = Quitte le mode creation et passe a la simulation\nF1 = Change la case et les cases suivantes en une case chemin. \nF2 = Change la case et les cases suivantes en une case mur\nF3 = Change la case en Thesee\nF4 = Change la case en sortie\n\n", "Assisto-ligne", JOptionPane.INFORMATION_MESSAGE); 
			}
		}         
}