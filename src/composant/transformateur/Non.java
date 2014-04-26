package composant.transformateur;


/**
 * 
 * Classe modelisant un non
 *
 */
public class Non extends $Transformateur {

	/**
	 * initialise les attributs in et out
	 */
	public Non(String nom, int idComposant){ 
		super(nom,idComposant,1,1);
	}

	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void calcul(){
		this.value = niveauFromBool( ! arrayEntrees.get(0).getValue()); 
	}

}