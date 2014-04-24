package composant.recepteur;

import java.util.List;

import composant._ComposantInOnly;

import port.In;

/*
 au moins un port dans liste de entre
 aucun port dans liste sortie
 */
public abstract class $Recepteur implements _ComposantInOnly {
	
	  String Nom;
	  int Numero;
	  	
	  /**
	   * Constructeur d'un recepteur
	   * @ensure les ports du composant ne seront pas connectés
	   * @param nom nom du composant à creer
	   * @param idComposant identifiant du composant à creer
	   * @param nbEnt nombre d'entrees du composant
	   * 
	   */
	  public $Recepteur(String nom, int idComposant, int nbEnt){
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
	   * vérifie si chaque ports du composant élémentaire est connecté
	   */
	  public boolean estExecutable(){
		  int i=0;
		  while(i<arrayEntrees.size() && arrayEntrees.get(i).getValide()==true){
			  i++ ;
		  } 	
		  
		  return (i>=arrayEntrees.size());
	  }
	    
	  public String getNom(){
		  return Nom;
	  }
	  public List<In> entreeList(){
		  return arrayEntrees;
	  }
	  
	  
}