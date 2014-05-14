package port;

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
	
	
	/* ** Accesseurs et Getteur ** */ 
	/**
	 * Le booleen Valide == true ssi il est connecter a un Port de sortie
	 * @return Valide
	 */
	public boolean getValide() {
		return Valide;
	}

	/**
	 * modifie la validiter du port
	 * @param valide : la validiter du port
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
	 * renvoiela valeur
	 * @return the value
	 */
	public boolean getValue() {

		return Value;
	}
	
	/**
	 * renvoie le numero du port
	 * @return the numero
	 */
	public int getNumero() {
		return Numero;
	}

	/**
	 * modifie le numero du port
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
