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
//			System.out.println(Integer.toBinaryString(i).length());
			if (j<Integer.toBinaryString(this.etat).length()){
//				System.out.println(Integer.toBinaryString(i).charAt(j));
				b = Integer.toBinaryString(this.etat).charAt(Integer.toBinaryString(this.etat).length() - j -1) == '1';
			} else
				b = false;
//			System.out.println(b);
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
}
