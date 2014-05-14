package composant.recepteur;

import composant.$Composant;

/**
 * 
 * Classe Led
 *
 */
public class Led extends $Recepteur {
	
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
			if (this.arrayEntrees.get(0).getValue())
				this.value = niveau.Haut;
			else 
				this.value = niveau.Bas;
		}
	}

	public String toString(){
		return "La Led n° " + getNumero() + " est au niveau "+ value.toString();
	}


	public String infoComplementaire()
	{
		return "{" + this.value.toString() + "}";
	}


}