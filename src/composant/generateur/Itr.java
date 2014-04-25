package composant.generateur;


/**
 * 
 * Classe Itr modelise un interupteur
 *
 */
public class Itr extends $Generateur {
	
	/**
	 * la valeur de sortie de Itr initialisée par l'utilisateur
	 */
	private String etat;
	public boolean valeurItr;
	
	/**
	 * initialise les attributs in et out
	 */
	public Itr(String nom, int idComposant, boolean valeurItr){ 
		super(nom,idComposant,1);
		this.valeurItr=valeurItr;
	}
	
	private void setNiveau(boolean value){
		this.valeurItr=value;
	}

	/**
	* initialise la sortie du generateur a valeurItr
	*/
	public void execute(){
		if(estExecutable()){
			this.sortieList().get(0).setValue(this.valeurItr);
		}
	}
	
	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void etat() {
		etat = "Le port de sortie est a " + this.sortieList().get(0).getValue() +"\n";
	}
	
	public String toString(){
		return "L'Itr n° " + getNumero() + "\n" + etat ;
	}
}