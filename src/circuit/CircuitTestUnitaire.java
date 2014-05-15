package circuit;

import java.util.List;

import composant.$Composant.niveau;
import composant.$Composant;
import circuit.composite.*;
import composant.generateur.Gen4bC2;
import composant.generateur.Itr;
import composant.recepteur.Aff4bC2;
import composant.recepteur.Led;


public class CircuitTestUnitaire {

//	List<List<niveau>,List<niveau>> extension; 
	Ferme Circuit;
	public CircuitTestUnitaire($Composant c) {
		Circuit = new Ferme();
		Circuit.addComposant(c);
		if (! (c instanceof Add4b)){
			for(int i=0; i<c.nbEntrees();i++){
				Itr G = new Itr("Iter",i+1,niveau.Bas);
				Circuit.listGenerateur.add(G);
				Circuit.connect(G, 0, c, i);
			}
			for(int i=0; i<c.nbSorties();i++){
				Led L = new Led("Led",i+1+c.nbEntrees());
				Circuit.listRecepteur.add(L);
				Circuit.connect(c, i, L, 0);
			}
		} else{
			Itr retenue_entrante = new Itr ("retenue_entrante", 1,niveau.Bas);
			Gen4bC2 gen4a = new Gen4bC2 ("Opérande A", 2,0);
			Gen4bC2 gen4b = new Gen4bC2 ("Opérande B", 3,0);
			Aff4bC2 aff4 = new Aff4bC2("Resultat", 4);
			Led retenue_sortante = new Led("retenue_sortante",5);
			Circuit.addGenerateur(retenue_entrante);
			Circuit.addGenerateur(gen4a);
			Circuit.addGenerateur(gen4b);
			Circuit.addRecepteur(aff4);
			Circuit.addRecepteur(retenue_sortante);
			
//			c.setNumero(4);
			Circuit.connect(retenue_entrante, 0, c, 8);
			Circuit.connect(c,4,retenue_sortante, 0);
			for(int i=0; i<4; i++){
				Circuit.connect(gen4a, i, c, i);
				Circuit.connect(gen4b, i, c, i+4);
				Circuit.connect(c, i, aff4, i);
			}

		}
	}

	
	// Attribut ListeComposants
	public void tester(){
		if (! (this.Circuit.listComposant.get(0) instanceof Add4b)){
			niveau etat;
			boolean b;
			for(int i=0; i<Math.pow(2, Circuit.listGenerateur.size());i++){
				for(int j=0; j<Circuit.listGenerateur.size();j++){
					if (j<Integer.toBinaryString(i).length()){
						b = Integer.toBinaryString(i).charAt(Integer.toBinaryString(i).length() - j -1) == '1';
					} else
						b = false;
					if (b) etat = niveau.Haut;
					else etat = niveau.Bas;
					
					if (Circuit.listGenerateur.get(j) instanceof Itr)
						((Itr) Circuit.listGenerateur.get(j)).setNiveau(etat);
				}
				Circuit.execute();
				System.out.println(Circuit.toDebug());
			}
		} else {
			for(int i=-8 ; i<8; i++){
				((composant.generateur.Gen4bC2) Circuit.listGenerateur.get(1)).setEtat(i);
				((composant.generateur.Gen4bC2) Circuit.listGenerateur.get(2)).setEtat(1);
				Circuit.execute();
				System.out.println(Circuit.toDebug());
			}
		}
	}
	
	
}
