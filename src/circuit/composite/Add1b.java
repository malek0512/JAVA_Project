package circuit.composite;

import composant.transformateur.Nand;
import composant.transformateur.Nand3b;
import composant.transformateur.Oux;

public class Add1b extends Composite{

	Oux xor1 = new Oux("xor1",1);
	Oux xor2 = new Oux("xor2",2);
	Nand nand1 = new Nand("Nand1",3);
	Nand nand2 = new Nand("Nand2",4);
	Nand nand3 = new Nand("Nand3",5);
	Nand3b nand4 = new Nand3b("Nand4",6);
	
	public Add1b(int numero) {
		super("Add1b",numero,3,2);
		addComposant(nand4);
		addComposant(xor1);
		addComposant(xor2);
		addComposant(nand1);
		addComposant(nand2);
		addComposant(nand3);
		
		
		// Entrees
		// a_0 sur port 0, 
		// b_0 sur port 1,
		// c_0 sur port 2
		
		//Sorties
		// r_0 sur Out 0, 
		// c_1 sur Out 1
		connect(this, 0, xor1, 0); //connecte 
		connect(this, 1, xor1, 1);
		
		connect(xor1, 0, xor2, 0);
		connect(this, 2, xor2, 1);
		connect(xor2, 0, this, 0);
		
		connect(this, 2, nand1, 0);
		connect(this, 1, nand1, 1);
		
		connect(this, 2, nand2, 0);
		connect(this, 0, nand2, 1);
		
		connect(this, 1, nand3, 0);
		connect(this, 0, nand3, 1);
		
		connect(nand1, 0, nand4, 0);
		connect(nand2, 0, nand4, 1);
		connect(nand3, 0, nand4, 2);
		
		connect(nand4, 0, this, 1);
	}

	
	
}
