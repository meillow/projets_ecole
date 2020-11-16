import java.io.*;
/**
 * La classe <code>EcrireBit</code> est utilisee lors de la sauvegarde afin d'ecrire bit a bit dans le fichier de sauvegarde.
 * @version 3.0
 * @author Jean-Baptiste SANCHEZ, Marie PELLIER
 */

public class EcrireBit extends FilterOutputStream 
{
	/**
	 * entiers qui vont permettre de situer ou se trouve l'information codee dans le fichier 
	 */
	int bits, offset;
	  
	/**
	 * va ecrire les bit specifies dans le fichier specifie
	 *
	 * @param out flux d'octets de sortie (int)
	 */
	public EcrireBit(OutputStream out) 
	{
		super(out);
	}
	/**
	 * va permettre d'ecrire un a un les bit et non par octet
	 *
	 * @param bit entier correspondant au bit (int)
	 */
	public void writeBit(int bit) throws IOException 
	  {
		/**
		 * Decale le bit sur la gauche de 1 si il y a rien d'ecrit pour le moment.
		 */
		if (bit==0) 
		{
		  bits<<=1;
		}
		/**
		 * Sinon decale les bits presents et le nouveau bit vers la gauche de 1 
		 */
		else 
		{
		  bits=bits<<1|1;
		}
		offset++;
		/**
		 * quand il y a 8 bit d'affile, remet le compteur a zero et permet de continuer a remplir bit a bit
		 */
	    if (offset==8) 
	    {
	      write(bits);
	      bits=0;
	      offset=0;
	    }
	  }
	  /**
	   * Vide les buffers d'ecriture vers le fichier de sortie.
	   */	  
	  public void flush() throws IOException 
	  {

	    if (offset!=0)
	    {
	      write(bits<<(8-offset));
	      offset=0;
	    }
	    super.flush();
	  }
	/**
	 * Ferme le flux ouvert.
	 */
	 public void close() throws IOException 
	 {
		flush();
		super.close();
	 }
}