package composant.generateur;

import java.util.List;

import composant._ComposantOutOnly;

import port.In;
import port.Out;

/*
juste a implementer l'interface
et ajouter le constructeur correctement

invariant :
a au moins un port de sortie //verifier le sujet si il n'y aurais pas plutot qu'un seul port de sortie 
		(rappel : ca empeche pas d'avoir plein de composant connecter)
n'a aucun port d'entre

*/
public abstract class $Generateur implements _ComposantOutOnly {
	
	  String Nom;
	  int Numero;
	  private int nbSortieMax;
	  /**
	   * Constructeur d'un generateur
	   * @ensure les ports du composant ne seront pas connectés
	   * @param nom nom du composant à creer
	   * @param idComposant identifiant du composant à creer
	   * @param nbSort nombre de sorties du composant
	   * 
	   */
	  public $Generateur(String nom, int idComposant, int nbSortieMax){
			// Initialisation du tableau des ports d'entree et de sortie du
			// composite,
			// faute de ne pourvoir etre fait avec un contructeur dans l'interface
			// _composantOutOnly and In
			for(int i=0;i<nbSortieMax;i++){
				arraySorties.add(new Out()); 
			}
		  this.nbSortieMax = nbSortieMax;
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
	   * revoie le nombre de sortie(s) du composant
	   */  
	  public int nbSorties(){
	    	return arraySorties.size();
	  }

	  /**
	   * vérifie si chaque ports du composant élémentaire est connecté
	   */
	  public boolean estExecutable(){
		  int j=0;
		  while(j<=nbSorties() && arraySorties.get(j).getValide()==true){
			  j++ ;
		  }
		  
		  return (j>=arraySorties.size());
	  }
	  
	  public List<Out> sortieList(){
		  return arraySorties;
	  }
	  
		public String getNom() {
			return Nom;
		}
}