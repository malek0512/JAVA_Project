package circuit;

import java.util.ArrayList;

import port.In;
import port.Out;
import composant._Composant;

public class Composite extends $Ouvert implements _Composant {
	
	private ArrayList<In> ArrayEntree;
	private ArrayList<Out> ArraySortie;

	/**
	 * Permet de creer un composite
	 * @param nbEntree : Nombre d'Entree du composite
	 * @param nbSortie : Nombre de Sortie du composite
	 */
	public Composite (int nbEntree, int nbSortie){
		super();
		ArrayEntree = new ArrayList<In>();
		ArraySortie = new ArrayList<Out>();
		for (int i = 0; i < nbEntree; i++) {
				ArrayEntree.add(new In());
		}
		for (int j = 0; j < nbSortie; j++) {
			ArraySortie.add(new Out());
		}
	}
	
	 /* @return Retourne le nombre de composant */
			public int nbComposant(ArrayList<_Composant> ArrayComposant){
				return ArrayComposant.size();
			}
			
	/* @return le nombre de sorties d'un composite */
			public int nbsortie() {
				return ArraySortie.size();
			}
	/*@return le nombre d'entrees d'un composite*/
			public int nbentree(){
				return ArrayEntree.size();
				
			}

			@Override
			public void nbPortEntreeLibre() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void nbPortSortieLibre() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void ajoutComposant() {
				// TODO Auto-generated method stub
				
			}

	
			

	



}