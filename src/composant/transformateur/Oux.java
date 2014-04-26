package composant.transformateur;

public class Oux extends $Transformateur {

	public Oux(String nom, int idComposant) {
		super(nom, idComposant,2,1);
	}

	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void calcul() {
		boolean entree1 = this.entreeList().get(0).getValue();
		boolean entree2 = this.entreeList().get(1).getValue();
		if (entree1 && entree2)
			this.value = niveauFromBool(false);
		else
			this.value = niveauFromBool(entree1 || entree2);
	}

}
