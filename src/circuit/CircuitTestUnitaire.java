package circuit;

import java.util.List;

import composant.$Composant.niveau;
import composant.$Composant;
import composant.generateur.Itr;
import composant.recepteur.Led;

public class CircuitTestUnitaire {

//	List<List<niveau>,List<niveau>> extension; 
	Ferme Circuit;
	public CircuitTestUnitaire($Composant c) {
		Circuit = new Ferme();
		Circuit.addComposant(c);
		for(int i=0; i<c.nbEntrees();i++){
			Itr G = new Itr("Iter",i,niveau.Bas);
			Circuit.listGenerateur.add(G);
			Circuit.connect(G, 0, c, i);
		}
		for(int i=0; i<c.nbSorties();i++){
			Led L = new Led("Led",i);
			Circuit.listRecepteur.add(L);
			Circuit.connect(c, i, L, 0);
		}
	}

	
	// Attribut ListeComposants
	public void tester(){
		niveau etat;
		boolean b;
		for(int i=0; i<Math.pow(2, Circuit.listGenerateur.size());i++){
//			System.out.println(i);
			for(int j=0; j<Circuit.listGenerateur.size();j++){
//				System.out.println(Integer.toBinaryString(i).length());
				if (j<Integer.toBinaryString(i).length()){
//					System.out.println(Integer.toBinaryString(i).charAt(j));
					b = Integer.toBinaryString(i).charAt(Integer.toBinaryString(i).length() - j -1) == '1';
				} else
					b = false;
//				System.out.println(b);
				if (b) etat = niveau.Haut;
				else etat = niveau.Bas;
				
//				System.out.println(etat);
				if (Circuit.listGenerateur.get(j) instanceof Itr)
					((Itr) Circuit.listGenerateur.get(j)).setNiveau(etat);
			}
			Circuit.execute();
			System.out.println(Circuit.toString());
		}
	}
	
	
}
