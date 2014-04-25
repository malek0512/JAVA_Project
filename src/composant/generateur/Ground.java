package composant.generateur;


/**
 * 
 * Classe Ground 
 *
 */
public class Ground extends $Generateur {
	
	private String etat;

	/**
	 * initialise les attributs in et out
	 */
	public Ground(String nom, int idComposant){ 
		super(nom,idComposant,1);
	}
	/**
	* initialise la sortie du generateur a false
	*/
	public void execute(){
		if(estExecutable()){
			this.sortieList().get(0).setValue(false);
		}
	}
	
	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void etat(){
		etat = "Le port de sortie est a " + this.sortieList().get(0).getValue() +"\n";
	}
	
	public String toString(){
		return "Le Ground n° " + getNumero() + "\n" + etat;
	}

}