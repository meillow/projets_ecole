import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
/**
 * La classe <code>Commencer</code> va permettre de lancer la simulation en fonction des choix effectues par l'utilisateur.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class OkButton implements ActionListener
{
	/**
	 * Bouton radio de choix de la grille et de l'algorithme.
	 */
	private JRadioButton nouvelle,existante,vide, alea,deter,debile;
	/**
     * Zone de saisie de texte de la taille de la grille.
     */
	private JTextField taille;
	/**
     * Formulaire creee.
     */
	private Formulaire zd;
	/**
     * Fenetre principale.
     */
	private Fenetre f1;
	/**
     * Menu barre.
     */
	private JMenuItem sauvegarder;
	/**
 	 * Ce constructeur recuperer tous les choix effectues lors du formulaire et effectues les changements pour l'affichage de la simulation dans la fenetre principale.
 	 * 
 	 * @param textt zone de texte qui contient forcement un entier (int entre 1 et 100)
 	 * @param vd bouton radio selection generation grille vide (true ou false)
 	 * @param al bouton radio selection generation grille aleatoire (true ou false)
 	 * @param dt bouton radio selection algorithme deterministe (true ou false)
 	 * @param db bouton radio selection algorithme debile (true ou false)
 	 * @param nv bouton radio selection generation nouvelle grille (true ou false)
 	 * @param exist bouton radio selection generation grille existante (true ou false)
 	 * @param weedow fenetre du formulaire (visible ou non visible)
 	 * @param f fenetre principale (visible)
 	 * @param s menu de la barre (true ou false)
 	 * @param t2 tableau des entiers types cases (int entre 0 et 11 pour chaque case)
 	 */
	public OkButton(JTextField textt,JRadioButton vd, JRadioButton al, JRadioButton dt,JRadioButton db,JRadioButton nv,JRadioButton exist,Formulaire weedow, Fenetre f, JMenuItem s, int [][] t2)
	{ 
		this.sauvegarder = s;
	    this.taille=textt;
	    this.vide=vd; 
	    this.alea=al;
	    this.deter=dt; 
	    this.debile=db;  
	    this.nouvelle=nv;
	    this.existante=exist;
	    this.zd=weedow;
	    this.f1=f;
	}
	  /**
	   * Reecriture de la methode actionPerformed dans le but que lorsque on clic sur un composant dont la chaine de caractere correspond a celle attendu, execute l'action demandee.
	   * L'action ici est de soit generer une grille en la creant, soit de faire apparaitre un jFileChooser pour laisse l'utilisateur choisir le fichier
	   * contenant une grille prealablement creee.
	   */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		/**
		 * Si l'utilisateur choisi de generer la simulation, le programme va utiliser des methodes en fonction des choix effectues.
		 */  
		if(arg0.getActionCommand().equals("Generer"))
		{
			if(nouvelle.isSelected())
			{
				try 
				{  
					int cote = Integer.parseInt(this.taille.getText());
			
					if(cote > 0 && cote < 101)
					{
						Grille grille = new Grille(this.f1, cote, deter, debile, sauvegarder);
			
						if(alea.isSelected())
						{
							/**
							 * Cree une grille aleatoirementen fonction de la taille.
							 */  
							/*System.out.println("GRILLE ALEATOIRE");*/
							grille.grillePA();
						}
			          	else if(vide.isSelected())
			          	{
			          		/**
							 * Cree une grille vide fonction de la taille.
							 */ 
			          		/*System.out.println("GRILLE VIDE");*/
			          		grille.grillePV();
			          	}
		
			          	zd.setVisible(false);
			        }
			        else
			        {
			        	/**
						 * Si l'utilisateur n'a pas choisi une taille valide alors il aura un message d'erreur lui indiquant le bon format.
						 */ 
			        	JOptionPane erreur1 = new JOptionPane();
			        	erreur1.showMessageDialog(null,"WOW ! Erreur format invalide (0 < x < 101)", "Erreur", JOptionPane.ERROR_MESSAGE);
			        	System.err.println(" Erreur format invalide (0 < x < 101)");
			        }
				}  
		      
				catch(NumberFormatException e) 
				{
					JOptionPane erreur = new JOptionPane();
					erreur.showMessageDialog(null,"WOW ! Erreur format invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					System.err.println("WOW ! Erreur format invalide.");
				}
			}
			/**
			 * Si le choix est une grille existante, un JFileChooser va se lancer pour que l'utilisateur choisisse le fichier a charger.
			 */ 
		    else if(existante.isSelected())
		    {
		    	JPanel grille_existante = new JPanel();
		    	grille_existante.setBackground(Color.white);
		    	grille_existante.setPreferredSize(new Dimension(50, 60));
		    	grille_existante.setBorder(BorderFactory.createTitledBorder("Grille existante"));
		
		
		    	final JFileChooser choix = new JFileChooser();
		    	choix.setCurrentDirectory(new File("./Fic_Lab"));
		    	int retour=choix.showOpenDialog(grille_existante);
		    	String fichier= new String();
		    	
		    	/**
				 * Recupere le nom du fichier lorsque l'utilisateur valide son choix, ferme la fenetre de dialogue et charge le fichier
				 * dans la fenetre principale.
				 */
		    	if(retour==JFileChooser.APPROVE_OPTION)
		    	{       
		    		fichier = choix.getSelectedFile().toString();
		    		/*System.out.println(fichier);*/
		
			        zd.setVisible(false);
			        this.f1.setSize(700, 700);
			        this.f1.setLocation(600, 200);
		
			        Grille grille = new Grille(this.f1, 0, deter, debile, sauvegarder);
			        grille.grilleChargee(fichier);
		    	}
		    }
		}
	}
	/**
	 * Methode qui renvoie le texte saisie pour la tailel de la grille.
	 * @return la chaine de caractere correspondant au nom du bouton permettant de savoir si on charge une grille existante.
	 */
	public String getNouvelleGrille()
	{
		return nouvelle.isSelected() ? nouvelle.getText() : (existante.isSelected()) ? existante.getText() : nouvelle.getText();  
	}
	/**
	 * Methode qui renvoie le choix des boutons de grille choisie.
	 * @return la chaine de caractere correspondant au nom du bouton permettant de savoir quel type de grille l'utilisateur a-t-il selectionne.
	 */
	public String getTypeGrille()
	{
	    return vide.isSelected() ? vide.getText() : (alea.isSelected()) ? alea.getText() : vide.getText();  
	}
	/**
	 * Methode qui renvoie le choix des radio boutons de choix de l'algorithme.
	 * @return la chaine de caractere correspondant au nom du bouton permettant de savoir quel type d'algorithme l'utilisateur a-t-il selectionne.
	 */
	public String getAlgo()
	{
	return deter.isSelected() ? deter.getText() : debile.isSelected() ? debile.getText() : deter.getText();  
	}
}