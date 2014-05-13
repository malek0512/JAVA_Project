package circuit;

import java.util.LinkedList;
import java.util.List;

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

public class Ferme implements _Circuit {
	protected List<$Generateur> listGenerateur;
	protected List<$Composant> listComposant;
	protected List<$Recepteur> listRecepteur;
//	protected Map<Integer,List<Couple>> memoireSortie;
	private String Nom;
	
	public Ferme(){
		listGenerateur = new LinkedList<$Generateur>();
		listComposant = new LinkedList<$Composant>();  
		listRecepteur = new LinkedList<$Recepteur>();
//		memoireSortie = new HashMap<Integer,List<Couple>>();
	}
	
	/**
	 * @require $Composant.getNumero() unique  
	 */
	public void addGenerateur ($Generateur g){
		if (! listGenerateur.contains(g)){
			listGenerateur.add(g);
//			if ( ! memoireSortie.containsKey(g.getNumero()))
//				memoireSortie.put(g.getNumero(), new LinkedList<Couple>());
//			else 
//				throw new Require("Attention Numero déja attribué")  ;
		}
	}
	
	public void addRecepteur ($Recepteur r){
		if (! listRecepteur.contains(r)){
			listRecepteur.add(r);
//			if ( ! memoireSortie.containsKey(r.getNumero()))
//				memoireSortie.put(r.getNumero(), new LinkedList<Couple>());
//			else 
//				throw new Require("Attention Numero déja attribué")  ;
		}
	}
	public void addComposant ($Composant c){
		if (! listComposant.contains(c)){
			listComposant.add(c);
//			if ( ! memoireSortie.containsKey(c.getNumero()))
//				memoireSortie.put(c.getNumero(), new LinkedList<Couple>());
//			else 
//				throw new Require("Attention Numero déja attribué")  ;
		}
	}	
	
	public void connect($Composant A, int noPortSortie, $Composant B, int noPortEntree){
			if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
				In PortE = B.entreeList().get(noPortEntree);
				A.sortieList().get(noPortSortie).connect(PortE); 
				// RMQ : PortSortie.connect se charge de modifié PortEntre.Value et PortEntre.Valide=1 
		}
	}
	
	public void deconnect($Composant A, int noPortSortie, $Composant B, int noPortEntree){
		if (A.nbSorties()>noPortSortie && B.nbEntrees()>noPortEntree){ 
			In PortE = B.entreeList().get(noPortEntree);
			A.sortieList().get(noPortSortie).deconnect(PortE); 
			// RMQ : PortSortie.donnect se charge de modifié PortEntre.Valide=0 et supprimer la 1ere occurrence de PortE dans la liste du port de sortie
		}
	}
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
	
	public void execute() {
		if (estExecutable()){
			for(int i=0;i<listGenerateur.size();i++){
				execute(listGenerateur.get(i));
			}
		} else {
			throw new Require ("Attention le circuit n'est pas executable"); 
		}
	}

	public String toString(){
		String res = "\n";
		for(int i=0;i<listGenerateur.size();i++)
			res += listGenerateur.get(i).toString() + "\n";
		res += "\n";
		for(int i=0;i<listRecepteur.size();i++)
			res += listRecepteur.get(i).toString() + "\n";
		return res;
	}
	
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
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		Nom = nom;
	}

//	/**
//	 * Mémorise la connexion sans auncune connexion au composant
//	 */
//	public void addSortie(int numeroSortie, int numeroComposant, int numeroEntreeComposant){
//		if (memoireSortie.)
//			memoireSortie.add(numeroSortie, new LinkedList<Couple>());
//			memoireSortie.get(numeroSortie).add(new composant.Couple(numeroComposant,numeroEntreeComposant));
//	}
	
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
