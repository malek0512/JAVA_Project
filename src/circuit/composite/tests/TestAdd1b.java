package circuit.composite.tests;

import circuit.CircuitTestUnitaire;
import circuit.composite.Add1b;

public class TestAdd1b {
		
	public static void main(String[] args) {
		CircuitTestUnitaire TestAdd = new CircuitTestUnitaire(new Add1b(1));
		TestAdd.tester();
	}
}
