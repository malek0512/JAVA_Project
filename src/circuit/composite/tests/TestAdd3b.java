package circuit.composite.tests;

import circuit.CircuitTestUnitaire;
import circuit.composite.Add3b;

public class TestAdd3b {
	
	public static void main(String[] args) {
		CircuitTestUnitaire TestAdd = new CircuitTestUnitaire(new Add3b(0));
		TestAdd.tester();
	}
}