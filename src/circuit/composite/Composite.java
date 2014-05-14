package circuit.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jus.util.assertion.Require;
import circuit._Ouvert;
import port.In;
import port.Out;
import composant.$Composant;
import composant.Couple;

public class Composite extends $Composant implements _Ouvert {
	// Attributs 
	private List<$Composant> ListComposant; //liste des composant
	private int date =0;
	private Boolean executable = true; // execution == false ssi il existe un composant appartenant a la liste des composant, ne possédent pas de sorties 
	private List<In> arrayEntreesInterieure; //list des port in pour les composant interne
	private List<Out> arraySortiesInterieure; //list des port out pour les composant interne 
	private List<List<Couple>> memoireSortiesInterieur;
	
	/**
	 * Constructeur Composite avec nom, numero, nbEntre et nbSortie
	 * @param nom : nom du composite
	 * @param numero : numero du composite
	 * @param nbEntreeMax : nombre d'entre
	 * @param nbSortieMax : nombre de sortie
	 */
	public Composite (String nom, int numero, int nbEntreeMax, int nbSortieMax){
		super(nom,numero,nbEntreeMax,nbSortieMax);
		arraySortiesInterieure = new ArrayList<Out>(nbEntreeMax);
		arrayEntreesInterieure = new ArrayList<In>(nbSortieMax);
		ListComposant= new ArrayList<$Composant>();
		memoireSortiesInterieur = new LinkedList<List<Couple>>();
		
		/* Creation d'un port d'entré interieur pour chaque port de sortie exterieur */
		for (int i = 0; i < nbSortieMax; i++) {
			arrayEntreesInterieure.add(new In(null));
			arrayEntreesInterieure.get(i).setNumero(i);
		}
//		spreadInInComposite();
		
		/* Creation d'un port de sortie interieur pour chaque port d'entré exterieur */
		for (int i = 0; i < nbEntreeMax; i++) {
			arraySortiesInterieure.add(new Out());
			arraySortiesInterieure.get(i).setNumero(i);
			memoireSortiesInterieur.add(new LinkedList<Couple>());
		}
//		spreadOutOutComposite();
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
			((Composite) A).arraySortiesInterieure.get(noPortSortie).connect(PortE); 
		} else if (B instanceof Composite){
			In PortE = ((Composite) B).arrayEntreesInterieure.get(noPortEntree);
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
	 * Propage les sortie interieures aux sortie exterieures du composite  
	 */
	protected void spreadOutOutComposite(){
		for (int i = 0; i < nbSortieMax; i++) {
			In PortE = arrayEntreesInterieure.get(i);
			arraySorties.get(i).setValide(PortE.getValide());
			arraySorties.get(i).setValue(PortE.getValue());
			
			// Transmet l'information des arraySortiesInterieurs a l'interieur des port de composants
			arraySorties.get(i).connectall();
		}
	}

	/**
	 * Propage les entrees exterieures du composite aux entrees interieures
	 */
	protected void spreadInInComposite(){
		for (int i = 0; i < nbEntreeMax; i++) {
			In PortE = arrayEntrees.get(i);
			arraySortiesInterieure.get(i).setValue(PortE.getValue());
			arraySortiesInterieure.get(i).setValide(PortE.getValide());
			
			// Transmet l'information des arraySorties aux ports des composants exterieurs
			arraySortiesInterieure.get(i).connectall();
		}
	}
	
	/**
	 *Fonction auxiliaire du tri topologique
	 * @param Un Composant A
	 * @return la liste des composants adjacent au composant A
	 */
	private LinkedList<$Composant> adja ($Composant A){
		LinkedList<$Composant> res = new LinkedList<$Composant>();
		$Composant T;
		for (int i=0;i<A.nbSorties();i++){
			for(int j=0;j<A.sortieList().get(i).getListConnexion().size();j++){
				T = A.sortieList().get(i).getListConnexion().get(j).getComposant();
				if (! res.contains(T) && T!=null )
					res.add(T);
			}
		}
		return res;
	}
	
	/**
	 * Implemente le Parcours en Profondeur d'un graphe
	 * @param A
	 * @param PortS
	 */
	private void PP(Composite G){
		$Composant u;

		for(int i=0; i<G.ListComposant.size(); i++){
			u = G.ListComposant.get(i);
			if (u.getC()==couleur.BLANC){
				Visite_PP(G,u);
				
			}
		}

	}
	
	/**
	 * Implemente le Parcours en Profondeur d'un graphe
	 * @param Composite G qui constitue le graphe
	 * @param $Composant u qui reresente le sommet par lequel on effectue la descente dans le graphe
	 */
	private void Visite_PP(Composite G,$Composant u){
		List<$Composant> adj;
		$Composant v;
		date++;
		u.setC(couleur.GRIS);
		u.setDebut(date);
		
		adj = adja(u);
		
		// S'il existe un composant avec aucune sortie hormis un recepteur, alors le composite n'est pas executable 
		if (adj.size()==0 && !(u instanceof composant.recepteur.$Recepteur))
			executable = executable && false;
		
		for (int i=0; i<adj.size();i++){
			v = adj.get(i);
			if (v.getC()==couleur.BLANC){
				Visite_PP(G,v);
			}
		}
		u.setC(couleur.NOIR);
		date++;
		u.setFin(date);
	}
	
	/**
	 * Effectue le tri topoliguqe des composants pour une execution plus rapide
	 * @ensure listComposant est trié selon l'ordre topologique d'execution 
	 */
	protected void triTopologique(){
		PP(this);
		Collections.sort(ListComposant);
	}
	
	/**
	 * Vraie si le composite est executable
	 * @return True ssi le composite est executable 
	 */
	public boolean estExecutable(){
		triTopologique();
		return executable;
	}
	
	// ***********************************************
	// Accesseurs et Getteurs						//
	// **********************************************
		
	/**
	 * renvoie le nombre de composant
	 * @return Nombre de composant
	 */
	public int nbComposant(){
		return ListComposant.size();
	}

	/**
	 *  Ajoute un composant a la liste des composants, sans connection
	 *  @ensure : le composant fait partie du composite
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
	
	/**
	 * @return the listComposant
	 */
	public List<$Composant> getListComposant() {
		return ListComposant;
	}

	/**
	 * @return the arraySortiesInterieure
	 */
	public List<Out> getArraySortiesInterieure() {
		return arraySortiesInterieure;
	}

	/**
	 * @return the arrayEntreesInterieure
	 */
	public List<In> getArrayEntreesInterieure() {
		return arrayEntreesInterieure;
	}

	/**
	 * Ajoute la connexion de l'entree du composite, a une liste memoireSortieInterieur, sans effectuer la connexion
	 * @ensure la connexion appartient a la liste
	 */
	public void addSortieInterieur(int numeroSortie, int numeroComposant, int numeroEntreeComposant){
		memoireSortiesInterieur.get(numeroSortie).add(new Couple(numeroComposant,numeroEntreeComposant));
	}
	
	// ***********************************************
	// Methodes implémentées issuent de l'heritage //
	// **********************************************

	/**
	 * renvoie le nombre d'entrée libre
	 * @return le nombre d'entrée libre 
	 */
	public int nbPortEntreeLibre() {
		return nbEntreeMax - nbEntrees();
	}
	
	/**
	 * renvoie le nombre de sortie libre
	 * @return le nombre de sortie libre 
	 */
	public int nbPortSortieLibre() {
		return nbSortieMax - nbSorties();
	}

	/**
	 * execute le composite
	 * @ensure l'execution de chaque composant selon l'ordre topologique
	 */
	public void execute() {	
		if (estExecutable()){
			spreadInInComposite();
			for(int i=0;i<nbComposant();i++){
				ListComposant.get(i).execute();
			}
			spreadOutOutComposite();

		} else {
			throw new Require ("Attention le circuit n'est pas executable");
		}
	}

	/**
	 * retourne les infos du composite
	 */
	public String toString() {
		String res = "Voici la liste des composant du composite :\n";
		for(int i=0; i<nbComposant();i++)
			res = res + ListComposant.get(i).toString() + "\n";
		return res;
	}
	
	/**
	 * retourne les infos du composite selon la grammaire
	 */
	public String toString2(){
		composant.transformateur.$Transformateur tr;
		composant.generateur.$Generateur ge;
		composant.recepteur.$Recepteur re;
		String res = "\n";		
		for(int i=0;i<this.ListComposant.size();i++)
		{
			if (this.ListComposant.get(i) instanceof composant.transformateur.$Transformateur)
				{
				tr = (composant.transformateur.$Transformateur) (this.ListComposant.get(i));
				res += tr.toString2() + "\n";
				}
			else if (this.ListComposant.get(i) instanceof composant.generateur.$Generateur)
			{
				ge = (composant.generateur.$Generateur) this.ListComposant.get(i);
				res += ge.toString2() + "\n";
			}
			else if (this.ListComposant.get(i) instanceof composant.recepteur.$Recepteur)
			{
				re = (composant.recepteur.$Recepteur) this.ListComposant.get(i);
				res += re.toString2() + "\n";
			}
		}
		
		res+= "[";
		for(int i=0; i<this.arrayEntrees.size();i++)//chaque entree
		{
			res+= "E" + this.arrayEntrees.get(i).toString2() + ";";
		}
		for(int i=0; i<this.arraySorties.size();i++)//chaque sortie
		{	
			res+= "S" + this.arraySorties.get(i).toString2() + ";";
		}
		res+= "]\n";
		return res;
	}
	
	/**
	 * retourne les infos pour le debuggage
	 */
	public String toDebug() {
		String res = "\n Composite PE et PS: \n" + "InIn :" + arrayEntrees.toString() + "\n"+ arraySortiesInterieure.toString();
		res += "\n OutOut :" + arraySorties.toString()+ "\n"+arrayEntreesInterieure.toString();
		res = "Voici la liste des composant:\n";
		for(int i=0; i<nbComposant();i++)
			res += ListComposant.get(i).toDebug() + "\n";
		
		return res;
	}


	/**
	 * renvoie le composant dont le numero est donnée
	 * @param numero numero du composant chercher
	 * @return le composant de numero numero, null il n'est pas dans le composite
	 */
	private $Composant findComposant(int numero){
		for(int i=0;i<ListComposant.size();i++){
			if (ListComposant.get(i).getNumero() == numero)
				return ListComposant.get(i);
		}
		return null;
		
	}
	
	/**
	 * Connecte tout les composantselon la liste mémoire
	 */
	public void connectAllFromList() {
		$Composant comp, compi;
		List memoire, sortie;
		Couple c; 
		
		// COnnecte les composant entre eux
		for(int i=0; i<nbComposant(); i++){
			comp = ListComposant.get(i);
			memoire = comp.getMemoireSortie();
			for(int j=0; j<comp.nbSorties();j++){
				sortie = (List) memoire.get(j);
				for(int k=0; k<sortie.size();k++){
					c = (Couple) sortie.get(k);
					// Si c.x == 0 alors il s'agit d'une entreeInterieur du composite
					if (c.x == 0){ 
						connect(comp, j, this, c.y);
					} else {
						compi = findComposant(c.x);
						connect(comp, j, compi, c.y);
					}
				}
			}
		}
		
		// COnnecte les sorties Interieures aux composants interieurs
		for(int i=0; i<nbEntrees();i++){
			memoire = memoireSortiesInterieur.get(i);
			for(int j=0; j<memoire.size();j++){
				c = (Couple) memoire.get(j);
				compi = findComposant(c.x);
				connect(this,i,compi,c.y);
			}
		}
	}
			
}