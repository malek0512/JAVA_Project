package circuit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import port.In;
import port.Out;
import composant.$Composant;

/*

void connect
- pour connecter un composant a un autre composant
- on apelle connect d'un seul composant qui recoit un nouveau port d'entree

void deconnecter
- pour deconcecter un composant a un autre composant

*/
public class Composite extends $Composant implements _Ouvert {
	// Attributs 
	private List<$Composant> ListComposant;
	private List<Out> arraySortiesComposite;
	private List<In> arrayEntreesComposite;
	
	/**
	 * Constructeur Composite
	 * @param nom
	 * @param numero
	 * @param nbEntreeMax
	 * @param nbSortieMax
	 */
	public Composite (String nom, int numero, int nbEntreeMax, int nbSortieMax){
		super(nom,numero,nbEntreeMax,nbSortieMax);
		arraySortiesComposite = new ArrayList<Out>(nbEntreeMax);
		arrayEntreesComposite = new ArrayList<In>(nbSortieMax);
		ListComposant= new LinkedList<$Composant>();
		/* Creation d'un port de sortie pour chaque port d'entré exterieur */
		for (int i = 0; i < nbSortieMax; i++) {
			arrayEntreesComposite.add(new In(this));
			arrayEntreesComposite.get(i).setNumero(i);
			spreadInInComposite();
		}
		
		/* Creation d'un port d'entré pour chaque port de sortie exterieur */
		for (int i = 0; i < nbEntreeMax; i++) {
			arraySortiesComposite.add(new Out());
			arraySortiesComposite.get(i).setNumero(i);
			spreadOutOutComposite();
		}
	}
	
	/**
	 * Connecte respectivement un noPortSortie du composant A, à un noPortEntree d'un composant B
	 * @ensure A.noPortSortie.valide == B.noPortEntree.valide == 1
	 * @ensure A.entreeList() contient B.noPortEntree 
	 * @ensure A.noPortSortie.value == B.noPortEntree.value 
	 * @param A
	 * @param noPortSortie
	 * @param B
	 * @param noPortEntree
	 * @require A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree
	 */
	public void connect($Composant A, int noPortSortie, $Composant B, int noPortEntree){
		if (A instanceof Composite){
			In PortE = B.entreeList().get(noPortEntree);
			((Composite) A).arraySortiesComposite.get(noPortSortie).connect(PortE); 
		} else if (B instanceof Composite){
			In PortE = ((Composite) B).arrayEntreesComposite.get(noPortEntree);
			A.sortieList().get(noPortSortie).connect(PortE);
		}else {
			if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
				In PortE = B.entreeList().get(noPortEntree);
				A.sortieList().get(noPortSortie).connect(PortE); 
				// RMQ : PortSortie.connect se charge de modifié PortEntre.Value et PortEntre.Valide=1 
			}
		}
	}
	
	/**
	 * Connecte respectivement un noPortSortie du composant A, à un noPortEntree d'un composant B
	 * @ensure A.noPortSortie.valide == B.noPortEntree.valie == 1
	 * @ensure A.entreeList() contient B.noPortEntree
	 * @param A
	 * @param noPortSortie
	 * @param B
	 * @param noPortEntree
	 */
	public void addConnexion($Composant A, int noPortSortie, $Composant B, int noPortEntree){
		if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
			In PortE = B.entreeList().get(noPortEntree);
			A.sortieList().get(noPortSortie).connect(PortE); 
			// RMQ : PortSortie.connect se charge de modifié PortEntre.Value et PortEntre.Valide=1 
		}
	}
	
	/**
	 * Deconnecte respectivement un noPortSortie du composant A, à un noPortEntree d'un composant B
	 * @ensure A.noPortSortie.valide == B.noPortEntree.valie == 0
	 * @ensure A.entreeList() ne contient plus B.noPortEntree 
	 * @param A
	 * @param noPortSortie
	 * @param B
	 * @param noPortEntree
	 * @require A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree
	 */
	public void deconnect($Composant A, int noPortSortie, $Composant B, int noPortEntree){
		if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
			In PortE = B.entreeList().get(noPortEntree);
			A.sortieList().get(noPortSortie).deconnect(PortE); 
			// RMQ : PortSortie.donnect se charge de modifié PortEntre.Valide=0 et supprimer la 1ere occurrence de PortE dans la liste du port de sortie
		}
	}

	/**
	 * Propage les entrees exterieures du composite aux entrees interieures
	 */
	protected void spreadInInComposite(){
		for (int i = 0; i < nbSortieMax; i++) {
			In PortE = arrayEntreesComposite.get(i);
			arraySorties.get(i).connect(PortE);
		}
	}

	/**
	 * Propage les sortie interieures aux sortie exterieures du composite  
	 */
	protected void spreadOutOutComposite(){
		for (int i = 0; i < nbEntreeMax; i++) {
			In PortE = arrayEntrees.get(i);
			arraySortiesComposite.get(i).connect(PortE);
		}
	}
	/**
	 * implemente le Parcours en Largeur d'un graphe
	 * @param A
	 * @param PortS
	 */
	void PL($Composant A, Out PortS){
		
	}
	
	/**
	 * @ensure listComposant est trié selon l'ordre topologique d'execution 
	 */
	protected void triTopologique(){
		
	}

	// ***********************************************
	// Accesseurs et Getteurs						//
	// **********************************************
		
	/**
	 * @return Nombre de composant
	 */
	public int nbComposant(){
		return ListComposant.size();
	}

	/**
	 *  Ajoute un composant a la liste des composants, sans connection
	 */
	public void addComposant($Composant composant) {
		ListComposant.add(composant);
	}
	
	/**
	 *  Ajoute une liste de composant a la liste des composants, sans connection
	 */
	public void ajoutListComposants(List<$Composant> l) {
		ListComposant.addAll(l);
	}

	// ***********************************************
	// Methodes implémentées issuent de l'heritage //
	// **********************************************

	public int nbPortEntreeLibre() {
		return nbEntreeMax - nbEntrees();
	}

	public int nbPortSortieLibre() {
		return nbSortieMax - nbSorties();
	}

	/**
	 * @ensure l'execution de chaque composant selon l'ordre topologique
	 */
	public void execute() {
		if (estExecutable()){
			triTopologique();
			spreadInInComposite();
			for(int i=0;i<nbComposant();i++){
				ListComposant.get(i).execute();
			}
			spreadOutOutComposite();
		}
	}

	public String toString() {
		String res = "Voici la liste des composant:\n";
		for(int i=0; i<nbComposant();i++)
			res += ListComposant.get(i).toString() + "\n";
		return res;
	}

}