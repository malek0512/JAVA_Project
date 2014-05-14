package composant.transformateur;
/**
 * Classe modelisant un Non Et logique
 */
public class Nand3b extends $Transformateur {

	public Nand3b(String nom, int idComposant) {
		super(nom,idComposant,3,1);
	}

		
	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void calcul(){
		this.value = niveauFromBool( ! (arrayEntrees.get(0).getValue() && arrayEntrees.get(1).getValue() && arrayEntrees.get(2).getValue())); 
	}

}
