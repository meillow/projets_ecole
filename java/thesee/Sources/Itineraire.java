import javax.swing.*;
import java.util.*;

/**
* La classe <code>Itineraire</code> est une classe qui a pour but de permettre a l'utilisateur de se deplacer
* dans la grille ainsi que des changer les type de case de celle ci a l'aide des touches de son clavier.
* @version 3.0
* @author Jean-Baptiste SANCHEZ, Marie PELLIER
*/
public class Itineraire
{
	/**
	 * Entier representant la position i et j de la case et i2 et j2 dans certains cas. On a enfin la cote de notre carre, c'est a dire sa taille en nombre de case
	 * la variable choixderoulement permet de gerer le choix du deroulement fait par l'utilisateur. x et y nous permettent de gerer les coordonnees de thesee.
	 * La profondeur est un entier utilise pour calculer notre profondeur dans le graphe. Etape lui sert a compter le nombre de fois qu'on a lance un deplacement.
	 * x2 et y2 sont des sauvegardes de position.
	 */
	private int i, j, i2, j2, cote, choixderoulement, x, y, profondeur, etape, x2, y2;
	/**
	 * Fenetre heritant elle meme de JFrame c'est la fenetre sur laquelle on travaille.
	 */
	private JFrame f1;
	/**
	 * Tableau d'entier a deux dimensions representant tout les typecase de notre grille.
	 */
	private int[][] typecase;
	/**
	 * Tableau d'entier a deux dimensions representant le nombre de fois que l'on a visitees une des cases de notre grille.
	 */
  private int[][] visitee;
	/**
	 * Pile d'entier des positions y de Thesee.
	 */
  private Stack<Integer> positionY = new Stack<Integer>();
	/**
	 * Pile d'entier des positions x de Thesee.
	 */
  private Stack<Integer> positionX = new Stack<Integer>();
  /**
   * Boolean permettant de detecter si l'action precedenete est une action de retour en arriere.
   */
  private boolean retourarriere;

	/**
	 * Constructeur permettant de recuperer la fenetre sur lasquelle on travaille, le tableau des type cases de notre grille ainsi que celui des cases visitees.
	 * Mais aussi les positions i et j ou i2 et j2 de Thesee ainsi que le cote et le choix du deroulement de l'utilisateur.
     *
     * @param f fenetre principale (visible)
     * @param tc tableau des entiers types cases (int entre 0 et 11 pour chaque case)
     * @param v tableau des entiers correspondant aux nombres de visites de chaque case (0, 1 ,2)
     * @param origini position i / l'ordonnee (int entre 0 et 100)
     * @param originj position j / l'abcisse (int entre 0 et 100) 
     * @param origini2 position i2 / l'ordonnee (int entre 0 et 100)
     * @param originj2 position j2 / l'abcisse (int entre 0 et 100)
     * @param c taille du cote de la grille (int entre 0 et 100)
     * @param cd choix du deroulement de l'algorithme (1 ou 2) 
	 */
	public Itineraire(JFrame f, int[][] tc, int[][] v, int origini, int originj, int origini2, int originj2, int c, int cd)
	{
		this.f1 = f;
		this.typecase = tc;
		this.visitee = v;
		this.i = origini;
		this.j = originj;
		this.i2 = origini2;
		this.j2 = originj2;
		this.cote = c;
		this.choixderoulement = cd;
		this.profondeur = 0;
		this.etape = 0;
    	this.retourarriere = false;
	}

