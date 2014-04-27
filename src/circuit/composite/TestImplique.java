package circuit.composite;

import circuit.CircuitTestUnitaire;
import circuit.Ferme;
import composant.$Composant.niveau;
import composant.generateur.Itr;
import composant.recepteur.$Recepteur;
import composant.recepteur.Led;

public class TestImplique {

	public static void main(String[] args) {
		CircuitTestUnitaire TestImp = new CircuitTestUnitaire(new Implique(0));
		TestImp.tester();
		
//		Ferme Circuit = new Ferme();
//		Itr inter1 = new Itr("iter",1,niveau.Haut);
//		Itr inter2 = new Itr("iter",2,niveau.Haut);
//		Implique imp = new Implique(3);
//		Led led = new Led("LED",4);	
//		
//		Circuit.addGenerateur(inter1);
//		Circuit.addGenerateur(inter2);
//		Circuit.addComposant(imp);
//		Circuit.addRecepteur(led);
//
//		Circuit.connect(inter1, 0, imp, 0);
//		Circuit.connect(inter2, 0, imp, 1);
//		Circuit.connect(imp, 0, led, 0);
//		
//		
//		Circuit.execute();
		
//		System.out.println(Circuit.toDebug());

	}
}
