package circuit.composite.tests;

import circuit.CircuitTestUnitaire;
import circuit.composite.Add4b;

public class TestAdd4b {
	
	public static void main(String[] args) {
		CircuitTestUnitaire TestAdd = new CircuitTestUnitaire(new Add4b(0));
		TestAdd.tester();
	}
}