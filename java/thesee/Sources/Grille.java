import javax.swing.*;
import java.lang.Integer;
import java.awt.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * La classe <code>Grille</code> est utilisee pour creer un certain type de grille.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class Grille
{
    /**
     * Objet de la classe Fenetre, c'est la fenetre sur laquelle on travaille.
     */
    private Fenetre f1;
    /**
     * Entier representant la cote de notre carre, c'est a dire sa taille en nombre de case.
     */
    public int cote;
    /**
  	 * JRadioButton representant et recuperant les valeurs respectives du radio button deterministe et random du menu dans le choix de l'algorithme.
  	 */
    private JRadioButton deter, debile;
    /**
     * Bouton sauvegarder dans le JMenuBar
     */
    private JMenuItem sauvegarder;

    /**
     * Constructeur permettant simplement de recuperer les informations renvoye par le formulaire du menu ainsi que la fenetre sur lasquelle on travaille.
     *
     * @param f fenetre principale (visible)
     * @param c taille du cote de la grille (int entre 0 et 100
     * @param dt bouton radio selection algorithme deterministe (true ou false)
 	 * @param db bouton radio selection algorithme debile (true ou false)
 	 * @param s menu de la barre (true ou false)
     */
    public Grille(Fenetre f, int c, JRadioButton dt, JRadioButton db, JMenuItem s)
    {
        this.f1 = f;
        this.cote = c;
        this.deter = dt;
        this.debile = db;
        this.sauvegarder = s;
    }

    /**
     * Methode permettant de creer une grille personnalise remplie aleatoirement.
     */
    public void grillePA()
    {
        final int MIN = 1;
        final int MAX = 8;

        int i, j, i2, j2;
        int[][] typecase = new int[this.cote + 2][this.cote + 2];

		GridLayout gestionnaire = new GridLayout(this.cote + 2, this.cote + 2);
		f1.setLayout(gestionnaire);

        /*On cree la frontiere invisible en assignant a la bordure le type mur*/
            /*System.out.println("Frontiere");*/

            for (i = 0; i <= this.cote + 1; i++)
            {
                for (j = 0; j <= this.cote + 1; j++)
                {
                    /*Si on se trouve a la premiere ligne on met une frontiere sur toute la ligne du dessus*/
                    if (i == 0)
                    {
                        typecase[i][j] = 0;
                    }
                    /*Si on se trouve a la derniere ligne on met une frontiere sur toute la ligne du dessous*/
                    else if (i == this.cote + 1)
                    {
                        typecase[i][j] = 0;
                    }

                    /*Si on se trouve a la premiere case d'une ligne entre la premiere et derniere ligne on met une frontiere sur la case de gauche*/
                    else if (i > 0 || i < this.cote + 2)
                    {
                        if(j == 0)
                        {
                            typecase[i][j] = 0;
                        }

                        else if (j == this.cote + 1)
                        {
                            typecase[i][j] = 0;
                        }
                    }
                    /*Debugger du tableau des type cases de la grille comprenant la frontiere*/
                    /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
                }
            }

    	/*On tire au hasard les cases avec des murs ou des chemins*/
            /*System.out.println("Remplissage de la grille");*/

        	Random rand = new Random();

        	for (i = 1; i <= this.cote; i++)
        	{
                for (j = 1; j <= this.cote; j++)
                {
                	typecase[i][j] = rand.nextInt((MAX - MIN) + 1) + MIN;
                    /*Debugger du tableau des type cases de la grille comprenant la frontiere*/
                    /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
            	}
        	}

    	/*On tire au hasard une case de la grille pour lui assigner Thesee*/
        	i = rand.nextInt((this.cote - MIN) + 1) + MIN;
        	j = rand.nextInt((this.cote - MIN) + 1) + MIN;

        	typecase[i][j] = 9;

    	/*On tire au hasard une case de la grille pour lui assigner la sortie*/
        	i2 = rand.nextInt((this.cote - MIN) + 1) + MIN;
        	j2 = rand.nextInt((this.cote - MIN) + 1) + MIN;

            while (i2 == i && j2 == j)
            {
                i2 = rand.nextInt((this.cote - MIN) + 1) + MIN;
                j2 = rand.nextInt((this.cote - MIN) + 1) + MIN;
            }
            i = i2;
            j = j2;
            typecase[i][j] = 10;


         /*Debugger du tableau  de la grille de jeu comprenant tout*/
            /*System.out.println("Debugger Grille de jeu");*/
            for (i = 1; i <= this.cote; i++)
            {
                for (j = 1; j <= this.cote; j++)
                {
                    /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
                }
            }

        /*On cree notre grille*/
            /*System.out.println("Grille");*/
            for (i = 0; i <= this.cote + 1; i++)
            {
              for (j = 0; j <= this.cote + 1; j++)
              {
                  /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
                  f1.add(new DessinCase(typecase, i, j, i, j));
              }
            }

            JOptionPane assistoligne = new JOptionPane();
            assistoligne.showMessageDialog(null,"\nUtilisation du mode creation :\n\nVous commencez de la premiere case en haut a gauche.\nAttention il doit y avoir 1 Thesee et 1 sortie pas plus, pas moins.\n\n\nFleche Directionnelle = Permet de vous deplacer dans la grille\nECHAP = Quitte le mode creation et passe a la simulation\nF1 = Change la case et les cases suivantes en une case chemin. \nF2 = Change la case et les cases suivantes en une case mur\nF3 = Change la case en Thesee\nF4 = Change la case en sortie\n\n", "Assisto-ligne", JOptionPane.INFORMATION_MESSAGE);

		/*Lancement de la configuration*/
			ConfigurationCase configuration_case = new ConfigurationCase(f1, typecase, i, j, i2, j2, this.cote, deter, debile);
			f1.addKeyListener(configuration_case);

		/*Instance de la sauvegarde*/
			Sauvegarde save = new Sauvegarde (cote, typecase);
			sauvegarder.addActionListener(save);

		f1.setVisible(true);
    }

    /**
     * Methode permettant de creer une grille personnalise vide
     */
    public void grillePV()
    {
        int i = 0, j = 0;
        int[][] typecase = new int[this.cote + 2][this.cote + 2];

		GridLayout gestionnaire = new GridLayout(this.cote + 2, this.cote + 2);
		f1.setLayout(gestionnaire);

        /*On cree la frontiere invisible en assignant a la bordure le type mur*/
            /*System.out.println("Frontiere");*/

            for (i = 0; i <= this.cote + 1; i++)
            {
                for (j = 0; j <= this.cote + 1; j++)
                {
                    /*Si on se trouve a la premiere ligne on met une frontiere sur toute la ligne du dessus*/
                    if (i == 0)
                    {
                        typecase[i][j] = 0;
                    }
                    /*Si on se trouve a la derniere ligne on met une frontiere sur toute la ligne du dessous*/
                    else if (i == this.cote + 1)
                    {
                        typecase[i][j] = 0;
                    }

                    /*Si on se trouve a la premiere case d'une ligne entre la premiere et derniere ligne on met une frontiere sur la case de gauche*/
                    else if (i > 0 || i < this.cote + 2)
                    {
                        if(j == 0)
                        {
                            typecase[i][j] = 0;
                        }

                        else if (j == this.cote + 1)
                        {
                            typecase[i][j] = 0;
                        }
                    }
                    /*Debugger du tableau des type cases de la grille comprenant la frontiere*/
                    /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
                }
            }

        /*On rempli notre grille*/
            /*System.out.println("Remplissage de la grille");*/
        	for (i = 1; i <= this.cote; i++)
        	{
                for (j = 1; j <= this.cote; j++)
                {
                	typecase[i][j] = 1;
                    /*Debugger du tableau des type cases de la grille comprenant la frontiere*/
                    /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
            	}
        	}

        /*On cree notre grille*/
            /*System.out.println("Grille");*/
            for (i = 0; i <= this.cote + 1; i++)
            {
              for (j = 0; j <= this.cote + 1; j++)
              {
                  /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
                  f1.add(new DessinCase(typecase, i, j, i, j));
              }
            }

            JOptionPane assistoligne = new JOptionPane();
            assistoligne.showMessageDialog(null,"\nUtilisation du mode creation :\n\nVous commencez de la premiere case en haut a gauche.\nAttention il qu'il y est absolument 1 Thesee et 1 sortie pas plus, pas moins.\n\n\nFleche Directionnelle = Permet de vous deplacer dans la grille\nECHAP = Quitte le mode creation et passe a la simulation\nF1 = Change la case et les cases suivantes en une case chemin. \nF2 = Change la case et les cases suivantes en une case mur\nF3 = Change la case en Thesee\nF4 = Change la case en sortie\n\n", "Assisto-ligne", JOptionPane.INFORMATION_MESSAGE);

		/*Lancement de la configuration*/
			ConfigurationCase configuration_case = new ConfigurationCase(f1, typecase, i, j, i, j, this.cote, deter, debile);
			f1.addKeyListener(configuration_case);

		/*Instance de la sauvegarde*/
			Sauvegarde save = new Sauvegarde (cote, typecase);
			sauvegarder.addActionListener(save);

		f1.setVisible(true);
    }

    /**
     * Methode permettant de charger une grille deja existante
     *
     * @param fichier chemin du fichier dans l'arborescence systeme a charger (String) 
     */
    public void grilleChargee(String fichier)
    {
        try
        {
            FileInputStream fis = new FileInputStream(fichier);
            DataInputStream data = new DataInputStream(fis);
            try
            {
                int i, j, iThesee, jThesee, iSortie, jSortie, iMur, jMur, cotee, bytegrille, bit;
                String s,output="";
                ArrayList<Integer> tab = new ArrayList<Integer>();

                cotee = data.read();
                iThesee = data.read() + 1;
                jThesee = data.read() + 1;
                iSortie = data.read() + 1;
                jSortie = data.read() + 1;



                /*On cree le tableau en fonction du cote du fichier*/
                int[][] typecase = new int[cotee + 2][cotee + 2];

                /*On initialise la frontiere de la grille*/
                for (i = 0; i <= cotee + 1; i++)
                 {
                     for (j = 0; j <= cotee + 1; j++)
                     {
                         /*Si on se trouve a la premiere ligne on met une frontiere sur toute la ligne du dessus*/
                             if (i == 0)
                             {
                                 typecase[i][j] = 0;
                             }
                         /*Si on se trouve a la derniere ligne on met une frontiere sur toute la ligne du dessous*/
                             else if (i == cotee + 1)
                             {
                                 typecase[i][j] = 0;
                             }

                         /*Si on se trouve a la premiere case d'une ligne entre la premiere et derniere ligne on met une frontiere sur la case de gauche*/
                             else if (i > 0 || i < cotee + 2)
                             {
                                 if(j == 0)
                                 {
                                     typecase[i][j] = 0;
                                 }

                                 else if (j == cotee + 1)
                                 {
                                     typecase[i][j] = 0;
                                 }
                             }
                     }
                 }

                for(i = 1; i <= cotee; i++)
                {
                    for(j = 1; j <= cotee; j++)
                    {
                        typecase[i][j] = 1;
                    }
                }

                i = 1;
                j = 1;

                while (data.available() != 0)
                {
                    bytegrille = data.read();
                    s=Integer.toBinaryString(bytegrille);
                    output = output+String.format("%8s", s).replace(' ','0');
                }
                    for(i = 0; i < output.length(); i++)
                    {
                        tab.add(Integer.parseInt(""+output.charAt(i)));
                    }
                    jMur=0;
                    iMur=1;
                    i=0;
                    while (i <output.length())
                        {

                        if (jMur>cotee-1) {
                            jMur=1;
                            iMur++;
                        }else
                        {
                            jMur++;
                        }
                        if ( tab.get(i) == 1)
                        {
                            /*System.out.println(tab);*/
                            typecase[jMur][iMur] = 6;
                        }
                         /*System.out.println(iMur+" "+jMur);*/
                        i++;
                        }


                   /*On place Thesee a la position de notre grille chargee*/
                        typecase[iThesee][jThesee] = 9;
                        typecase[iSortie][jSortie] = 10;

                    /*On cree notre grille*/
                        GridLayout gestionnaire = new GridLayout(cotee + 2, cotee + 2);
                        f1.setLayout(gestionnaire);

                        for (i = 0; i <= cotee + 1; i++)
                        {
                            for (j = 0; j <= cotee + 1; j++)
                            {
                                /*System.out.println("\ti = " + i + "| j = " + j + "| typecase = " + typecase[i][j]);*/
                                f1.add(new DessinCase(typecase, i, j, i, j));
                            }
                        }

                        JOptionPane assistoligne = new JOptionPane();
                        assistoligne.showMessageDialog(null,"\nUtilisation du mode creation :\n\nVous commencez de la premiere case en haut a gauche.\nAttention il qu'il y est absolument 1 Thesee et 1 sortie pas plus, pas moins.\n\n\nFleche Directionnelle = Permet de vous deplacer dans la grille\nECHAP = Quitte le mode creation et passe a la simulation\nF1 = Change la case et les cases suivantes en une case chemin. \nF2 = Change la case et les cases suivantes en une case mur\nF3 = Change la case en Thesee\nF4 = Change la case en sortie\n\n", "Assisto-ligne", JOptionPane.INFORMATION_MESSAGE);

                    /*Lancement de la configuration*/
                        ConfigurationCase configuration_case = new ConfigurationCase(f1, typecase, i, j, i, j, cotee, deter, debile);
                        f1.addKeyListener(configuration_case);

                    /*Instance de la sauvegarde*/
                        Sauvegarde save = new Sauvegarde (cote, typecase);
                        sauvegarder.addActionListener(save);
            }
            catch(IOException err)
            {
                System.err.println("Erreur de lecture du fichier");
            }
         }
         catch (IOException err)
         {
            System.err.println("Erreur de nom du fichier");
         }
    }
}