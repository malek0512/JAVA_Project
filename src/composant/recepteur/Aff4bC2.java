package composant.recepteur;

import composant.$Composant.niveau;

public class Aff4bC2 extends $Recepteur{

	private Integer etat; //Compris entre -8 et 7
	
	public Aff4bC2(String nom, int idComposant) {
		super(nom,idComposant,4);
		this.etat = 0;
	}

	/**
	* modifie l'attribut etat en fonction de l'entree
	*/
	public Integer getEtat() {
		return etat;
	}
	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	
	public void execute(){
//		if(estExecutable()){
			// Recupere la valeure des entrees vers un int
			for(int i=0; i<arrayEntrees.size()-1; i++){
				if (arrayEntrees.get(i).getValue())
					this.etat = 1 * ((int) Math.pow(2, i+1)) + this.etat;
			}
			if (arrayEntrees.get(3).getValue())
				this.etat = -1 * this.etat;
//		}
	}

	public String toString(){
		return "Le Aff4bC2 n° " + getNumero() + " est a l'état "+ etat.toString();
	}

	public String infoComplementaire()
	{
		return "{" + this.etat.toString() + "}";
	}

}
