package composant.transformateur;


/**
 * 
 * Classe modelisant un Ou 
 *
 */
public class Ou extends $Transformateur {
	

  public Ou(String nom, int idComposant){ 
	  super(nom,idComposant,2,1);
  }

	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void calcul(){
		this.value = niveauFromBool(arrayEntrees.get(0).getValue() || arrayEntrees.get(1).getValue()); 
	}

}