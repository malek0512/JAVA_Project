package composant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
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

public abstract class $Composant implements Comparable<$Composant> {

	
	// Attributs
	protected List<Out> arraySorties;
	protected List<In> arrayEntrees;
	protected String Nom;
	protected int Numero;
	protected int nbEntreeMax;
	protected int nbSortieMax;
	public enum niveau {Bas,Haut};
	protected niveau value;
	public enum couleur {BLANC,GRIS,NOIR};
	protected couleur c;
	protected int debut,fin;
	protected List<List<Couple>> memoireSortie;


	public $Composant(String nom, int numero, int nbEntreeMax, int nbSortieMax) {
		super();
		Nom = nom;
		Numero = numero;
		this.value = niveau.Bas;
		this.c = couleur.BLANC;
		this.nbEntreeMax = nbEntreeMax;
		this.nbSortieMax = nbSortieMax;
		arraySorties = new ArrayList<Out>(this.nbSortieMax); //Attention erreur possible
		arrayEntrees = new ArrayList<In>(this.nbEntreeMax);
		memoireSortie = new ArrayList<List<Couple>>(this.nbSortieMax);
		for (int i = 0; i < nbEntreeMax; i++) {
			arrayEntrees.add(new In(this));
			arrayEntrees.get(i).setNumero(i);
		}
		for (int i = 0; i < nbSortieMax; i++) {
			arraySorties.add(new Out());
			arraySorties.get(i).setNumero(i);
			memoireSortie.add(i, new LinkedList<Couple>());
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
		while (j < nbSorties() && arraySorties.get(j).getValide() == true) {
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

	public int compareTo($Composant o) {
		return o.fin;
	}
	
	/**
	 * 
	 * @return String
	 */
	public abstract String toString();

	public String toDebug(){
		return Nom + " niveau " + getNiveau().toString() +":\n PE :" +entreeList().toString() + ",\n PS :" +sortieList().toString();
	}
	
	/**
	 * ajoute au composant un port de sortie si il n'existe pas, y ajoute juste une connexion
	 * @param numS : numero de sortie du composant
	 * @param numC : numero du composant qui va etre connecter
	 * @param numSC : numero du port d'entre du composant destinataire
	 * @author Alex
	 */
//	protected abstract addSortie(int numS,int numC, int numSC);
	
	/**
	 * info complementaire dependant de chaque composant, par exemple, une DEL renverra "Allumer" ou "Eteint"
	 * @author Alex
	 */
	protected String infoComplementaire(){ // depend de chaque composant
		return "";
	}
	/**
	 * @return the debut
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * @param debut the debut to set
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}

	/**
	 * @return the fin
	 */
	public int getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}

	/**
	 * @return the c
	 */
	public couleur getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(couleur c) {
		this.c = c;
	}
	
	/**
	 * Mémorise la connexion sans auncune connexion au composant
	 */
	public void addSortie(int numeroSortie, int numeroComposant, int numeroEntreeComposant){
		memoireSortie.get(numeroSortie).add(new Couple(numeroComposant,numeroEntreeComposant));
	}	
	
	/**
	 * @return the memoireSortie
	 */
	public List<List<Couple>> getMemoireSortie() {
		return memoireSortie;
	}
	
	
}
