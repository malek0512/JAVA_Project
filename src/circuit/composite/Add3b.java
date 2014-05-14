package circuit.composite;

public class Add3b extends Composite {

	Add1b add1 = new Add1b(1);
	Add1b add2 = new Add1b(2);
	Add1b add3 = new Add1b(3);
	
	public Add3b(int numero) {
		super("Add3b",numero,7,4);
		addComposant(add1);
		addComposant(add2);
		addComposant(add3);
		
		// Entrees
		// a_0 sur port 0, a_1 sur port 1, a_2 sur port 2, 
		// b_0 sur port 3, b_1 sur port 4, b_5 sur port 5
		// c_0 sur port 6
		
		//Sorties
		// r_0 sur Out 0, r_1 sur Out 1, r_2 sur Out 2, 
		// c_1 sur Out 3

		connect(this, 0, add1, 0);
		connect(this, 3, add1, 1);
		connect(this, 6, add1, 2);
		
		connect(this, 1, add2, 0);
		connect(this, 4, add2, 1);
		connect(add1, 1, add2, 2);
		
		connect(this, 2, add3, 0);
		connect(this, 5, add3, 1);
		connect(add2, 1, add3, 2);
		
		//Sortie
		connect(add1, 0, this, 0);
		connect(add2, 0, this, 1);
		connect(add3, 0, this, 2);
		connect(add3, 1, this, 3);
	}

}
