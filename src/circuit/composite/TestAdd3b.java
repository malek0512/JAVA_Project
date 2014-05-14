package circuit.composite;

import circuit.CircuitTestUnitaire;

public class TestAdd3b {
	
	public static void main(String[] args) {
		CircuitTestUnitaire TestAdd = new CircuitTestUnitaire(new Add3b(0));
		TestAdd.tester();
	}
}