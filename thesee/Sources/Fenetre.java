import javax.swing.*;
/**
 * La classe <code>Fenetre</code> va permettre de generer une fenetre principal pour afficher la simulation
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class Fenetre extends FenetreOrigine
{
	/**
	 * Tableau d'entier a deux dimensions representant en fonction de la position le type de la case correspondant.
	 */
	private int [][] typecase;
	/**
     * Constructeur permettant de recuperer la configuration de la grille generee.
     *
     * @param grille grille generee
     * @param t2 tableau des entiers types cases (int entre 0 et 11 pour chaque case)
     */
	public Fenetre(Grille grille,int [][] t2)
	{ 
		this.typecase=t2;
	}
	/**
     * definition de la fenetre principale.
     */
	public Fenetre()
	{
		/**
	     * Jmenubar avec bouton commencer et sauvegarder
	     */
	       
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Fichier");
		menuBar.add(menu1);
		JMenuItem commencer = new JMenuItem ("Generer");
		menu1.add(commencer);
		JMenuItem sauvegarder = new JMenuItem ("Sauvegarder");
		menu1.add(sauvegarder);
		JMenu menu2 = new JMenu("?");
		JMenuItem commandes = new JMenuItem ("Commandes");
		menu2.add(commandes);
		menuBar.add(menu2);
		setJMenuBar(menuBar);
	
		Commencer start=new Commencer(this,sauvegarder,typecase);
		Infos info = new Infos();
		commandes.addActionListener(info);
		commencer.addActionListener(start);
	}
}