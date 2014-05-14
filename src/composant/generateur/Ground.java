package composant.generateur;
import java.util.Iterator;

/**
 * 
 * Classe Ground 
 *
 */
public class Ground extends $Generateur {
	
	/**
	 * initialise les attributs in et out
	 */
	public Ground(String nom, int idComposant){ 
		super(nom,idComposant,1);
		value = niveau.Bas;
		spreadNiveau();
	}



}