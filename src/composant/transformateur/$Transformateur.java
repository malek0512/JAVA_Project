package composant.transformateur;

import java.util.List;

import composant._ComposantInOnly;
import composant._ComposantOutOnly;

import port.In;
import port.Out;

/*
 au moins un port dans chaque liste
 */

public abstract class $Transformateur implements _ComposantInOnly, _ComposantOutOnly {
	
	  String Nom;
	  int Numero;

	
	  /**
	   * Constructeur d'un transformateur
	   * @ensure les ports du composant ne seront pas connectés
	   * @param nom nom du composant à creer
	   * @param idComposant identifiant du composant à creer
	   * @param nbEnt nombre d'entrees du composant
	   * @param nbSort nombre de sorties du composant
	   * 
	   */
	  public $Transformateur(String nom, int idComposant, int nbEnt, int nbSort){
		  this.Nom=nom;
		  this.Numero = idComposant;
	  }
	
	  /**
	   * identifiant du composant
	   */
	  public int getNumero(){
		  return Numero;
	    }
	
	  /**
	   * revoie le nombre d'entree(s) du composant
	   */
	  public int nbEntrees(){
	    	return arrayEntrees.size();
	  }
	    
	  /**
	   * revoie le nombre de sortie(s) du composant
	   */  
	  public int nbSorties(){
	    	return arraySorties.size();
	  }

	  /**
	   * vérifie si chaque ports du composant élémentaire est connecté
	   */
	  public boolean estExecutable(){
		  int i=0;
		  int j=0;
		  while(i<arrayEntrees.size() && arrayEntrees.get(i).getValide()==true){
			  i++ ;
		  } 	
		  while(j<=nbSorties() && arraySorties.get(i).getValide()==true){
			  j++ ;
		  }
		  return (i>=arrayEntrees.size() && j>=arraySorties.size());
	  }
	  
	  public List<In> entreeList(){
		  return arrayEntrees;
	  }
	  
	  public List<Out> sortiesList(){
		  return arraySorties;
	  }
}
