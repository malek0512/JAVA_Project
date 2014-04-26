package composant.transformateur;


/**
 * Classe modelisant un Et logique
 */
public class Et extends $Transformateur {
	
/**
 * initialise les attributs in et out
 */
  public Et(String nom, int idComposant){ 
		super(nom,idComposant,2,1);
  }
	
	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void calcul(){
		this.value = niveauFromBool(arrayEntrees.get(0).getValue() && arrayEntrees.get(1).getValue()); 
	}

}