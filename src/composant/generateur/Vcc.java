package composant.generateur;

/**
 * 
 * Classe modélisant une Vcc qui est un generateur
 * renvoie toujours la valeur true
 */
public class Vcc extends $Generateur {
	
	private String etat;

	/**
	 * initialise les attributs in et out
	 */
	  public Vcc(String nom, int idComposant){ 
		  super(nom,idComposant,1);
	  }
	/**
	* initialise la sortie du generateur a true
	*/
		public void execute(){
			if(estExecutable()){
				this.out().get(0).setValue(true);
			}
		}
	
	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void etat(){
		etat = "Le port de sortie est a " + this.out().get(0).getValue() +"\n";
	}
	
	public String toString(){
		return "Le Vcc n° " + getNumero() + "\n" + etat;
	}
}