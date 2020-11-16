import javax.swing.*;
import java.io.*;

/**
 * La classe <code>Deroulement</code> est utilisee pour gerer le mode de visualisation de la simulation
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class Deroulement
{
  /**
   * Entier representant la position i et j de la case et i2 et j2 dans certains cas. On a enfin la cote de notre carre, c'est a dire sa taille en nombre de case.
   * La variable choixderoulement permet de gerer le choix du deroulement fait par l'utilisateur.
   * L'entier etape est un compteur pour savoir combien d'etape il a fallut pour que l'algorithme se termine.
   * La variable moyenne_fic recupere la valeur de moyenne du fichier et moyenne_alea ou moyenne_deter permettent de calculer la nouvelle moyenne a partir du resultat de l'algorithme.
   */
	private int i, j, i2, j2, cote, choixderoulement, etape, moyenne_fic, moyenne_deter, moyenne_alea;
  /**
   * JRadioButton representant et recuperant les valeurs respectives du radio button deterministe et random du menu dans le choix de l'algorithme.
   */
  private JRadioButton deter,debile;
  /**
   * Fenetre heritant elle meme de JFrame c'est la fenetre sur laquelle on travaille.
   */
	private JFrame f1;
  /**
   * Tableau d'entier a deux dimensions representant tout les typecase de notre grille.
   */
	private int[][] typecase;

  /**
   * Constructeur permettant de recuperer la fenetre sur lasquelle on travaille, le tableau des type cases de notre grille.
   * Mais aussi les positions i et j ou i2 et j2 de Thesee ainsi que le cote de la grille.
   * Et enfin le choix de l'algorithme avec les deux radio button, et le choix du deroulement.
   *
   * @param f fenetre principale (visible)
   * @param tc tableau des entiers types cases (int entre 0 et 11 pour chaque case)
   * @param origini position i / l'ordonnee (int entre 0 et 100)
   * @param originj position j / l'abcisse (int entre 0 et 100) 
   * @param origini2 position i2 / l'ordonnee (int entre 0 et 100)
   * @param originj2 position j2 / l'abcisse (int entre 0 et 100)
   * @param c taille du cote de la grille (int entre 0 et 100)
   * @param dt bouton radio selection algorithme deterministe (true ou false)
   * @param db bouton radio selection algorithme debile (true ou false)
   * @param cd choix du deroulement de l'algorithme (1 ou 2) 
   */
	public Deroulement(JFrame f, int[][] tc, int origini, int originj, int origini2, int originj2, int c, JRadioButton dt, JRadioButton db, int cd)
	{
		this.f1 = f;
		this.typecase = tc;
		this.i = origini;
		this.j = originj;
		this.i2 = origini2;
		this.j2 = originj2;
	    this.cote = c;
	    this.deter = dt;
	    this.debile = db;
	    this.choixderoulement = cd;
	    this.etape = 0;

	    this.moyenne_fic = 0;
	    this.moyenne_alea = 0;
	    this.moyenne_deter = 0;

	    if (this.choixderoulement == 1)
	    {
	      this.f1.setVisible(false);
	      deroulementAutomatique();
	    }
	    else if (this.choixderoulement == 2)
	    {
	      deroulementManuel();
	    }
	}

	/**
	 * Methode de visualisation automatique de l'algorithme 
	 */
  	public void deroulementAutomatique()
    {
      boolean fin = false;

      /*On initialise le tableau des cases visitees a 0 partout*/
        int[][] visitee = new int[this.cote + 2][this.cote + 2];
        for (i = 1; i <= this.cote; i++)
        {
          for (j = 1; j <= this.cote; j++) 
          {
            visitee[i][j] = 0;
          }
        }

      /*On instancie notre classe itineraire pour faire ensuite un certain type d'algorithme*/
        Itineraire it = new Itineraire(f1, typecase, visitee, i, j, i2, j2, this.cote, this.choixderoulement);

        if (deter.isSelected()) 
        {
          while(this.etape < 10000 && fin == false)
          {
            this.etape ++;
            fin = it.deplacementTheseeDeterministe();

            /*Debugger des varaibles etape et fin*/
              /*System.out.println("\nEtape : " + etape);*/
              /*System.out.println("Fin : " + fin);*/
          }
          if (fin == true) 
          {
            /*Lecture de la moyenne*/
             try
              {
                FileInputStream fic1 = new FileInputStream ("./Sauvegarde_Moyenne/Moyenne_Deterministe.bin");
                DataInputStream dat1 = new DataInputStream (fic1);
              
                try
                {
                  this.moyenne_fic = dat1.readInt();
                }
                catch(IOException e)
                {
                  System.err.println("Erreur de lecture du fichier");
                }

                try
                {
                  dat1.close();
                }
                catch(IOException e)
                {
                  System.err.println("Erreur de fermeture du fichier.");
                }
              }
              catch(IOException e)
              {
                System.err.println("Erreur d'ouverture du fichier, ce fichier n'existe pas"); 
              }

            /*Moyenne des coups*/
              moyenne_deter = (etape + moyenne_fic) / 2;

            /*Sauvegarde la nouvelle moyenne*/
              try
              {
                FileOutputStream fic2 = new FileOutputStream ("./Sauvegarde_Moyenne/Moyenne_Deterministe.bin");
                DataOutputStream dat2 = new DataOutputStream (fic2);

                try
                {
                  dat2.writeInt(moyenne_deter);
                }

                catch(IOException e)
                {
                  System.err.println("Erreur d'ecriture dans le fichier.");
                }

                try
                {
                  dat2.close();
                }
                catch(IOException e)
                {
                  System.err.println("Erreur de fermeture du fichier.");
                }
              }
              catch(IOException e)
              {
                System.err.println("Erreur dans l'ouverture du fichier.");
              }

            /*Message user*/
              JOptionPane assistoligne = new JOptionPane();
              if(fin == false)
              {
                assistoligne.showMessageDialog(null,"La simulation est termine, Thesee a du se deplacer" + " " + etape + " " + "fois pour sortir du labyrinthe. La moyenne de Thesee en mode deterministe est de " + moyenne_deter , "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
              }
              else
              {
                assistoligne.showMessageDialog(null,"La simulation est termine, Thesee a du se deplacer" + " " + etape + " " + "fois pour sortir du labyrinthe. La moyenne de Thesee en mode deterministe est de " + moyenne_deter , "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
              }
                /*System.out.println("Deplacement total : " + etape);*/

          }
        }

        else if (debile.isSelected()) 
        {
          while(this.etape < 10000 && fin == false)
          {
            fin = it.deplacementTheseeAleatoire();
            this.etape ++;

            /*Debugger des varaibles etape et fin*/
              /*System.out.println("Etape : " + etape);*/
              /*System.out.println("Fin : " + fin);*/
          }
          

            /*Lecture de la moyenne*/
              try
              {
                FileInputStream fic1 = new FileInputStream ("./Sauvegarde_Moyenne/Moyenne_Aleatoire.bin");
                DataInputStream dat1 = new DataInputStream (fic1);
              
                try
                {
                  this.moyenne_fic = dat1.readInt();
                }
                catch(IOException e)
                {
                  System.err.println("Erreur de lecture du fichier");
                }

                try
                {
                  dat1.close();
                }
                catch(IOException e)
                {
                  System.err.println("Erreur de fermeture du fichier.");
                }
              }
              catch(IOException e)
              {
                System.err.println("Erreur d'ouverture du fichier, ce fichier n'existe pas"); 
              }

            /*Moyenne des coups*/
              moyenne_alea = (etape + moyenne_fic) / 2;

            /*Sauvegarde la nouvelle moyenne*/
              try
              {
                FileOutputStream fic2 = new FileOutputStream ("./Sauvegarde_Moyenne/Moyenne_Aleatoire.bin");
                DataOutputStream dat2 = new DataOutputStream (fic2);

                try
                {
                  dat2.writeInt(moyenne_alea);
                }

                catch(IOException e)
                {
                  System.err.println("Erreur d'ecriture dans le fichier.");
                }

                try
                {
                  dat2.close();
                }
                catch(IOException e)
                {
                  System.err.println("Erreur de fermeture du fichier.");
                }
              }
              catch(IOException e)
              {
                System.err.println("Erreur dans l'ouverture du fichier.");
              }

            /*Message user*/
              JOptionPane assistoligne = new JOptionPane();
              if(fin == false)
              {
                assistoligne.showMessageDialog(null,"La simulation est termine, Thesee a du se deplacer" + " " + etape + " " + "fois pour sortir du labyrinthe. La moyenne de Thesee en mode aleatoire est de " + moyenne_alea , "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
              }
              else
              {
                assistoligne.showMessageDialog(null,"La simulation est termine, Thesee a du se deplacer" + " " + etape + " " + "fois pour sortir du labyrinthe. La moyenne de Thesee en mode aleatoire est de " + moyenne_alea , "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
              }
                /*System.out.println("Deplacement total : " + etape);*/
        }
    }

   /**
	* Methode de visualisation manuel de l'algorithme 
	*/
    public void deroulementManuel()
    {
        /*On initialise le tableau des cases visitees a 0 partout*/
          int[][] visitee = new int[this.cote + 2][this.cote + 2];
          for (i = 1; i <= this.cote; i++)
          {
            for (j = 1; j <= this.cote; j++) 
            {
              visitee[i][j] = 0;
            }
          }

          if (deter.isSelected()) 
          {
            /*System.out.println("DETER");*/
            Itineraire itineraire = new Itineraire(f1, typecase, visitee, i, j, i2, j2, this.cote, this.choixderoulement);
            EtapeSuivante etape_suivante = new EtapeSuivante(itineraire, deter, debile);
            f1.addKeyListener(etape_suivante);
          }

          else if (debile.isSelected()) 
          {
            /*System.out.println("DEBILE");*/
            Itineraire itineraire = new Itineraire(f1, typecase, visitee, i, j, i2, j2, this.cote, this.choixderoulement);
            EtapeSuivante etape_suivante = new EtapeSuivante(itineraire, deter, debile);
            f1.addKeyListener(etape_suivante);
          }
    } 
}