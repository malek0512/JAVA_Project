package composant.generateur;

import composant.$Composant.niveau;

public class Gen4bC2 extends $Generateur{

	private Integer etat; //Compris entre -8 et 7
	
	public Gen4bC2(String nom, int idComposant, int etat) {
		super(nom,idComposant,4);
		this.etat=etat;
	}
	
	/**
	 * (Synchronise) Met a jour la valeur des ports de sorties selon la valeur de son etat
	 */
	protected void spreadNiveau(){
		Boolean b;
		for(int j=0; j<arraySorties.size();j++){
			if (j<Integer.toBinaryString(this.etat).length()){
				b = Integer.toBinaryString(this.etat).charAt(Integer.toBinaryString(this.etat).length() - j -1) == '1';
			} else
				b = false;
			arraySorties.get(j).setValue(b);
			
		}
	}
	
	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
		spreadNiveau();
	}
	/**
	 * retourne les infos du generateur
	 */
	public String toString() {
		return "Generateur " + Nom + " N° " + Numero + " a l'etat " + getEtat();
	}
}
