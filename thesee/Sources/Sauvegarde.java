import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * La classe <code>Commencer</code> va permettre de sauvegarder les caracteristiques de la grille generee.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */

public class Sauvegarde implements ActionListener
{
	/**
	 * Int qui prend la valeur designe pour la taille de la grille.
	 */
	private int cote;
	/** Tableau d'int, il sert a enregistrer les coordonnees des cases de la grille.
	 */
	private int [][] typecase;
	/**
	 * Constructeur permettant de recuperer la taille de la grille ainsi que les coordonnees de chaque type de cases.
	 *
	 * @param c taille du cote de la grille (int entre 0 et 100)
	 * @param t2 tableau des entiers types cases (int entre 0 et 11 pour chaque case)
	 */
	public Sauvegarde(int c, int[][] t2)
	{ 
		this.cote = c;
	    this.typecase=t2;
	  }

	@Override
	/**
	 * Reecriture de la methode actionPerformed dans le but que lorsque on clic sur un composant dont la chaine de caractere correspond a celle attendu, execute l'action demandee.
	 * L'action ici est d'enregistrer la grille dans un fichier grace a un JFileChooser lorsque l'utilisateur a clique sur le bouton sauvegarder.
	 */
	public void actionPerformed(ActionEvent arg0)
	{
	   
	    if(arg0.getActionCommand().equals("Sauvegarder"))
	    {
	    	JPanel fenetre_sauvegarde = new JPanel();
		    	fenetre_sauvegarde.setBackground(Color.white);
		    	fenetre_sauvegarde.setPreferredSize(new Dimension(50, 60));
		    	fenetre_sauvegarde.setBorder(BorderFactory.createTitledBorder("Grille existante"));
	    	JFileChooser fenetre_en_cours = new JFileChooser();
	    		fenetre_en_cours.setCurrentDirectory(new File("./Fic_Lab"));
	    	
	    	String nouveau_fichier= new String();
	    	int retour=fenetre_en_cours.showSaveDialog(fenetre_sauvegarde);
	    	/**
    		 * Recupere le nom saisie par l'utilisateur en ajoutant l'extention de fichier .lab par defaut, et lance la sauvegarde.
    		 */
	    	if(retour==JFileChooser.APPROVE_OPTION)
	    	{
	    		nouveau_fichier=fenetre_en_cours.getSelectedFile().toString();
	    		if(nouveau_fichier.endsWith(".lab")|| nouveau_fichier.endsWith(".LAB"))
	    		{;}
	    		else {
	    			nouveau_fichier = nouveau_fichier+ ".lab";
	    		}
	    		try
	    		{
	    			FileOutputStream fic1 = new FileOutputStream (nouveau_fichier);
	    			DataOutputStream dat1 = new DataOutputStream (fic1);   
	    			int i,j;    
	    			try 
	    			{
	    				/**
	    	    		 * Ecrit l'octet de la  taille de la grille.
	    	    		 */
	    				dat1.write(this.cote);
	    				/*System.out.println("cote= "+cote);*/    				
	    				/**
	    	    		 * Ecrit les 4 octets de position de thesee et du minotaure dans la grille.
	    	    		 */
	    				for (j=1;j<=this.cote+1;j++) 
	    				{
	    					for (i=1;i<=this.cote+1;i++) 
	                        {
	    						if (this.typecase[i][j] == 9) 
	    						{
	    							dat1.write(i-1);
	    							dat1.write(j-1);
	    							/*System.out.println("thesee :"+ i + j);*/
	    						}
	                        }
	                      }	                    
	                      for (j=1;j<=this.cote+1;j++) 
	                      {
	                    	  for (i=1;i<=this.cote+1;i++) 
	                    	  {
	                    		  if (this.typecase [i][j]==10) 
	                    		  {
	                    			  int i2=i;
	                    			  int j2=j;
	                    			  dat1.write(i2-1);
	                    			  dat1.write(j2-1);
	                    			  /*System.out.println("minotaure :"+ i2 + j2);*/
	                    		  }
	                    	  }
	                      }
	                      /**
	                       * Ouvre un flux permettant d'ecrire bit a bit.
	                       */
	                      EcrireBit  fic1_2 = new EcrireBit (fic1);
	                      try {
	                    	  for (j=1;j<=this.cote;j++) 
	                    	  {
	                    		  for (i=1;i<=this.cote;i++) 
	                    		  {
	                    			  if ( this.typecase[i][j] !=8 && this.typecase[i][j] !=7 && this.typecase[i][j] !=6)
	                    			  {  
	                    				  /**
	            	                       * Ecrit un bit de 0 si la case n'est pas une case noir 
	            	                       */
	                    				  fic1_2.writeBit(0);
	                    				  /*System.out.print(" 0 ");*/
	                    			  }
	                    			  else if (this.typecase[i][j] !=10 && this.typecase[i][j] !=9 && this.typecase[i][j] !=5 && this.typecase[i][j] !=4 && this.typecase[i][j] !=3 && this.typecase[i][j] !=2 && this.typecase[i][j] !=1)
	                    			  {
	                    				  /**
	            	                       * Ecrit un bit de 1 si la case est une case noir 
	            	                       */
			                              fic1_2.writeBit(1);
			                              /*System.out.print(" 1 ");*/
	                    			  }
	                    			  else if (this.typecase[i][j] !=1 && this.typecase[i][j] !=2 && this.typecase[i][j] !=3 && this.typecase[i][j] !=4 && this.typecase[i][j] !=5 && this.typecase[i][j] !=6 && this.typecase[i][j] !=7 && this.typecase[i][j] !=8)
	                    			  {
	                    				  /**
	            	                       * Ecrit un bit de 0 si la case n'est pas une case noir 
	            	                       */
	                    				  fic1_2.writeBit(0);
	                    				  /*System.out.print(" 0 ");*/
	                    			  }
	                    		  }
	                    	  }
	                        }
	                      	catch(IOException er)
	                      	{
	                      		System.err.println("erreur d'ecriture du fichier");
	                      	}
	                      /**
	                       * Fermeture du flux de bit a bit. 
	                       */
	                      try 
	                      {
	                    	  fic1_2.close();
	                      }
	                      catch(IOException er) 
	                      {
	                    	  System.err.println("erreur dans la fermeture");
	                      }
	    			}
	    			catch(IOException er) 
	    			{
	    				System.err.println("erreur ouverture du flux");
	    			}
	    			/**
                     * Fermeture du flux de d'ouverture du fichier. 
                     */
	    			try 
                    {
                  	  fic1.close();
                    }
                    catch(IOException er) 
                    {
                  	  System.err.println("erreur dans la fermeture");
                    }
	    		} catch (FileNotFoundException e) 
	    		{
	    			System.err.println("erreur d'ouverture du flux du fichier");          
	    		}
	    		finally{;}
	    	}
	    }         
	}
}