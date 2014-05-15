package composant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jus.util.assertion.Require;
import port.In;
import port.Out;

/**
 * invariant :nbEntrees + nbSorties > 0
 */

public abstract class $Composant implements Comparable<$Composant> {

	
	// Attributs
	protected List<Out> arraySorties; //port de sortie
	protected List<In> arrayEntrees; //port d'entre
	protected String Nom; //nom du composant
	protected int Numero; //numero du composant
	
	

	protected int nbEntreeMax; //nombre d'entre du composant
	protected int nbSortieMax; //nombre de sortie du composant
	public enum niveau {Bas,Haut}; //enum pour les valeur des composant
	protected niveau value; //valeur du composant
	public enum couleur {BLANC,GRIS,NOIR}; //enum pour les grahs
	protected couleur c; //attribut pour les graphs
	protected int debut,fin; //attribut pour les graphs
	protected List<List<Couple>> memoireSortie; //information pour les connexion
	

	/**
	 * creer le composant avec les donnée
	 * @param nom : nom du composant
	 * @param numero : numero du composant
	 * @param nbEntreeMax : nombre d'entre du composant
	 * @param nbSortieMax : nombre de sortie du composant
	 */
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
	 * renvoie le numero du composant
	 * @return Numero du composant
	 */
	public int getNumero() {
		return Numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		Numero = numero;
	}
	/**
	 * renvoie le nom du composant
	 * @return Nom du composant
	 */
	public String getNom() {
		return new String(Nom);
	}

	/**
	 * renvoie le nombre d'entree
	 * @return le nombre d'entree(s) du composant
	 */
	public int nbEntrees() {
		return arrayEntrees.size();
	}

	/**
	 * revoie le nombre de sortie(s) du composant
	 * @return le nombre de sortie du composant
	 */
	public int nbSorties() {
		return arraySorties.size();
	}

	/**
	 * retourne l'ensemble des sorties du composant
	 * @require !(this instanceof Recepteur)
	 * @return un tableau de Port
	 */
	public List<Out> sortieList() {
		return arraySorties;
	}

	/**
	 * retourne l'ensemble des entrees du composant
	 * @require !(this instanceof Generateur)
	 * @return un tableau de Port
	 */
	public List<In> entreeList() {
		return arrayEntrees;
	}

	/**
	 * vrai si le composant est executable, donc si tout ses entree son up
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
	 * renvoie la valeur du composant
	 * @return le niveau {Bas,Haut}
	 */
	public niveau getNiveau() {
		return value;
	}

	/**
	 * convertie un boolean en niveau
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
		else
			throw new Require("nbSorties()!=0");
	}

	public int compareTo($Composant o) {
		return -1*Integer.compare(this.fin, o.fin);
	}
	
	/**
	 * Abstraite car l'affichage depend de du type de composant
	 * @return String
	 */
	public abstract String toString();

	/**
	 * Fonction de debogage, permettant l'affichage des valeurs des port d'entrés et sorties
	 * @return String
	 */
	public String toDebug(){
		return Nom + " niveau " + getNiveau().toString() +":\n PE :" +entreeList().toString() + ",\n PS :" +sortieList().toString();
	}
	

	/**
	 * Info complementaire dependant de chaque composant, par exemple, une DEL renverra "Allumer" ou "Eteint"
	 * @author Alex
	 */
	protected String infoComplementaire(){ // depend de chaque composant
		return "";
	}
	/**
	 * Accesseur de l'attribut, debut, servant au tri en profondeur
	 * @return the debut
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * Mutateur de l'attribut, debut, servant au tri en profondeur
	 * @param debut the debut to set
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}

	/**
	 * Accesseur de l'attribut, fin, servant au tri en profondeur
	 * @return the fin
	 */
	public int getFin() {
		return fin;
	}

	/**
	 * Mutateur de l'attribut, fin, servant au tri en profondeur
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}

	/**
	 * Accesseur de l'attribut, c couleur, servant au tri en profondeur
	 * @return the c
	 */
	public couleur getC() {
		return c;
	}

	/**
	 * Mutateur de l'attribut, C couleur, servant au tri en profondeur
	 * @param c the c to set
	 */
	public void setC(couleur c) {
		this.c = c;
	}
	
	/**
	 * Mémorise la connexion sans auncune connexion au composant
	 * @Remarque le numeroComposant == -1 ssi il s'agit d'une entree interieure au composite 
	 * @ensure getMemoireSortie().contains ( new Couple(numeroComposant, numeroEntreeComposant))
	 * @require numeroSortie <= nbSorties() && numeroSortie >=0 
	 */
	public void addSortie(int numeroSortie, int numeroComposant, int numeroEntreeComposant){
		//if (! (numeroSortie <= nbSorties() && numeroSortie >=0))
		memoireSortie.get(numeroSortie).add(new Couple(numeroComposant,numeroEntreeComposant));
		//else
			//throw new Require("!(numeroSortie <= nbSorties() && numeroSortie >=0");
		
	}
	
	/**
	 * Retourne la liste des sorties sotckées en memoire
	 * @return the memoireSortie
	 */
	public List<List<Couple>> getMemoireSortie() {
		return memoireSortie;
	}
	
}
