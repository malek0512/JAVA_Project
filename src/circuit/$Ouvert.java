package circuit;

import java.util.LinkedList;
import java.util.List;

import composant._Composant;

/*
--Invariant--
A au moins une connexion libre

--Attribut--

--Methode--
retourner liste des port d'entrer libre
retourner liste des port de  sortie libre


*/

public abstract class $Ouvert implements _Circuit {
	
	protected List<_Composant> Listcomposant;
	public $Ouvert(){
		Listcomposant = new LinkedList<_Composant>();  
	}	
	

}
