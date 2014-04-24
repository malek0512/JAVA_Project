package circuit;

import java.util.LinkedList;
import java.util.List;

import composant._Composant;
/*
 	--Invariant--
Aucune connexion est libre (entrer ou sortie)

	--Attribut--

	--Methode--

 */

public abstract class $Ferme implements _Circuit {
	protected List<_Composant> Listcomposant;
	public $Ferme(){
		Listcomposant = new LinkedList<_Composant>();  
	}
	
	
	
}
