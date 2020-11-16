import javax.swing.*;
import java.awt.*;
/**
 * La classe <code>Fenetre</code> definit les parametres initiaux de la fenetre principale.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */
public abstract class FenetreOrigine extends JFrame
{
	/**
	 * va renvoye les specifite de la fenetre principale comme sa position, sa taille, son nom...
	 */
	public FenetreOrigine()
	{      
		super();
		this.setTitle("Thesee et le Minotaure");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);      
		this.getContentPane().setLayout(new FlowLayout());
		this.setVisible(true);
	}
}
