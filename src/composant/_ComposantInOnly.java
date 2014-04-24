package composant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import port.In;

public interface _ComposantInOnly extends _Composant{
	public List<In> arrayEntrees = new ArrayList<In>();
	
	/**
	 * @return le nombre d'entree(s) du composant
	 */
	  public int nbEntrees();
	

	  
	  /**
	   * retourne l'ensemble des entrees du composant
	   * @require !(this instanceof Generateur)
	   * @return un tableau de Port
	   */
	  public List<In> entreeList();

}
