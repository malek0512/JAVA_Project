package circuit;

import java.util.LinkedList;
import java.util.List;

import port.In;
import port.Out;
import composant._Composant;
import composant._ComposantInOnly;
import composant._ComposantOutOnly;
/*

void connect
- pour connecter un composant a un autre composant
- on apelle connect d'un seul composant qui recoit un nouveau port d'entree

void deconnecter
- pour deconcecter un composant a un autre composant

*/
public class Composite extends $Ouvert implements _ComposantInOnly, _ComposantOutOnly {
	// Attributs 
	private List<_Composant> ListComposant;
	private String Nom;
	private int Numero;
	private int nbEntreeMax;
	private int nbSortieMax;
	/**
	 * Permet de creer un composite
	 */
	public Composite (int nbEntreeMax, int nbSortieMax){
		super();
		this.nbEntreeMax=nbEntreeMax;
		this.nbSortieMax=nbSortieMax;
		ListComposant= new LinkedList<_Composant>();
	}
	
	 /* @return le nombre de composant */
	public int nbComposant(){
		return ListComposant.size();
	}
			
	/* @return le nombre de sorties Out d'un composite */
	public int nbSortie() {
		return arraySorties.size();
	}
	/*@return le nombre d'entrees In d'un composite*/
	public int nbEntree(){
		return arrayEntrees.size();
	}

	/* Ajoute un composant a la liste des composants, sans aucune precaution*/
	public void ajoutComposant(_Composant composant) {
		ListComposant.add(composant);
	}

	public int ajoutConnexion(_ComposantOutOnly A, int noSortie, _ComposantInOnly B, int noEntree){
		return 0;
	}
	void connect(_ComposantOutOnly A, int noPortSortie, _ComposantInOnly B, int noPortEntree){
		In PortE = B.entreeList().get(noPortEntree);
		A.sortieList().get(noPortSortie).connect(PortE); 
		// RMQ : PortSortie.connect se charge de modifié PortEntre.Value et PortEntre.Valide=1 
	}

	// ***********************************************
	// Fonctions implémentées issuent de l'heritage //
	// **********************************************
	public int getNumero() {
		return Numero;
	}

	public String getNom() {
		return Nom;
	}

	public boolean estExecutable() {
		boolean OK = true;
		for(int i=0; i<nbEntrees(); i++){
			OK = OK && arrayEntrees.get(i).getValide() == true;
		}
		return OK;
	}

	public int nbSorties() {
		return arraySorties.size();
	}

	public List<Out> sortieList() {
		return (arraySorties);
	}

	public int nbEntrees() {
		return arrayEntrees.size();
	}

	public List<In> entreeList() {
		return (arrayEntrees);
	}

	public int nbPortEntreeLibre() {
		return nbEntreeMax - nbEntrees();
	}

	public int nbPortSortieLibre() {
		return nbSortieMax - nbSorties();
	}

	
}