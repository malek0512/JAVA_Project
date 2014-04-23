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
		this.Nom=nom;
		this.Numero=numero;
	}

	/* Méthodes */
	/**
	 * @ensure (que le port est branché et sa valeur initialisée) , le port est supprimer de ListPE
	 * @requrie !(PortE != null) && !(PortE.valide == false)
	 * @param PortE
	 */
	public void connect(In PortE){
		PortE.Value = this.Value;
		PortE.Valide = true;
		addPE(PortE);
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
	 * @require !(PortE != null) && !(PortE.valide == false)
	 * @ensure que le PortE est brancher a la sortie :  
	 * @param PortE
	 */
	private void addPE(In PortE){
		if (PortE != null){
			if (PortE.Valide == true)
				ListPE.add(PortE);
			else
				throw new Require ("! (PortE.valide == false)");
		} else 
			throw new Require("! (PortE != null)");
	}
	
	@Override
	public String tooString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Accesseur pour les invariants et requires */
	/**
	 * @return the listPE
	 */
	public LinkedList<In> getListPE() {
		return new LinkedList<In>(ListPE);
	}

	
	
}