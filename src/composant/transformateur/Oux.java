package composant.transformateur;

public class Oux extends Ou{

	public Oux(String nom, int idComposant) {
		super(nom, idComposant);
	}

	/**
	 * la fonction booléenne Ou
	 * out[1] = in[1].value() || in[2].value()
	 */
		public void execute(){
			if(estExecutable()){
				boolean entree1 = this.entreeList().get(0).getValue();
				boolean entree2 = this.entreeList().get(1).getValue();
				if (entree1 && entree2)
					this.sortieList().get(0).setValue(false);
				else 
					this.sortieList().get(0).setValue(entree1 || entree2);
			}
		}
}
