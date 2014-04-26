package port;

import java.util.LinkedList;
import java.util.List;

import jus.util.assertion.Require;

/*
attribut :
liste port entree

methode :
void connect
	- qui sera appliquer dès l'ajout d'un element dans la liste
	- sert a : modifier la valeur du port d'entre
void deconnect
	- transmettre au port d'entrees qu'il n'est plus connecter a eux si ils sont avaible
	- supprimer de la liste le port en question

 */
public class Out extends $Port {

	/* Attributs */
	private List<In> ListPE;
	
	/* Constructeurs */
	public Out() {
		super();
		ListPE = new LinkedList<In>();
	}

	public Out(String nom, int numero) {
		this.Numero=numero;
	}

	/* Méthodes */
	/**
	 * @ensure transmet la valeur de this.Value a PortE (que le port est branché et sa valeur initialisée)
	 * @esnure PortE.value = this.Value && PortE.Valide == true && PortE appartient this.getListPE()  
	 * @requrie !(PortE != null)
	 * @param PortE
	 */
	public void connect(In PortE){
		PortE.Valide = true;
		PortE.Value = this.Value;
		ListPE.add(PortE);
	}

	/**
	 * @ensure transmet la valeur de this.Value à sur l'ensemble des PortE
	 * @ensure this.getListPE().get(i).value == this.value && this.getListPE().get(i).valide == true    
	 */
	public void connectall(){
		for(int i=0;i<ListPE.size(); i++){
			ListPE.get(i).Valide=true;
			ListPE.get(i).Value=this.Value;
		}	
	}
	
	/**
	 * @ensure que le port n'est plus branché
	 * @requrie !(PortE != null) && !(PortE.valide == false)
	 * @param PortE
	 */
	public void deconnect(In PortE){
		PortE.Valide = false;
		ListPE.remove(PortE);
	}
	
	/**
	 * @require !(PortE != null)
	 * @ensure que le PortE est brancher a la sortie :  
	 * @param PortE
	 */
	public void addConnexion(In PortE, int numeroComposant){
		if (PortE != null){
				PortE.Valide = true;
				ListPE.add(PortE);
		} else 
			throw new Require("! (PortE != null)");
	}
	
	public String toString() {
		return "PortS n°" + Numero;
	}

	/* Accesseur pour les invariants et requires */
	/**
	 * @return the listPE
	 */
	public LinkedList<In> getListConnexion() {
		return new LinkedList<In>(ListPE);
	}

}