import java.awt.*;
import javax.swing.*;
/**
 * La classe <code>Fenetre</code> va permettre d'afficher une fenetre de dialogue avec un menu pour que l'utilisateur choisisse 
* les parametres de la simulation et la lance.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */

public class Formulaire extends JDialog 
{
	/**
	 * Zone de texte annoncant qu'il va falloir saisir un texte.
	 */
	private JLabel txt_taille;
	/**
	 * Bouton radio servant a choisir les parametres d'algorithme et de creation de grille.
	 */
	private JRadioButton existante, nouvelle, deter, debile, vide, alea;
	/**
	 * Zone de texte servant a entrer un entier codant pour la taille de la grille.
	 */
	private JTextField taille;
	/**
	 * Fenetre principale ou sera affiche la grille.
	 */
	private Fenetre f1;
	/**
	 * Bouton du menu barre pour la sauvegarde.
	 */
	private JMenuItem sauvegarder;
	/**
	 * Tableau d'entier a deux dimensions representant en fonction de la position le type de la case correspondant.
	 */
	private int [][] typecase;
	/**
	 * Constructeur permettant de transmettre toutes les specifites de la fenetre de dialogue.
	 *
	 * @param t titre de la fenetre formulaire
	 * @param m visibilite de la fenetre formulaire (true ou false)
	 * @param f fenetre principale (visible)
	 * @param s menu de la barre (true ou false)
     * @param t2 tableau des entiers types cases (int entre 0 et 11 pour chaque case)
	 */

	public Formulaire(String t, boolean m, Fenetre f, JMenuItem s, int [][] t2)
	  {
		this.sauvegarder = s;
	    this.f1=f;
	    this.setSize(550, 400);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	    this.typecase=t2;
	  
	  }
	/**
	 * Methode qui va afficher la fenetre de dialogue.
	 */
	public void showZDialog()
	{
	    this.setVisible(true);      
	}
	/**
	 * Methode qui va definire tous les composant de la fenetre de dialogue.
	 */
	private void initComponent()
	{
		/**
		 * choix de la taille de la grille.
		 */
		JPanel content = new JPanel();
			content.setPreferredSize(new Dimension(220, 600));
			content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel texte_taille = new JPanel();
		    texte_taille.setBorder(BorderFactory.createTitledBorder("Taille choisie"));
		    texte_taille.setBackground(Color.white);
		    texte_taille.setPreferredSize(new Dimension(220, 80));
		taille = new JTextField();
		taille.setPreferredSize(new Dimension(100, 25));
		
		txt_taille = new JLabel("Saisir un nombre :");
		
		txt_taille.setEnabled(false);
		taille.setEnabled(false);
		
		texte_taille.add(txt_taille);
		texte_taille.add(taille);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		content.add(texte_taille,gbc);
		
		/**
		 * choix d'une nouvelle grille ou d'une existante.
		 */
		JPanel choix_grille = new JPanel();
		    choix_grille.setBackground(Color.white);
		    choix_grille.setBorder(BorderFactory.createTitledBorder("Choix du type de grille"));
		    choix_grille.setPreferredSize(new Dimension(440, 60));
		existante = new JRadioButton("Existante");
		nouvelle = new JRadioButton("Nouvelle");
		
		ButtonGroup bg2 = new ButtonGroup();
			bg2.add(existante);
			bg2.add(nouvelle);
		choix_grille.add(existante);
		choix_grille.add(nouvelle);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		content.add(choix_grille,gbc);
		
		/**
		 * choix d'une grille vide ou d'une grille generee aleatoirement en fonction de la taille saisie.
		 */
		JPanel choix_type_grille = new JPanel();
		    choix_type_grille.setBackground(Color.white);
		    choix_type_grille.setBorder(BorderFactory.createTitledBorder("Choix de la grille"));
		    choix_type_grille.setPreferredSize(new Dimension(440, 60));
		vide = new JRadioButton("Vide");
		vide.setSelected(true);
		alea = new JRadioButton("Aleatoire");
	
	    ButtonGroup bg = new ButtonGroup();
		    bg.add(vide);
		    bg.add(alea);
	    choix_type_grille.add(vide);
	    choix_type_grille.add(alea);
	
	    alea.setEnabled(false);
	    vide.setEnabled(false);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	
	    content.add(choix_type_grille,gbc);
				
	    /**
		 * choix de l'algorithme entre deterministe et debile.
		 */
		JPanel choix_algo = new JPanel();
		    choix_algo.setBackground(Color.white);
		    choix_algo.setBorder(BorderFactory.createTitledBorder("Choix Algorithme"));
		    choix_algo.setPreferredSize(new Dimension(440, 60));
		deter = new JRadioButton("Deterministe");
		deter.setSelected(true);
		debile = new JRadioButton("Debile");
		
		ButtonGroup bg3 = new ButtonGroup();
			bg3.add(deter);
			bg3.add(debile);
        choix_algo.add(deter);
        choix_algo.add(debile);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        content.add(choix_algo,gbc);

        content.setBackground(Color.white);
		   
		    
        /**
		 * bouton de lancement de la simulation ou d'annulation.
		 */
		
		JPanel control = new JPanel();
		
		JButton okBouton = new JButton("Generer");
		JButton cancelBouton = new JButton("Annuler");
		       
			control.add(okBouton);
		    control.add(cancelBouton);
		   
		
		    gbc.gridx = 1;
		    gbc.gridy = 4;
		    gbc.gridheight = 1;
		    gbc.gridwidth = 1;
		    content.add(control,gbc);
		        
	    /**
		 * lance les actions correspondantes aux boutons choisi
		 */
		ExistanteGrille eg =new ExistanteGrille(txt_taille,taille,vide,alea);
			existante.addActionListener(eg);

       	NouvelleGrille nvg =new NouvelleGrille(txt_taille, taille,vide,alea);
       		nouvelle.addActionListener(nvg); 

        OkButton ok =new OkButton(taille, vide,alea,deter,debile,nouvelle,existante,this,this.f1,sauvegarder,typecase);
        	okBouton.addActionListener(ok); 

        CancelBouton annuler =new CancelBouton(this);
        	cancelBouton.addActionListener(annuler); 

        this.getContentPane().add(content);
	}  
}
