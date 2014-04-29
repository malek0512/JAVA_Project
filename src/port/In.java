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
	
	public In ($Composant A, int numero){
		super(numero);
		this.A = A;
	}

	public $Composant getComposant() {
		return A;
	}
	
	public String toString() {
		return "PortE n°" + Numero + " est " + Valide + " a l'etat " + Value;
	}
	public String toString2() //y a n'y a rien a decrire au niveau des ports d'entrée, lolilol 
	{
		return "";
	}
}
