import javax.swing.*;
import java.awt.event.*;

/**
 * La classe <code>ConfigurationCase</code> est une classe observateur avec l'interface KeyListener, elle a pour but de permettre a
 * l'utilisateur de se deplacer dans la grille ainsi que des changer les type de case de celle ci a l'aide des touches de son clavier
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class ConfigurationCase implements KeyListener
{
	/**
	 * Entier representant la position i et j de la case et i2 et j2 dans certains cas. On a enfin la cote de notre carre, c'est a dire sa taille en nombre de case.
	 * Type nous permet d'enregistrer le type de case choisie par l'utilisateur. L'entier thesee et sortie permetttent de verifier combien il y a de thesee et de sortie.
	 * Enfin la variable choixderoulement permet de gerer le choix du deroulement fait par l'utilisateur.
	 */
	private int i, j, i2, j2, cote, type, thesee, sortie, choixderoulement;
	/**
	 * JRadioButton representant et recuperant les valeurs respectives du radio button deterministe et random du menu dans le choix de l'algorithme.
	 */
	private JRadioButton deter, debile;
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
	 * Mais aussi les positions i et j ou i2 et j2 de Thesee ainsi que le cote de la grille et le choix de l'algorithme avec les deux radio button.
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
	 */
	public ConfigurationCase(JFrame f, int[][] tc, int origini, int originj, int origini2, int originj2, int c, JRadioButton dt, JRadioButton db)
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

		this.choixderoulement = 0;
		this.type = 1;

		this.i = 1;
		this.j = 1;
	}

	/**
	 * Reecriture de la methode keyReleased dans le but que lorsque on appuie sur une touche et qu'on la relache cela execute l'action souhaite.
	 * On s'en servira alors pour dispose plusieurs touches pour pouvoir changer les types des cases et se deplacer dans la grille.
	 */
	@Override
	public void keyReleased(KeyEvent evenement)
	{
		int touche = evenement.getKeyCode();
		/*System.out.println(i + " | " + j);*/

		if (touche == KeyEvent.VK_F1)
		{
			/*System.out.println("Chemin");*/
			type = 1;
			typecase[i][j] = 1;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

        else if (touche == KeyEvent.VK_F2)
		{
			/*System.out.println("Mur");*/
			type = 6;
			typecase[i][j] = 6;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

		else if (touche == KeyEvent.VK_F3)
		{
			/*System.out.println("Thesee");*/
			type = 9;
			typecase[i][j] = 9;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

		else if (touche == KeyEvent.VK_F4)
		{
			/*System.out.println("sortie");*/
			type = 10;
			typecase[i][j] = 10;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

		else if (touche == KeyEvent.VK_UP)
		{
			if (type != 9 && type != 10)
			{
				typecase[i][j] = type;
				f1.add(new DessinCase(typecase, i, j, i2, j2));
			}
			else
			{
				type = 1;
			}

			/*System.out.println("Case suivante");*/
			i--;

			if (i < 1)
			{
				i = this.cote;
			}

			if (i > this.cote)
			{
				i = 1;
			}

			typecase[i][j] = 11;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

		else if (touche == KeyEvent.VK_RIGHT)
		{
			if (type != 9 && type != 10)
			{
				typecase[i][j] = type;
				f1.add(new DessinCase(typecase, i, j, i2, j2));
			}
			else
			{
				type = 1;
			}

		    /*System.out.println("Case suivante");*/
		    j++;

		    if (j < 1)
			{
				j = this.cote;
			}

			if (j > this.cote)
			{
				j = 1;
			}

			typecase[i][j] = 11;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

		else if (touche == KeyEvent.VK_DOWN)
		{
			if (type != 9 && type != 10)
			{
				typecase[i][j] = type;
				f1.add(new DessinCase(typecase, i, j, i2, j2));
			}
			else
			{
				type = 1;
			}

		    /*System.out.println("Case suivante");*/
		    i++;

		    if (i < 1)
			{
				i = this.cote;
			}

			if (i > this.cote)
			{
				i = 1;
			}

			typecase[i][j] = 11;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

		else if (touche == KeyEvent.VK_LEFT)
		{
			if (type != 9 && type != 10)
			{
				typecase[i][j] = type;
				f1.add(new DessinCase(typecase, i, j, i2, j2));
			}
			else
			{
				type = 1;
			}

		    /*System.out.println("Case suivante");*/
		    j--;

		    if (j < 1)
			{
				j = this.cote;
			}

			if (j > this.cote)
			{
				j = 1;
			}

			typecase[i][j] = 11;
			f1.add(new DessinCase(typecase, i, j, i2, j2));
		}

    else if (touche == KeyEvent.VK_ESCAPE)
    {
    	typecase[i][j] = type;
		f1.add(new DessinCase(typecase, i, j, i2, j2));

			/*On compte le nombre de Thesee et de sortie*/
			  for (i = 1; i <= this.cote; i++)
			  {
			    for (j = 1; j <= this.cote; j++)
			    {
			    	if (typecase[i][j] == 9)
					{
						thesee++;
					}
					if (typecase[i][j] == 10)
					{
						sortie++;
					}
			    }
			  }

			if (thesee == 1 && sortie == 1)
			{
				/*System.out.println("Fin Configuration Case");*/

				String[] typederoulements = new String[]{"Automatique","Manuel"};
				String typederoulement = (String)JOptionPane.showInputDialog(null,"Choississez un mode de visualisation", "Visualisation", JOptionPane.QUESTION_MESSAGE, null, typederoulements, typederoulements[0]);

	            if("Automatique".equals(typederoulement))
	            {
	                choixderoulement = 1;
	            }
	            else if ("Manuel".equals(typederoulement))
	            {
	                choixderoulement = 2;
	            }

				new Deroulement(f1, typecase, i, j, i2, j2, this.cote, deter, debile, choixderoulement);
				f1.removeKeyListener(f1.getKeyListeners()[0]);
			}
			else if (thesee != 1 || sortie != 1)
			{
				/*System.out.println(thesee + " " + sortie);*/
				System.err.println("Il ne doit y avoir qu'un seul Thesee et qu'une seule sortie");
				JOptionPane erreur1 = new JOptionPane();
            	erreur1.showMessageDialog(null,"WOW ! Erreur il ne doit y avoir qu'un seul Thesee et qu'une seule sortie", "Erreur", JOptionPane.ERROR_MESSAGE);
            	thesee = 0;
            	sortie = 0;
				i = 1;
				j = 1;
			}
			else
			{
				System.err.println("Il ne doit y avoir qu'un seul Thesee et qu'une seule sortie");
				JOptionPane erreur2 = new JOptionPane();
            	erreur2.showMessageDialog(null,"WOW ! Erreur il ne doit y avoir qu'un seul Thesee et qu'une seule sortie", "Erreur", JOptionPane.ERROR_MESSAGE);
            	thesee = 0;
            	sortie = 0;
				i = 1;
				j = 1;
			}
    }
	}

	/**
	 * Autre methode de la classe KeyListener permettant de gerer si on presse, que l'on appuie sur une touche de notre clavier.
	 */
	public void keyPressed(KeyEvent evenement){}
	/**
	 * Autre methode de la classe KeyListener permettant de gerer lorsque le caractere unicode represente par cette touche est envoye par le clavier a l'entree systeme.
	 */
	public void keyTyped(KeyEvent evenement){}
}