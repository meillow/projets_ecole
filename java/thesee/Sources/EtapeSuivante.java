import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 * La classe <code>EtapeSuivante</code> est utilisee en tant qu'observateur des touches appuyer sur le clavier avec l'interface KeyListener afin de
 * detecter la touche de passage a l'etape suivante de l'algorithme en mode manuelle.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public class EtapeSuivante implements KeyListener
{
	/**
	 * Entier representant le nombre d'etape, c'est a dire compter le nombre de fois qu'on a lance un deplacement.
	 * Les entiers moyenne_fic, moyenne_deter et moyenne_alea permettent de gerer respectivement la moyenne lue dans le fichier correspondant.
	 * la moyenne a enreigistrer dans le fichier de sauvegarde pour l'algorithme deterministe et idem pour l'algorithme aleatoire
	 */
	private int etape, moyenne_fic, moyenne_deter, moyenne_alea;
	/**
	 * Objet de la classe Itineraire qui va gerer les deplacements.
	 */
	private Itineraire itineraire;
	/**
	 * JRadioButton representant et recuperant les valeurs respectives du radio button deterministe et random du menu dans le choix de l'algorithme.
	 */
	private JRadioButton deter, debile;
	/**
	 * Boolean permettant de representer le moment ou la simulation est finie ou non.
	 */
	private boolean fin;

	/**
	 * Constructeur permettant de recuperer l'itineraire c'est a dire l'objet de la classe qui va gerer les deplacements.
	 * On recupere aussi Les deux radio buttons du formulaire qui nous permet de savoir quel algorithme l'utilisateurs a t-il selectionne.
     *
     * @param i objet de la classe itineraire correspondant a l'instruction de deplacement
     * @param dt bouton radio selection algorithme deterministe (true ou false)
     * @param db bouton radio selection algorithme debile (true ou false)
	 */
	public EtapeSuivante(Itineraire i, JRadioButton dt, JRadioButton db)
	{
		super();
        this.itineraire = i;
        this.deter = dt;
        this.debile = db;

        this.etape = 0;
        this.moyenne_fic = 0;
        this.moyenne_alea = 0;
        this.moyenne_deter = 0;
        this.fin = false;
	}

	/**
	 * Reecriture de la methode keyReleased dans le but que lorsque on appuie sur une touche et qu'on la relache cela execute l'action souhaite.
	 * On s'en servira alors pour dispose plusieurs touches pour pouvoir passer les etapes de la simulation via l'appuie et la relache d'une touche.
	 */
	@Override
	public void keyReleased(KeyEvent evenement)
	{
        this.etape++;
        int touche = evenement.getKeyCode();

        if (fin == true)
        {
            etape --;
            /*System.out.println("FIN");*/
            if (deter.isSelected())
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
                  /*System.out.println("Deplacement total : " + etape);*/
                  JOptionPane assistoligne = new JOptionPane();
                  assistoligne.showMessageDialog(null,"La simulation est terminee, Thesee a du se deplacer" + " " + etape + " " + "fois pour essayer de sortir du labyrinthe. La moyenne de Thesee en mode deterministe est de " + moyenne_deter , "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
                  System.exit(0);
            }
            else if (debile.isSelected())
            {
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
                    /*System.out.println("Deplacement total : " + etape);*/
                    JOptionPane assistoligne = new JOptionPane();
                    assistoligne.showMessageDialog(null,"La simulation est terminee, Thesee a du se deplacer" + " " + etape + " " + "fois pour essayer de sortir du labyrinthe. La moyenne de Thesee en mode aleatoire est de " + moyenne_alea , "Assisto-ligne", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
            }
        }

        else if (deter.isSelected())
		{
            /*System.out.println("Etape : " + etape);*/
            fin = itineraire.deplacementTheseeDeterministe();
		}

        else if (debile.isSelected())
        {
            /*System.out.println("Etape : " + etape);*/
            fin = itineraire.deplacementTheseeAleatoire();
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