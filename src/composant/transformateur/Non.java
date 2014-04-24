package composant.transformateur;


/**
 * 
 * Classe modelisant un non
 *
 */
public class Non extends $Transformateur {
	
	private String etat;
	private String etatIn;
	private String etatOut;

	/**
	 * initialise les attributs in et out
	 */
	public Non(String nom, int idComposant){ 
		super(nom,idComposant,1,1);
	}

	/**
	 * la fonction booléenne Non
	 */
	public void execute(){
		if(estExecutable()){
			this.out().get(0).setValue(!(this.out().get(0).getValue()));
		}
	}

	/**
	 * affecte a l'attribut etat a sa juste valeur
	 */
	public void etat(){
		etatIn();
		etatOut();
		etat = etatIn + etatOut;
	}
	/**
	 * affecte a l'attribut etatIn a sa juste valeur
	 */
	public void etatIn(){
		etatIn = "Le port d'entree est a " + this.in().get(0).getValue() +"\n";
	}
		
	/**
	 * affecte a l'attribut etatOut a sa juste valeur
	 */
	public void etatOut(){
		etatOut = "Le port de sortie est a " + this.out().get(0).getValue() +"\n";
	}
		
	public String toString(){
		return "Le Non n° " + getNumero() + "\n" + etat;
	}
}