import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * La classe <code>DessinCase</code> est utilisee pour creer les composants graphique representant les differents types de cases.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class DessinCase extends JComponent
{
	/**
	 * Entier representant la position i et j de la case ou i2 et j2 dans certains cas.
	 */
	private int i, j, i2, j2;
	/**
	 * Tableau d'entier a deux dimensions representant en fonction de la position le type de la case correspondant.
	 */
	private int[][] typecase;

	/**
	 * Constructeur permettant simplement de recuperer le type de la case que l'on veut creer, la position ou on doit creer le composant ainsi que la taille de notre grille.
     *
     * @param tc tableau des entiers types cases (int entre 0 et 11 pour chaque case)
     * @param origini position i / l'ordonnee (int entre 0 et 100)
     * @param originj position j / l'abcisse (int entre 0 et 100) 
     * @param origini2 position i2 / l'ordonnee (int entre 0 et 100)
     * @param originj2 position j2 / l'abcisse (int entre 0 et 100)
	 */
	public DessinCase(int[][] tc, int origini, int originj, int origini2, int originj2)
	{
		this.typecase = tc;
		this.i = origini;
		this.j = originj;
		this.i2 = origini2;
		this.j2 = originj2;
	}

  /**
   * Reecriture de la methode paintComponent dans le but de creer nos composant graphique et les mettres a jour.
   */
	@Override
  	public void paintComponent(Graphics pinceau)
  	{
		/*On cree un nouveau pinceau pour pouvoir le modifier plus tard*/
    		Graphics monPinceau = pinceau.create();

		/*Si le composant n'est pas cense etre transparent*/
    		if (this.isOpaque())
    		{
      			/*On repeint toute la surface avec la couleur de fond*/
      			monPinceau.setColor(this.getBackground());
      			monPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    		}

		/*Maintenant on fait ce que l'on veut*/
			monPinceau.setColor(this.getForeground());

    /*Creation d'une case chemin*/
			if (typecase[i][j] == 1 || typecase[i][j] == 2 || typecase[i][j] == 3 || typecase[i][j] == 4 || typecase[i][j] == 5)
			{
			    monPinceau.setColor(Color.BLACK);
				monPinceau.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
				this.repaint();
				this.setOpaque(false);
			}

    /*Creation d'une case mur ou frontiere si c'est 0*/
			else if (typecase[i][j] == 0 || typecase[i][j] == 6 || typecase[i][j] == 7 || typecase[i][j] == 8)
			{
				if (typecase[i][j] == 0)
				{
					monPinceau.setColor(Color.WHITE);
				}
				else
				{
					monPinceau.setColor(Color.BLACK);
				}
				monPinceau.fillRect(0,0,this.getWidth(),this.getHeight());
				this.repaint();
				this.setOpaque(false);
			}

    /*Creation de Thesee*/
			else if(typecase[i][j] == 9)
			{
				monPinceau.setColor(Color.YELLOW);
				monPinceau.fillRect(0,0,this.getWidth(),this.getHeight());
				this.repaint();
				this.setOpaque(false);
			}

    /*Creation de la sortie*/
			else if(typecase[i2][j2] == 10 && typecase[i][j] != 11)
			{
				monPinceau.setColor(Color.RED);
				monPinceau.fillRect(0,0,this.getWidth(),this.getHeight());
				this.repaint();
				this.setOpaque(false);
			}
		/*Curseur*/
			else if(typecase[i][j] == 11)
			{
				monPinceau.setColor(Color.BLUE);
				monPinceau.fillRect(35, 35, this.getWidth() - 70, this.getHeight() - 70);
				this.repaint();
				this.setOpaque(false);
			}
	}
}
