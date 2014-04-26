package composant.generateur;

import composant.$Composant;


/*
 juste a implementer l'interface
 et ajouter le constructeur correctement

 invariant :
 a au moins un port de sortie //verifier le sujet si il n'y aurais pas plutot qu'un seul port de sortie 
 (rappel : ca empeche pas d'avoir plein de composant connecter)
 n'a aucun port d'entre

 */
public abstract class $Generateur extends $Composant {

	/**
	 * Constructeur d'un generateur
	 * 
	 * @ensure les ports du composant ne seront pas connectés
	 * @param nom
	 *            du composant à creer
	 * @param idComposant
	 *            identifiant du composant à creer
	 * @param nbSort
	 *            nombre de sorties du composant
	 * 
	 */
	public $Generateur(String nom, int idComposant, int nbSortieMax) {
		super(nom,idComposant,0,nbSortieMax);
	}

	/**
	* Propage la valeur du niveau (du PortS) aux composants qui lui sont reliés
	*/
	public void execute(){
		spreadNiveau();
		if(estExecutable()){
			for(int i=0;i<nbSorties();i++)
				this.sortieList().get(0).connectall();
		}
	}

	public String toString() {
		return "Generateur " + Nom + " N° " + Numero + " a l'etat " + getNiveau().toString();
	}
}