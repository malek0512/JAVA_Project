package composant.transformateur;

import composant.$Composant;

/*
 au moins un port dans chaque liste
 */

public abstract class $Transformateur extends $Composant {

	/**
	 * Constructeur d'un transformateur
	 * 
	 * @ensure les ports du composant ne seront pas connectés
	 * @param nom
	 *            nom du composant à creer
	 * @param idComposant
	 *            identifiant du composant à creer
	 * @param nbEntreeMax
	 *            nombre d'entrees du composant
	 * @param nbSortieMax
	 *            nombre de sorties du composant
	 * 
	 */
	public $Transformateur(String nom, int idComposant, int nbEntreeMax, int nbSortieMax) {
		super(nom,idComposant,nbEntreeMax,nbSortieMax);
	}

	/**
	 * la fonction de calcul booléenne 
	 * @ensure value = Bas ou Haut selon les entrees
	 */
	public abstract void calcul();
	
	public void execute() {
		calcul();
		spreadNiveau();
		if(estExecutable()){
			for(int i=0;i<nbSorties();i++)
				this.sortieList().get(0).connectall();
		}
	}
	public String toString(){
		return "Transformateur " + Nom + " N° " + Numero + " est au niveau " + value.toString();
	}
	
	/**
	 * @author Alex
	 */
	public String toString2(){
		String res = new String("<" + Numero + "|" + Nom + "(" + nbEntreeMax + "," + nbSortieMax + ")" + infoComplementaire() + "->");
		res = res.concat(arraySorties.get(0).toString2());
		for (int i=1; i<arraySorties.size(); i++)
		{
			res = res.concat("\n\t->"+ arraySorties.get(i).toString2());
		}
		res = res.concat(">");
		return res;
	}
}
