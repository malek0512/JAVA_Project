package circuit;
import composant.$Composant.niveau;
//import composant.generateur.$Generateur;
import composant.generateur.Itr;
import composant.recepteur.Led;
import composant.recepteur.$Recepteur;
import composant.transformateur.Oux;
import circuit.Ferme;

public class TestOuX {

	
	// Attribut ListeComposants
	public static void tester(){
		Ferme Circuit = new Ferme();
		
		Itr inter1 = new Itr("iter",1,niveau.Haut);
		Itr inter2 = new Itr("iter",2,niveau.Bas);
		Oux ou_exclusif = new Oux("ORR", 3);
		$Recepteur led = new Led("LED",4);
		
		Circuit.addGenerateur(inter1);
		Circuit.addGenerateur(inter2);
		Circuit.addComposant(ou_exclusif);
		Circuit.addRecepteur(led);

		Circuit.connect(inter1, 0, ou_exclusif, 0);
		Circuit.connect(inter2, 0, ou_exclusif, 1);
		Circuit.connect(ou_exclusif, 0, led, 0);

		Circuit.execute();
//		inter1.execute();
//		inter1.execute();
//		ou_exclusif.execute();
//		led.execute();
		
//		System.out.println(inter1.toString());
//		System.out.println(inter2.toString());
//		System.out.println(ou_exclusif.toString());
		System.out.println(led.toString());
//		System.out.println(inter1.sortieList().toString());
//		System.out.println(inter2.sortieList().toString());
//		System.out.println(ou_exclusif.entreeList().toString());
//		System.out.println(ou_exclusif.sortieList().toString());
//		System.out.println(led.toString());
	}
	
	public static void main (String [] args) {
		tester();
	}

}
