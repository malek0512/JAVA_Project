package composant.transformateur;


/**
 * 
 * Classe modelisant un Ou 
 *
 */
public class Ou extends $Transformateur {
	
	private String etat;
	private String etatIn;
	private String etatOut;

/**
 * initialise les attributs in et out
 */
  public Ou(String nom, int idComposant){ 
	  super(nom,idComposant,2,1);
  }
/**
 * la fonction booléenne Ou
 * out[1] = in[1].value() || in[2].value()
 */
	public void execute(){
		if(estExecutable()){
			this.sortieList().get(0).setValue(this.entreeList().get(0).getValue() || this.entreeList().get(1).getValue());
		}
	}

  	/**
	 * affecte a l'attribut etat sa juste valeur
	 */
	public void etat(){
		etatIn();
		etatOut();
		etat = etatIn + etatOut;
	}
	/**
	 * affecte a l'attribut etatIn sa juste valeur
	 */
	public void etatIn(){
		etatIn = "";
		for(int i=1; i<=this.nbEntrees(); i++){
			etatIn = this.etatIn + "Le port d'entree " + i + " est a " + this.entreeList().get(i).getValue() +"\n";
		}
	}
	/**
	 * affecte a l'attribut etatOut sa juste valeur
	 */
	public void etatOut(){
		etatOut = "Le port de sortie est a " + this.sortieList().get(0).getValue() +"\n";
	}
	
	public String toString(){
		return "Le Ou n° " + getNumero() + "\n" + etat;
	}
}