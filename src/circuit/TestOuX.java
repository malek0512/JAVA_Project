package circuit;
import composant.$Composant.niveau;
//import composant.generateur.$Generateur;
import composant.generateur.Itr;
import composant.recepteur.Led;
import composant.transformateur.Oux;
import circuit.Ferme;

public class TestOuX {

	
	// Attribut ListeComposants
	public static void tester(){
		Ferme Circuit = new Ferme();
		
		Itr inter1 = new Itr("iter1",1,niveau.Haut);
		Itr inter2 = new Itr("iter2",2,niveau.Bas);
		Oux ou_exclusif = new Oux("ORR", 3);
		Led led = new Led("LED",4);

		Circuit.addGenerateur(inter1);
		Circuit.addGenerateur(inter2);
		Circuit.addComposant(ou_exclusif);
		Circuit.addRecepteur(led);

		Circuit.connect(inter1, 0, ou_exclusif, 0);
		Circuit.connect(inter2, 0, ou_exclusif, 1);
		Circuit.connect(ou_exclusif, 0, led, 0);

		Circuit.execute();

//		System.out.println(inter1.toString());
//		System.out.println(inter1.sortieList().get(0).getValue());
		System.out.println(led.toString());
	}
	
	public static void main (String [] args) {
		tester();
	}

}
