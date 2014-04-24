package composant.recepteur;

/**
 * 
 * Classe Led
 *
 */
public class Led extends $Recepteur {
	
	private String etat;

	/**
	 * initialise les attributs in et out
	 */
	public Led(String nom, int idComposant){ 
		super(nom,idComposant,1);
	}

	/**
	* modifie l'attribut etat en fonction de l'entree
	*/
	public void execute(){
		if(estExecutable()){
			etat();	
		}
	}

	/**
	 * mise a jour de la variable etat
	 */
	public void etat(){
		if(this.in().get(0).getValue())
			etat = "allumee";
		else
			etat = "eteinte";
	}

	public String toString(){
		return "La Led n° " + getNumero() + "\n" + etat;
	}
}