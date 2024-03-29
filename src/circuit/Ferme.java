package circuit;

import java.util.LinkedList;
import java.util.List;

import circuit.composite.Composite;
import port.In;
import jus.util.assertion.*;
import composant.$Composant;
import composant.Couple;
import composant.generateur.$Generateur;
import composant.recepteur.$Recepteur;
/*
 	--Invariant--
Aucune connexion est libre (entrer ou sortie)


	--Attribut--
Un circuit Ouvert et des 
Liste de composants
	--Methode--

 */
import composant.recepteur.Led;


/**
 * Invariant : N'a aucune connexion libre
 */
public class Ferme implements _Circuit {
	protected List<$Generateur> listGenerateur; //list de generateur
	protected List<$Composant> listComposant; //list des composants composite et tranformateur
	protected List<$Recepteur> listRecepteur; //list des recepteur
	private String Nom;
	
	/**
	 * Constructeur, initialise le circuit ferme sans aucun composant.
	 */
	public Ferme(){
		listGenerateur = new LinkedList<$Generateur>();
		listComposant = new LinkedList<$Composant>();  
		listRecepteur = new LinkedList<$Recepteur>();
	}
	
	/**
	 * vrai si comp est dans le circuit
	 * @param comp
	 * @return vrai si comp est dans le circuit
	 */
	public boolean estDansCircuit($Composant comp)
	{
		return (listGenerateur.contains(comp) || this.listComposant.contains(comp) || this.listRecepteur.contains(comp));
	}
	
	/**
	 * ajout un generateur, sauf si il est déja dans la liste
	 * @require : $Composant.getNumero() unique  
	 * @ensure : estDansCircuit(g)==true
	 */
	public void addGenerateur ($Generateur g){
		if (!listGenerateur.contains(g)){
			listGenerateur.add(g);
		}
	}
	
	/**
	 * ajout d'un recepteur, sauf si il est déja dans la liste
	 * @param r un recepteur
	 * @ensure estDansCircuit(r)==true
	 */
	public void addRecepteur ($Recepteur r){
		if (! listRecepteur.contains(r)){
			listRecepteur.add(r);
		}
	}

	/**
	 * ajoute le composant au circuit
	 * @param c
	 * @ensure estDansCircuit(c)==true
	 */
	public void addComposant ($Composant c){
		if (c instanceof $Recepteur)
			this.addRecepteur(($Recepteur) c);
		else if (c instanceof $Generateur)
			this.addGenerateur(($Generateur) c);
		else if (! listComposant.contains(c))
		{
			if (! listComposant.contains(c))
			listComposant.add(c);
		}
	}	
	
