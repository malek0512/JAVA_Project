package port;

import composant.$Composant;

/*
attribut :
bool valide
methode :


 */
public class In extends $Port {

	private $Composant A;
	
	/* Constructeurs */
	public In($Composant A) {
		super();
		this.A = A;
	}

	/**
	 * renvoie le composant au quel est liée le port
	 * @return le composant liée du port
	 */
	public $Composant getComposant() {
		return A;
	}
	
	/**
	 * renvoie les informations du port in
	 */
	public String toString() {
		return "PortE n°" + Numero + " est " + Valide + " a l'etat " + Value;
	}
	
	/**
	 * renvoie les informations du port selon la grammaire 
	 */
	public String toString2() //y a n'y a rien a decrire au niveau des ports d'entrée 
	{
//		// de la forme : "-># <numero du port sortie> ( <numero du composant dest> # <numero du port in dest>, ...)
//		String res = new String("#" + this.Numero + "(");
////		if (ListPE.get(0).getComposant()!=null)
//			res = res.concat("" + ListPE.get(0).getComposant().getNumero() + "#" + ListPE.get(0).getNumero());
////		else
////			res = res.concat("" + this.getNumero() + "#" + ListPE.get(0).getNumero());
//		
//		//il y avais au moins un element dans la liste in
//		for(int i=1;i<ListPE.size(); i++){//on rajoute le reste, avec un "," en debut :D
////			if (ListPE.get(i).getComposant()!=null)
//				res = res.concat("," + ListPE.get(i).getComposant().getNumero() + "#" + ListPE.get(i).getNumero());
////			else
////				res = res.concat("," + this.getNumero() + "#" + ListPE.get(i).getNumero());
//		}
//		res = res.concat(")");
		return "";//res;
	}
}
