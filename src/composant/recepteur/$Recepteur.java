package composant.recepteur;

import composant.$Composant;


/*
 au moins un port dans liste de entre
 aucun port dans liste sortie
 */
public abstract class $Recepteur extends $Composant {

	/**
	 * Constructeur d'un recepteur
	 * 
	 * @ensure les ports du composant ne seront pas connectés
	 * @param nom
	 *            nom du composant à creer
	 * @param idComposant
	 *            identifiant du composant à creer
	 * @param nbEnt
	 *            nombre d'entrees du composant
	 * @param nbEntreeMax 
	 * 
	 */
	public $Recepteur(String nom, int idComposant, int nbEntreeMax) {
		super(nom,idComposant,nbEntreeMax,0);
		
	}

	public String toString(){
		return "Recepteur " + Nom + "N°" + Numero;
	}
	
	/**
	 * @author Alex
	 */
	public String toString2(){
		return "<" + Numero + "|" + Nom + "(" + nbEntreeMax + ",0)" + infoComplementaire() + ">";
	}

}