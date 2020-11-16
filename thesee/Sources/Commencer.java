import javax.swing.*;
import java.awt.event.*;

/**
 * La classe <code>Commencer</code> est utilisee pour ouvrir la fenetre de dialogue pour choisir les parametres de la simulation.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */

public class Commencer implements ActionListener
{
	/**
     * Objet de la classe JMenuItem, un bouton dans un menu bar permettant d'ouvrir une fenetre de sauvegarde.
     */
	private JMenuItem sauvegarder = new JMenuItem();
	/**
	 * Objet de la classe Fenetre, c'est la fenetre principale du programme.
	 */
	private Fenetre f1;
 	/**
 	 * Tableau d'int, il sert a enregistrer les coordonnees des cases de la grille.
 	 */
	private int [][] typecase;
	/**
	 * Constructeur permettant de recuperer le bouton de sauvegarde, la fenetre principale et les coordonnees generees depuis la fenetre principale.
	 *
	 * @param f fenetre principale (visible)
 	 * @param s menu de la barre (true ou false)
 	 * @param t2 tableau des entiers types cases (int entre 0 et 11 pour chaque case)
	 */
	public Commencer(Fenetre f, JMenuItem s, int [][] t2)
	{ 
		this.f1=f;
		this.sauvegarder = s;
		this.typecase=t2;
	}
	/**
	 * Reecriture de la methode actionPerformed dans le but que lorsque on clic sur un composant dont la chaine de caractere correspond a celle attendu, execute l'action demandee.
	 * L'action ici est creer un nouveau formulaire de choix de parametre de la simulation.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		/*On va creer une fenetre de formulaire si on clic sur generer.*/
		if(arg0.getActionCommand().equals("Generer"))
		{
			Formulaire zd = new Formulaire("Choix de grille", true, f1, this.sauvegarder,this.typecase);
			zd.showZDialog(); 
			new JOptionPane();
		}
	}
        
}