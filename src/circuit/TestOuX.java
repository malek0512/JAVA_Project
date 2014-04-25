package circuit;
import circuit.Composite;
import composant._Composant;
import composant._ComposantInOnly;
import composant._ComposantOutOnly;
import composant.generateur.Itr;
import composant.recepteur.Led;
import composant.transformateur.Oux;

public class TestOuX {

	// Attribut ListeComposants
	public static void tester(){
		Itr inter1 = new Itr("iter1",1,true);
		Itr inter2 = new Itr("iter2",2,false);
		Oux ou_exclusif = new Oux("ORR", 3);
		Led led = new Led("LED",4);
		Composite compa = new Composite("comp",1,2,1);
		
		compa.ajoutComposant(inter1);
		compa.ajoutComposant(inter2);
		compa.ajoutComposant(ou_exclusif);
		compa.ajoutComposant(led);
		
		inter1.execute();
		inter2.execute();
		System.out.println(inter1.valeurItr);
		System.out.println(inter1.sortieList().get(0).getValue());
		compa.connect((_ComposantOutOnly) inter1, 0, (_ComposantInOnly) ou_exclusif, 0);
		compa.connect((_ComposantOutOnly) inter2, 0, (_ComposantInOnly) ou_exclusif, 1);
		
		ou_exclusif.execute();
		compa.connect((_ComposantOutOnly) ou_exclusif, 0, (_ComposantInOnly) led, 0);
		led.execute();
		
		System.out.println(led.toString());
	}
	
	public static void main (String [] args) {
		tester();
	}

	
}
