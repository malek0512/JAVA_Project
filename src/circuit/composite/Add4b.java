package circuit.composite;

public class Add4b extends Composite{

	Add3b add3b = new Add3b(1);
	Add1b add1b = new Add1b(2);
	public Add4b(int numero) {
		super("Add4b",numero,9,5);
		addComposant(add3b);
		addComposant(add1b);
		connect(this, 0, add3b, 0); //connecte operande a
		connect(this, 1, add3b, 1);
		connect(this, 2, add3b, 2);
		connect(this, 3, add1b, 0);
		
		connect(this, 8, add3b, 6); //connecte la retenue : c_0 et add3b c_0
		connect(add3b, 3, add1b, 2);
		connect(add1b, 1, this, 4);
		
		connect(this, 4, add3b, 3); //connecte operande b
		connect(this, 5, add3b, 4);
		connect(this, 6, add3b, 5);
		connect(this, 7, add1b, 1);
		
		connect(add3b, 0, this, 0); //connecte le resultat
		connect(add3b, 1, this, 1);
		connect(add3b, 2, this, 2);
		connect(add1b, 0, this, 3);
		
	}

	
}
