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
	
	/* ** Méthodes ** */
	protected abstract String tooString();
	
	public boolean getValide(){
		return(this.Valide);
	}
	
	public void setValue(boolean v){
		this.Value=v;
	}
	/**
	 * @return the value
	 */
	public boolean getValue() {
		return Value;
	}
}
