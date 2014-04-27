package circuit.composite;

import composant.transformateur.Non;
import composant.transformateur.Ou;

public class Implique extends Composite{

	Non Not = new Non ("Not",1);
	Ou Or = new Ou ("Or",2);
	
	public Implique(int numero) {
		super("implique",numero,2,1);
		addComposant(Not);
		addComposant(Or);
		connect(this, 0, Not, 0);
		connect(Not, 0, Or, 0);
		connect(this,1, Or, 1);
		connect(Or, 0, this, 0);
	}
	
}
