package circuit.composite.tests;

import circuit.CircuitTestUnitaire;
import circuit.Ferme;
import circuit.composite.Implique;
import composant.$Composant.niveau;
import composant.generateur.Itr;
import composant.recepteur.$Recepteur;
import composant.recepteur.Led;

public class TestImplique {

	public static void main(String[] args) {
		CircuitTestUnitaire TestImp = new CircuitTestUnitaire(new Implique(0));
		TestImp.tester();
		
	}
}
