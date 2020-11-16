import javax.swing.*;
import java.awt.event.*;
/**
 * La classe <code>CancelBouton</code> est utilisee pour fermer la fenetre de dialogue de choix de creation de grille.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class CancelBouton implements ActionListener
{
  /**
   * Objet de la classe Formulaire, c'est ce qui permet de choisir les caracterisques de la simulation.
   */
	private Formulaire zd;
  /**
   * Constructeur permettant de recuperer le bouton annuler du formulaire ainsi que de recuperer le formulaire.
   *
   * @param weedow fenetre du formulaire (visible ou non visible)
   */
	public CancelBouton(Formulaire weedow)
	{ 
    	this.zd=weedow;
  	}
  /**
   * Reecriture de la methode actionPerformed dans le but que lorsque on clic sur un composant dont la chaine de caractere correspond a celle attendu, execute l'action demandee.
   * L'action ici est de rendre le formulaire invisible
   */
	@Override
  	public void actionPerformed(ActionEvent arg0) 
  	{ 
    	if(arg0.getActionCommand().equals("Annuler"))
    	{
    		zd.setVisible(false);
    	}  
   	}
}