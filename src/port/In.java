package port;

import composant.$Composant;

/*
attribut :
bool valide
methode :


 */
public class In extends $Port {

	private $Composant A;
	
	/**
	 * @return the a
	 */
	public $Composant getComposant() {
		return A;
	}


	/* Constructeurs */
	public In($Composant A) {
		super();
		this.A = A;
	}

	public String toString() {
		return "PortE n°" + Numero + " est " + Valide + " a l'etat " + Value;
	}

}
