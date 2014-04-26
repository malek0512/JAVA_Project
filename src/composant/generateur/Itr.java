package composant.generateur;

import composant.generateur.$Generateur;


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
	 * @param niveau = {Bas,Haut}
	 * ensure modifie le niveau du generateur : 
	 */
	public void setNiveau(niveau etat) {
		this.value = etat;
		this.spreadNiveau();
	}
	
}