	/**
	 * Methode permettant de deplacer Thesee de maniere deterministe.
	 * @return un boolean permettant de savoir si Thesee est sorti ou bloque.
	 */
	public boolean deplacementTheseeDeterministe()
	{
      boolean fin = false;
      profondeur ++;
      etape ++;

  		/*On recupere la position de Thesee dans x et y*/
    		for (i = 1; i <= this.cote; i++)
        {
            for (j = 1; j <= this.cote; j++)
            {
            	if(typecase[i][j] == 9)
            	{
            		y = i;
            		x = j;
                /*System.out.println("Position de Thesee :");*/
                /*System.out.println("\ti / y = " + y + " | " + " j / x = " + x);*/
                /*System.out.println("Vision de Thesee :");*/
                /*System.out.println("\tNord : " + typecase[y - 1][x] + " | " + visitee[y - 1][x] + "\n\tEst : " + typecase[y][x + 1] + " | " + visitee[y][x + 1] + "\n\tSud : " + typecase[y + 1][x] + " | " + visitee[y + 1][x] + "\n\tOuest : " + typecase[y][x - 1] + " | " + visitee[y][x - 1]);*/

                /*On enregistre la position de thesee dans les piles seulement si nous sommes pas retourner en arriere*/
                  if (retourarriere == false)
                  {
                    positionY.push(y);
                    positionX.push(x);
                  }

                  /*System.out.println("Profondeur : " + profondeur);*/
            	}
            }
        }
        /*On met ou remet la variable de retour en arriere a false*/
          retourarriere = false;

          if (etape == 1)
          {
            this.y2 = positionY.peek();
            this.x2 = positionX.peek();
          }
          /*System.out.println("pileY : " + positionY.search(y2));*/
          /*System.out.println("pileX : " + positionX.search(x2));*/


      /*Si la case de sortie se trouve autour de thesee c'est gagne*/
        if((typecase[y - 1][x] == 10) || (typecase[y][x + 1] == 10) || (typecase[y + 1][x] == 10) || (typecase[y][x - 1] == 10))
        {
          /*System.out.println("Thesee est sorti");*/
          JOptionPane assistoligne = new JOptionPane();
          assistoligne.showMessageDialog(null,"Thesee est sorti, si vous etes en mode manuel appuyez sur une touche apres avoir clique sur ok pour afficher le resultat", "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
          fin = true;

          return fin;
        }

      /*Si les cases tout autour de Thesee sont des murs il n'y a aucune solution*/
        else if(
                  (typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) &&
                  (typecase[y][x + 1] == 0 || typecase[y][x + 1] == 6 || typecase[y][x + 1] == 7 || typecase[y][x + 1] == 8) &&
                  (typecase[y + 1][x] == 0 || typecase[y + 1][x] == 6 || typecase[y + 1][x] == 7 || typecase[y + 1][x] == 8) &&
                  (typecase[y][x - 1] == 0 || typecase[y][x - 1] == 6 || typecase[y][x - 1] == 7 || typecase[y][x - 1] == 8)
                )
        {
          /*System.out.println("Thesee est bloque.Il n'y a pas de solution a cette simulation");*/
          JOptionPane assistoligne = new JOptionPane();
          assistoligne.showMessageDialog(null,"Thesee est bloque, si vous etes en mode manuel appuyez sur une touche apres avoir clique sur ok pour afficher le resultat", "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
          fin = true;

          return fin;
        }

      /*Si la case au Nord est libre et qu'elle n'a jamais ete visitee on va au Nord*/
        else if((typecase[y - 1][x] == 1 || typecase[y - 1][x] == 2 || typecase[y - 1][x] == 3 || typecase[y - 1][x] == 4 || typecase[y - 1][x] == 5) && (visitee[y - 1][x] == 0))
        {
          /*La case ou se trouvais thesee devient une case libre*/
            typecase[y][x] = 1;

          /*Cette case est referencee comme visitee 1 fois*/
            visitee[y][x] = 1;

          /*Thesee se positionne au nord*/
            /*System.out.println("Je vais au Nord");*/
            typecase[y - 1][x] = 9;

            if(choixderoulement == 2)
            {
              DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
              f1.add(nouvelle_case_thesee);
            }

            return fin;
        }

  		/*Si la case au Nord est un mur ou qu'elle a deja ete visitee et que l'Est est libre on va a l'Est*/
    		else if(
                  (
                    ((typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) || (visitee[y - 1][x] == 1 || visitee[y - 1][x] == 2))
                  ) && ((typecase[y][x + 1] == 1 || typecase[y][x + 1] == 2 || typecase[y][x + 1] == 3 || typecase[y][x + 1] == 4 || typecase[y][x + 1] == 5) && (visitee[y][x + 1] == 0))
                )
    		{
          /*La case ou se trouvait Thesee devient une case libre. Cette case est referencee comme visitee 1 fois*/
            typecase[y][x] = 1;
            visitee[y][x] = 1;

    			/*Thesee se positionne a l'Est*/
            /*System.out.println("Je vais a l'Est");*/
            typecase[y][x + 1] = 9;

            if(choixderoulement == 2)
            {
              DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
              f1.add(nouvelle_case_thesee);
            }

            return fin;
    		}

  		/*Si la case au Nord et a l'Est sont des murs ou sont des cases deja visitee 1 ou 2 fois et que le Sud est libre on va au Sud*/
    		else if(
                  (
                    ((typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) || (visitee[y - 1][x] == 1 || visitee[y - 1][x] == 2)) ||
                    ((typecase[y][x + 1] == 0 || typecase[y][x + 1] == 6 || typecase[y][x + 1] == 7 || typecase[y][x + 1] == 8) || (visitee[y][x + 1] == 1 || visitee[y][x + 1] == 2))
                  ) && ((typecase[y + 1][x] == 1 || typecase[y + 1][x] == 2 || typecase[y + 1][x] == 3 || typecase[y + 1][x] == 4 || typecase[y + 1][x] == 5) && (visitee[y + 1][x] == 0))
                )
    		{
          /*La case ou se trouvait Thesee devient une case libre. Cette case est referencee comme visitee 1 fois*/
            typecase[y][x] = 1;
            visitee[y][x] = 1;

    			/*Thesee se positionne au Sud*/
            /*System.out.println("Je vais au Sud");*/
            typecase[y + 1][x] = 9;

            if(choixderoulement == 2)
            {
              DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
              f1.add(nouvelle_case_thesee);
            }

            return fin;
    		}

  		/*Si la case au Nord, a l'Est et au Sud sont des murs ou sont des cases deja visitee 1 ou 2 fois et que l'Ouest est libre on va a l'Ouest*/
    		else if(
                  (
                    ((typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) || (visitee[y - 1][x] == 1 || visitee[y - 1][x] == 2)) ||
                    ((typecase[y][x + 1] == 0 || typecase[y][x + 1] == 6 || typecase[y][x + 1] == 7 || typecase[y][x + 1] == 8) || (visitee[y][x + 1] == 1 || visitee[y][x + 1] == 2)) ||
                    ((typecase[y + 1][x] == 0 || typecase[y + 1][x] == 6 || typecase[y + 1][x] == 7 || typecase[y + 1][x] == 8) || (visitee[y + 1][x] == 1 || visitee[y + 1][x] == 2))
                  ) && ((typecase[y][x - 1] == 1 || typecase[y][x - 1] == 2 || typecase[y][x - 1] == 3 || typecase[y][x - 1] == 4 || typecase[y][x - 1] == 5) && (visitee[y][x - 1] == 0))
                )
    		{
          /*La case ou se trouvait Thesee devient une case libre. Cette case est referencee comme visitee 1 fois*/
            typecase[y][x] = 1;
            visitee[y][x] = 1;

    			/*Thesee se positionne a l'Ouest*/
            /*System.out.println("Je vais a l'Ouest");*/
      			typecase[y][x - 1] = 9;

            if(choixderoulement == 2)
            {
              DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
              f1.add(nouvelle_case_thesee);
            }

            return fin;
    		}

      /*Si les cases tout autour de Thesee sont des murs ou sont visitee 2 fois on annonce qu'il n'y a aucune solution*/
        else if(
                  ((typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) || (visitee[y - 1][x] == 2)) &&
                  ((typecase[y][x + 1] == 0 || typecase[y][x + 1] == 6 || typecase[y][x + 1] == 7 || typecase[y][x + 1] == 8) || (visitee[y][x + 1] == 2)) &&
                  ((typecase[y + 1][x] == 0 || typecase[y + 1][x] == 6 || typecase[y + 1][x] == 7 || typecase[y + 1][x] == 8) || (visitee[y + 1][x] == 2)) &&
                  ((typecase[y][x - 1] == 0 || typecase[y][x - 1] == 6 || typecase[y][x - 1] == 7 || typecase[y][x - 1] == 8) || (visitee[y][x - 1] == 2))
                )
        {
          /*System.out.println("Thesee est bloque. Il n'y a pas de solution a cette simulation");*/
          JOptionPane assistoligne = new JOptionPane();
          assistoligne.showMessageDialog(null,"Thesee est bloque, si vous etes en mode manuel appuyez sur une touche apres avoir clique sur ok pour afficher le resultat", "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
          fin = true;

          return fin;
        }

  		/*Si les cases tout autour de Thesee sont des murs ou sont visitee 1 fois on retourne a la position precedente*/
    		else if(
                  ((typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) || (visitee[y - 1][x] == 1)) ||
                  ((typecase[y][x + 1] == 0 || typecase[y][x + 1] == 6 || typecase[y][x + 1] == 7 || typecase[y][x + 1] == 8) || (visitee[y][x + 1] == 1)) ||
                  ((typecase[y + 1][x] == 0 || typecase[y + 1][x] == 6 || typecase[y + 1][x] == 7 || typecase[y + 1][x] == 8) || (visitee[y + 1][x] == 1)) ||
                  ((typecase[y][x - 1] == 0 || typecase[y][x - 1] == 6 || typecase[y][x - 1] == 7 || typecase[y][x - 1] == 8) || (visitee[y][x - 1] == 1))
                )
    		{
    			/*La case ou se trouvait Thesee devient une case libre. Cette case est referencee comme visitee 2 fois*/
    			 typecase[y][x] = 1;
    			 visitee[y][x] = 2;

    			/*Thesee retourne a la position precedente*/
            /*System.out.println("Je retourne en arriere");*/
            retourarriere = true;
            profondeur = profondeur - 2;
            y = positionY.pop();
            x = positionX.pop();
            y = positionY.peek();
            x = positionX.peek();

            typecase[y][x] = 9;

            if(choixderoulement == 2)
            {
              DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
              f1.add(nouvelle_case_thesee);
            }

            return fin;
    		}
      return fin;
  }

	/**
	 * Methode permettant de deplacer Thesee de maniere aleatoire.
	 * @return un boolean permettant de savoir si Thesee est sorti ou bloque.
	 */
  public boolean deplacementTheseeAleatoire()
  {
    boolean fin = false;
    boolean thesee_est_sortie = true;
    boolean directioncorrect = false;

    /*On recupere la position de Thesee dans x et y*/
      for (i = 1; i <= this.cote; i++)
      {
        for (j = 1; j <= this.cote; j++)
        {
          if(typecase[i][j] == 9)
          {
            y = i;
            x = j;
            /*System.out.println("Position Thesee");*/
            /*System.out.println("\ti / y = " + y + " | " + " j / x = " + x);*/
          }
        }
      }

    /*On verifie si la sortie existe*/
      for (i = 1; i <= this.cote; i++)
      {
        for (j = 1; j <= this.cote; j++)
        {
          if(typecase[i][j] == 10)
          {
            thesee_est_sortie = false;
          }
        }
      }

    /*Gestion des directions*/

    /*Si les cases tout autour de Thesee sont des murs il n'y a aucune solution*/
      if(
          (typecase[y - 1][x] == 0 || typecase[y - 1][x] == 6 || typecase[y - 1][x] == 7 || typecase[y - 1][x] == 8) &&
          (typecase[y][x + 1] == 0 || typecase[y][x + 1] == 6 || typecase[y][x + 1] == 7 || typecase[y][x + 1] == 8) &&
          (typecase[y + 1][x] == 0 || typecase[y + 1][x] == 6 || typecase[y + 1][x] == 7 || typecase[y + 1][x] == 8) &&
          (typecase[y][x - 1] == 0 || typecase[y][x - 1] == 6 || typecase[y][x - 1] == 7 || typecase[y][x - 1] == 8)
        )
      {
        /*System.out.println("Thesee est bloque");*/
        JOptionPane assistoligne = new JOptionPane();
        assistoligne.showMessageDialog(null,"Thesee est bloque, si vous etes en mode manuel appuyez sur une touche apres avoir clique sur ok pour afficher le resultat", "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
        fin = true;

        return fin;
      }

    /*Si Thesee est sorti c'est gagne*/
      else if (thesee_est_sortie == true)
      {
          /*System.out.println("Thesee est sorti");*/
          JOptionPane assistoligne = new JOptionPane();
          assistoligne.showMessageDialog(null,"Thesee est sorti, si vous etes en mode manuel appuyez sur une touche apres avoir clique sur ok pour afficher le resultat", "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
          fin = true;

          return fin;
      }

    /*Sinon si la sortie existe, Thesee n'est donc pas sorti il doit prendre une direction au hasard*/
      else
      {
        while(directioncorrect == false)
        {
          Random rand = new Random();
          int direction = rand.nextInt((4 - 1) + 1) + 1;;
          /*System.out.println(direction);*/

          if(direction == 1 && (typecase[y - 1][x] == 1 || typecase[y - 1][x] == 2 || typecase[y - 1][x] == 3 || typecase[y - 1][x] == 4 || typecase[y - 1][x] == 5 || typecase[y - 1][x] == 10))
          {
            /*La case ou se trouvais thesee devient une case libre*/
              typecase[y][x] = 1;

            /*Thesee se positionne au Nord*/
              /*System.out.println("Je vais au Nord");*/
              typecase[y - 1][x] = 9;

              if(choixderoulement == 2)
              {
                DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
                f1.add(nouvelle_case_thesee);
              }

              directioncorrect = true;
          }

          else if(direction == 2 && (typecase[y][x + 1] == 1 || typecase[y][x + 1] == 2 || typecase[y][x + 1] == 3 || typecase[y][x + 1] == 4 || typecase[y][x + 1] == 5 || typecase[y][x + 1] == 10))
          {
            /*La case ou se trouvait Thesee devient une case libre*/
              typecase[y][x] = 1;

            /*Thesee se positionne a l'Est*/
              /*System.out.println("Je vais a l'Est");*/
              typecase[y][x + 1] = 9;

              if(choixderoulement == 2)
              {
                DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
                f1.add(nouvelle_case_thesee);
              }

              directioncorrect = true;
          }

          else if(direction == 3 && (typecase[y + 1][x] == 1 || typecase[y + 1][x] == 2 || typecase[y + 1][x] == 3 || typecase[y + 1][x] == 4 || typecase[y + 1][x] == 5 || typecase[y + 1][x] == 10))
          {
            /*La case ou se trouvait Thesee devient une case libre*/
              typecase[y][x] = 1;

            /*Thesee se positionne au Sud*/
              /*System.out.println("Je vais au Sud");*/
              typecase[y + 1][x] = 9;

              if(choixderoulement == 2)
              {
                DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
                f1.add(nouvelle_case_thesee);
              }

              directioncorrect = true;
          }

          else if(direction == 4 && (typecase[y][x - 1] == 1 || typecase[y][x - 1] == 2 || typecase[y][x - 1] == 3 || typecase[y][x - 1] == 4 || typecase[y][x - 1] == 5 || typecase[y][x - 1] == 10))
          {
            /*La case ou se trouvait Thesee devient une case libre*/
              typecase[y][x] = 1;

            /*Thesee se positionne a l'Ouest*/
              /*System.out.println("Je vais a l'Ouest");*/ 
              typecase[y][x - 1] = 9;

              if(choixderoulement == 2)
              {
                DessinCase nouvelle_case_thesee = new DessinCase(typecase, i, j, i2, j2);
                f1.add(nouvelle_case_thesee);
              }

              directioncorrect = true;
          }
        }
        return fin;
      }
  }
}