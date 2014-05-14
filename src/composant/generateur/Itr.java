package composant.generateur;

import composant.generateur.$Generateur;
import jus.util.assertion.Require;

/**
 * 
 * Classe Itr modelise un interupteur
 *
 */
public class Itr extends $Generateur {
	
	
	/**
	 * initialise les attributs in et out
	 */
	public Itr(String nom, int idComposant, niveau etat){ 
		super(nom,idComposant,1);
		this.value = etat;
	}

	/**
	 * initialise avec le nom, le numero, et l'état sous forme de string
	 * @require : Etat.equals("bas") || Etat.equals("haut")
	 */
	public Itr(String nom, int idComposant, String etat){ 
		super(nom,idComposant,1);
		if (etat.equals("haut"))
			setNiveau(niveau.Haut);
		else if (etat.equals("bas"))
			setNiveau(niveau.Bas);
		else
			throw new Require("Etat n'est pas valide");
	}
	
	/**
	 * @param niveau = {Bas,Haut}
	 * ensure modifie le niveau du generateur : 
	 */
	public void setNiveau(niveau etat) {
		this.value = etat;
		this.spreadNiveau();
	}
	
	public String infoComplementaire()
	{
		return "{" + value.toString() + "}";
	}
}