	/**
	 * effectue la connection entre A et B par les numeros de connexion donné
	 * @param A composant source
	 * @param noPortSortie numero du port de sortie de A
	 * @param B composant destination
	 * @param noPortEntree numero du port d'entre de A
	 */
	public void connect($Composant A, int noPortSortie, $Composant B, int noPortEntree){
			if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
				In PortE = B.entreeList().get(noPortEntree);
				A.sortieList().get(noPortSortie).connect(PortE); 
				// RMQ : PortSortie.connect se charge de modifié PortEntre.Value et PortEntre.Valide=1 
		}
	}
	
	/**
	 * effectue la déconnection entre A et B par les numeros de connexion donné
	 * @param A composant source
	 * @param noPortSortie numero du port de sortie de A
	 * @param B composant destination
	 * @param noPortEntree numero du port d'entre de A
	 */
	public void deconnect($Composant A, int noPortSortie, $Composant B, int noPortEntree){
		if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
			In PortE = B.entreeList().get(noPortEntree);
			A.sortieList().get(noPortSortie).deconnect(PortE); 
			// RMQ : PortSortie.donnect se charge de modifié PortEntre.Valide=0 et supprimer la 1ere occurrence de PortE dans la liste du port de sortie
		}
	}
	
	/*
	 * fonction intermediaire
	 */
	private boolean estExecutableCircuit($Composant c, boolean OK){
		OK = c.estExecutable();
		int i=0;
		while (i<c.nbSorties() && OK){
			int j=0;
			while (j<c.sortieList().get(i).getListConnexion().size() && OK){
				OK = OK && estExecutableCircuit(c.sortieList().get(i).getListConnexion().get(j).getComposant(), OK);
				j++;
			}
			i++;
		}
		return OK;
	}
	
	/**
	 * vrai si le circuit est executable
	 * @return true si le circuit est executable
	 */
	public boolean estExecutable(){
		boolean OK = true;
		int i=0;
		while (i < listGenerateur.size() && OK){
			OK = OK && listGenerateur.get(i).estExecutable();
			OK = OK && estExecutableCircuit(listGenerateur.get(i), OK);
			i++;
		}	
		return OK;
	}
	
	/*
	 * fonction intermediaire
	 */
	private void execute($Composant c){
		c.execute();
		int i=0;
		while (i<c.nbSorties()){
			int j=0;
			while (j<c.sortieList().get(i).getListConnexion().size()){
				execute(c.sortieList().get(i).getListConnexion().get(j).getComposant());
				j++;
			}
			i++;
		}
	}
	
	/**
	 * execute le circuit
	 * @ensure : tout les composant on effectuer calcul(), et la valeur des recepteur est mis a jour en fonction des connection
	 * @require : this.estExecutable()
	 */
	public void execute() {
		if (estExecutable()){
			for(int i=0;i<listGenerateur.size();i++){
				execute(listGenerateur.get(i));
			}
		} else {
			throw new Require ("Attention le circuit n'est pas executable"); 
		}
	}

	/**
	 * return les information sur le circuit
	 */
	public String toString(){
		String res = "\n";
		for(int i=0;i<listGenerateur.size();i++)
			res += listGenerateur.get(i).toString() + "\n";
		res += "\n";
		for(int i=0;i<listRecepteur.size();i++)
			res += listRecepteur.get(i).toString() + "\n";
		return res;
	}
	
	/**
	 * return les informations sur le circuit au format de la grammaire
	 */
	public String toString2(){
		Composite comp;
		composant.transformateur.$Transformateur tr;
		String res = "\n";
		for(int i=0;i<listGenerateur.size();i++)
			res += listGenerateur.get(i).toString2() + "\n";
		res += "\n";
		for(int i=0;i<listComposant.size();i++)
		{
			if (listComposant.get(i) instanceof Composite)
				{
				comp = (Composite) (listComposant.get(i));
				res += comp.toString2() + "\n";
				}
			else if (listComposant.get(i) instanceof composant.transformateur.$Transformateur)
			{
				tr = (composant.transformateur.$Transformateur) listComposant.get(i);
				res += tr.toString2() + "\n";
			}
		}
		res += "\n";
		for(int i=0;i<listRecepteur.size();i++)
			res += listRecepteur.get(i).toString2() + "\n";
		return res;
	}
	
	/**
	 * return les informations pour le debuggage 
	 */
	public String toDebug(){
		String res = "\n";
		for(int i=0;i<listGenerateur.size();i++)
			res += listGenerateur.get(i).toDebug() + "\n";
		res += "\n";
		for(int i=0;i<listComposant.size();i++)
			res += listComposant.get(i).toDebug() + "\n";
		res += "\n";
		for(int i=0;i<listRecepteur.size();i++)
			res += listRecepteur.get(i).toDebug() + "\n";
		return res;
	}
	
	/**
	 * modifie le nom du circuit
	 * @param nom du circuit
	 * @ensure le circuit a pour nom nom
	 */
	public void setNom(String nom) {
		Nom = nom;
	}

	/**
	 * renvoie le composant, si il n'est pas présent renvoie null
	 * @param numero du composant desirer
	 * @return le composant si le composant est dans le circuit, sinon renvoi null  
	 */
	private $Composant findComposant(int numero){
		for(int i=0;i<listGenerateur.size();i++){
			if (listGenerateur.get(i).getNumero() == numero)
				return listGenerateur.get(i);
		}
		
		for(int i=0;i<listComposant.size();i++){
			if (listComposant.get(i).getNumero() == numero)
				return listComposant.get(i);
		}
		
		for(int i=0;i<listRecepteur.size();i++){
			if (listRecepteur.get(i).getNumero() == numero)
				return listRecepteur.get(i);
		}
		// Si le numero de composant n'est dans aucune liste alors on retourne null
		return null;
		
	}	

	/**
	 * effectue la connection de tout les composant
	 */
	public void connectAllFromList() {
		$Composant comps, compi;
		List memoire, sortie;
		Couple c; 
		// COnnecte les composant entre eux		
		for(int i=0;i<listGenerateur.size();i++){
			comps = listGenerateur.get(i);;
			memoire = comps.getMemoireSortie();
			for(int j=0; j<comps.nbSorties();j++){
				sortie = (List) memoire.get(j);
				for(int k=0; k<sortie.size();k++){
					c = (Couple) sortie.get(k);
					compi = findComposant(c.x);
					connect(comps, j, compi, c.y);
				}
			}
		}
		
		for(int i=0;i<listComposant.size();i++){
			comps = listComposant.get(i);;
			memoire = comps.getMemoireSortie();
			for(int j=0; j<comps.nbSorties();j++){
				sortie = (List) memoire.get(j);
				for(int k=0; k<sortie.size();k++){
					c = (Couple) sortie.get(k);
					compi = findComposant(c.x);
					connect(comps, j, compi, c.y);
				}
			}
		}
		
		for(int i=0;i<listRecepteur.size();i++){
			comps = listRecepteur.get(i);;
			memoire = comps.getMemoireSortie();
			for(int j=0; j<comps.nbSorties();j++){
				sortie = (List) memoire.get(j);
				for(int k=0; k<sortie.size();k++){
					c = (Couple) sortie.get(k);
					compi = findComposant(c.x);
					connect(comps, j, compi, c.y);
				}
			}
		}
	}
}
