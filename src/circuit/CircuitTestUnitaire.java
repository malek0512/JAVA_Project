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
			Itr retenue_entrante = new Itr ("retenue_entrante", 0,niveau.Bas);
			Gen4bC2 gen4 = new Gen4bC2 ("Gen4bC2", 1,0);
			Aff4bC2 aff4 = new Aff4bC2("Aff4bC2", 2);
			Led retenue_sortante = new Led("retenue_sortante",3);
			Circuit.addGenerateur(retenue_entrante);
			Circuit.addGenerateur(gen4);
			Circuit.addRecepteur(aff4);
			Circuit.addRecepteur(retenue_sortante);
			
//			Circuit.connect(retenue_entrante, 0, c, 4);
//			Circuit.connect(c,4,retenue_sortante, 0);
//			for(int i=0; i<4; i++){
//				Circuit.connect(gen4, i, c, i);
//				Circuit.connect(c, i, aff4, i);
//			}
			c.setNumero(4);
//			retenue_entrante.addSortie(0, 4, 4);
//			System.out.println(retenue_entrante.getMemoireSortie().get(0).toString());
//			retenue_sortante.addSortie(0, 4, numeroEntreeComposant)
//			Circuit.connectAllFromList();
			Circuit.connect(retenue_entrante, 0, c, 4);
			Circuit.connect(c,4,retenue_sortante, 0);
			for(int i=0; i<4; i++){
				Circuit.connect(gen4, i, c, i);
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
				System.out.println(Circuit.toString2());
			}
		} else {
			for(int i=-8 ; i<8; i++){
				((composant.generateur.Gen4bC2) Circuit.listGenerateur.get(1)).setEtat(i);
				Circuit.execute();
			}
			System.out.println(Circuit.toString2());
		}
	}
	
	
}
