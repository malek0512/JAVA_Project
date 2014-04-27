package circuit;

import composant.$Composant.niveau;
import composant.generateur.Itr;
import composant.recepteur.$Recepteur;
import composant.recepteur.Led;
import composant.transformateur.Oux;

public class CircuitTestUnitaire {

	public CircuitTestUnitaire() {
		// TODO Auto-generated constructor stub
	}

	
	// Attribut ListeComposants
	public static void tester(){
		Ferme Circuit = new Ferme();
		$Recepteur led = new Led("LED",1);
		Itr inter1 = new Itr("iter",4,niveau.Bas);
		Itr inter2 = new Itr("iter",2,niveau.Bas);
		Oux ou_exclusif = new Oux("ORR", 3);
		
		Circuit.addGenerateur(inter1);
		Circuit.addGenerateur(inter2);
		Circuit.addComposant(ou_exclusif);
		Circuit.addRecepteur(led);

		Circuit.connect(inter1, 0, ou_exclusif, 0);
		Circuit.connect(inter2, 0, ou_exclusif, 1);
		Circuit.connect(ou_exclusif, 0, led, 0);

		/* 4 test possible */
		
		/* 1er test */
		System.out.println(inter1.toString());
		System.out.println(inter2.toString());
		Circuit.execute();
		System.out.println(led.toString());
		
		/* 2eme test */
		inter1.setNiveau(niveau.Bas);
		inter2.setNiveau(niveau.Haut);
		System.out.println(inter1.toString());
		System.out.println(inter2.toString());
		Circuit.execute();
		System.out.println(led.toString());
		
		/* 3eme test */
		inter1.setNiveau(niveau.Haut);
		inter2.setNiveau(niveau.Bas);
		System.out.println(inter1.toString());
		System.out.println(inter2.toString());
		Circuit.execute();
		System.out.println(led.toString());
		
		/* 4eme test */
		inter1.setNiveau(niveau.Haut);
		inter2.setNiveau(niveau.Haut);
		System.out.println(inter1.toString());
		System.out.println(inter2.toString());
		Circuit.execute();
		System.out.println(led.toString());
		
//		inter1.execute();
//		inter1.execute();
//		ou_exclusif.execute();
//		led.execute();
		
//		System.out.println(inter1.toString());
//		System.out.println(inter2.toString());
//		System.out.println(ou_exclusif.toString());
//		System.out.println(led.toString());
//		System.out.println(inter1.sortieList().toString());
//		System.out.println(inter2.sortieList().toString());
//		System.out.println(ou_exclusif.entreeList().toString());
//		System.out.println(ou_exclusif.sortieList().toString());
//		System.out.println(led.entreeList().toString());
	}
	
	public static void main (String [] args) {
		tester();
	}

}
