package port;

import composant.$Composant;

/*
attribut :
bool valide
methode :


 */
public class In extends $Port {

	private $Composant A;
	
	/* Constructeurs */
	public In($Composant A) {
		super();
		this.A = A;
	}

	/**
	 * renvoie le composant au quel est liée le port
	 * @return le composant liée du port
	 */
	public $Composant getComposant() {
		return A;
	}
	
	/**
	 * renvoie les informations du port in
	 */
	public String toString() {
		return "PortE n°" + Numero + " est " + Valide + " a l'etat " + Value;
	}
	
	/**
	 * renvoie les informations du port selon la grammaire 
	 */
	public String toString2() //y a n'y a rien a decrire au niveau des ports d'entrée 
	{
		return "";
	}
}
