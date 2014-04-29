package port;

/*
 attribut :
 un nom
 un numero
 bool value du composant
 	-histoire d'eviter de repeter le calcul :d
 
 methode :
 tostring alias donne la "grammaire"
 */

abstract class $Port {

	/* Attributs */
	protected int Numero;
	protected boolean Value;
	protected boolean Valide; 
	
	/* ** Constructeur ** */
	/* Inutile d'initialiser Value, car non pertinente si Valide = false */
	public $Port() {
		Numero=0;
		Valide=false;
	}
	/**
	 * construit le port avec le numero donné en argument
	 * @author Alex
	 */
	public $Port(int numero){
		this.Numero=numero;
		this.Valide=false;
	}
	
	/**
	 * construit le port avec le numero donné en argument et la value
	 * @ensure getValide == true //normal vue qu'il possede une valeur desormais
	 * @author Alex
	 */
	public $Port(int numero, boolean value){
		this.Numero=numero;
		this.Valide=true;
		this.Value=value;
		
	}
	
	/* ** Accesseurs et Getteur ** */ 
	/**
	 * Le booleen Valide == true ssi il est connecter a un Port de sortie
	 * @return Valide
	 */
	public boolean getValide() {
		return Valide;
	}

	/**
	 * @param valide the valide to set
	 */
	public void setValide(boolean valide) {
		Valide = valide;
	}
	
	/**
	 * Modifie la valeur du port en entrée
	 * @param value the value to set
	 */
	public void setValue(boolean value) {
		Value = value;
	}

	/**
	 * @return the value
	 */
	public boolean getValue() {

		return Value;
	}
	
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return Numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		Numero = numero;
	}

	/* ** Méthodes ** */
	public String toString() {
		return "Port n°" + Numero + " est " + Valide + " a l'etat " + Value;
	}


}
