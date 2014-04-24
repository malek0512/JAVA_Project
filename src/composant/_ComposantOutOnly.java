package composant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import port.Out;


public interface _ComposantOutOnly extends _Composant{
	
	public List<Out> arraySorties = new ArrayList<Out>();
	
	
	  /**
	   * revoie le nombre de sortie(s) du composant
	   */  
	public int nbSorties();
	
	
	  /**
	   * retourne l'ensemble des sorties du composant
	   * @require !(this instanceof Recepteur)
	   * @return un tableau de Port
	   */
	public List<Out> sortieList();

	  /**
	   * @return true si tout les ports ENTREE sont activé
	   */
	public boolean estExecutable(); 
}
