package composant;

import java.util.ArrayList;
import java.util.List;

import port.In;
import port.Out;

/*
 --Invariant--
 a au moins un port
 --Attribut--
 String : Nom;
 Int : Numero;
 liste de port entrer 
 liste de port sortie

 --Methode--
 void action (effectue sont action si executable()==true et envoie la valeur dans son port de sortie)
 - generateur, envoyer info sur les ports sortie
 - transfo, si port entrer avaiable, envoyer info sur port sortie
 - recepeteur, si port entrer avaiable, faire un truc, genre allumer une dell.
 void connect
 - pour connecter un composant a un autre composant
 - on apelle connect d'un seul composant qui recoit un nouveau port d'entree
 void deconnecter
 - pour deconcecter un composant a un autre composant
 bool executable
 - renvoie true si tout les ports ENTREE sont up
 */
/*
 --Invariant--
 a au moins un port
 --Attribut--
 String : Nom;
 Int : Numero;
 liste de port entrer 
 liste de port sortie

 --Methode--
 void action (effectue son action si executable()==true et envoie la valeur dans son port de sortie)
 - generateur, envoyer info sur les ports sortie
 - transfo, si port entrer avaiable, envoyer info sur port sortie
 - recepeteur, si port entrer available, faire un truc, genre allumer une dell.



 bool executable 
 - renvoie true si tout les ports ENTREE sont up
 ==> deplacer dans _Composant_out
 */

/**
 * invariant :nbEntrees + nbSorties > 0
 */

public abstract class $Composant {

	// Attributs
	protected List<Out> arraySorties;
	protected List<In> arrayEntrees;
	protected String Nom;
	protected int Numero;
	protected int nbEntreeMax;
	protected int nbSortieMax;
	public enum niveau {Bas,Haut};
	protected niveau value;

	public $Composant(String nom, int numero, int nbEntreeMax, int nbSortieMax) {
		super();
		Nom = nom;
		Numero = numero;
		this.nbEntreeMax = nbEntreeMax;
		this.nbSortieMax = nbSortieMax;
		arraySorties = new ArrayList<Out>(this.nbEntreeMax);
		arrayEntrees = new ArrayList<In>(this.nbSortieMax);
		for (int i = 0; i < nbEntreeMax; i++) {
			arrayEntrees.add(new In(this));
			arrayEntrees.get(i).setNumero(i);
		}
		for (int i = 0; i < nbSortieMax; i++) {
			arraySorties.add(new Out());
			arraySorties.get(i).setNumero(i);
		}
	}

	/**
	 * Tout composant possede un numero
	 * 
	 * @return Numero du composant
	 */
	public int getNumero() {
		return Numero;
	}

	/**
	 * Tout composant possede un nom
	 * 
	 * @return Nom du composant
	 */
	public String getNom() {
		return Nom;
	}

	/**
	 * @return le nombre d'entree(s) du composant
	 */
	public int nbEntrees() {
		return arrayEntrees.size();
	}

	/**
	 * revoie le nombre de sortie(s) du composant
	 */
	public int nbSorties() {
		return arraySorties.size();
	}

	/**
	 * retourne l'ensemble des sorties du composant
	 * 
	 * @require !(this instanceof Recepteur)
	 * @return un tableau de Port
	 */
	public List<Out> sortieList() {
		return arraySorties;
	}

	/**
	 * retourne l'ensemble des entrees du composant
	 * 
	 * @require !(this instanceof Generateur)
	 * @return un tableau de Port
	 */
	public List<In> entreeList() {
		return arrayEntrees;
	}

	/**
	 * @return true si tout les ports ENTREE sont activé
	 */
	public boolean estExecutable() {
		int i = 0;
		int j = 0;
		while (i < arrayEntrees.size()
				&& arrayEntrees.get(i).getValide() == true) {
			i++;
		}
		while (j < nbSorties() && arraySorties.get(i).getValide() == true) {
			j++;
		}
		return (i == arrayEntrees.size() && j == arraySorties.size());
	}

	/**
	* Dans le cas d'un recepteur, seul la valeur du niveau est mis a jour selon l'entree
	* Sinon execute() Propage la valeur du niveau aux composants qui lui sont reliés
	*/
	public abstract void execute();
	
	/**
	 * @return le niveau {Bas,Haut}
	 */
	public niveau getNiveau() {
		return value;
	}

	/**
	 * 
	 * @param b : boolean
	 * @return type niveau selon une valeur booleenne 
	 */
	public niveau niveauFromBool (boolean b){
		if (b)
			return niveau.Haut;
		else 
			return niveau.Bas;
	}
	
	/**
	 * (Synchronise) Met a jour la valeur des ports de sorties selon la valeur de son nineau
	 *@require nbSorties()!=0
	 */
	protected void spreadNiveau(){
		if (nbSorties()!=0) {
			boolean valueSortie = value.ordinal()==1;
			for(int i=0; i<nbSorties();i++)
				arraySorties.get(i).setValue(valueSortie);
		}
	}

	/**
	 * 
	 * @return String
	 */
	public abstract String toString();

}
