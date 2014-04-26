package circuit;

import java.util.LinkedList;
import java.util.List;

import port.In;

import composant.$Composant;
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

public class Ferme implements _Circuit {
	protected List<$Generateur> listGenerateur;
	protected List<$Composant> listComposant;
	protected List<$Recepteur> listRecepteur;
	
	public Ferme(){
		listGenerateur = new LinkedList<$Generateur>();
		listComposant = new LinkedList<$Composant>();  
		listRecepteur = new LinkedList<$Recepteur>();
	}
	
	public void addGenerateur ($Generateur g){
		if (! listGenerateur.contains(g))
			listGenerateur.add(g);
	}
	
	public void addRecepteur ($Recepteur r){
		if (! listRecepteur.contains(r))
			listRecepteur.add(r);
	}
	public void addComposant ($Composant c){
		if (! listComposant.contains(c))
			listComposant.add(c);
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
	public boolean estExecutableCircuit($Composant c, boolean OK){
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
			i++;
		}
		
		return OK;
	}
	public void execute($Composant c){
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
		}
	}
}
