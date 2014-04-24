package composant;

import java.util.ArrayList;
import java.util.List;

import port.Out;

/*
juste a implementer l'interface
et ajouter le constructeur correctement

invariant :
a au moins un port de sortie //verifier le sujet si il n'y aurais pas plutot qu'un seul port de sortie 
		(rappel : ca empeche pas d'avoir plein de composant connecter)
n'a aucun port d'entre

*/
public abstract class $Generateur implements _Composant {
	
	  String Nom;
	  int idComposant;
	  private List<Out> Ports_Sortie;
	
	  /**
	   * Constructeur d'un generateur
	   * @ensure les ports du composant ne seront pas connectés
	   * @param nom nom du composant à creer
	   * @param idComposant identifiant du composant à creer
	   * @param nbSort nombre de sorties du composant
	   * 
	   */
	  public $Generateur(String nom, int idComposant, int nbSort){
		  this.Nom=nom;
		  this.idComposant = idComposant;
		  Ports_Sortie = new ArrayList<Out>();
	  }
	
	  /**
	   * identifiant du composant
	   */
	  public int IdComposant(){
		  return idComposant;
	    }
	  
	  /**
	   * revoie le nombre de sortie(s) du composant
	   */  
	  public int nbSorties(){
	    	return Ports_Sortie.size();
	  }

	  /**
	   * vérifie si chaque ports du composant élémentaire est connecté
	   */
	  public boolean estExecutable(){
		  
		  int j=0;
		  
		  while(j<=nbSorties() && Ports_Sortie.get(j).getValide()==true)
		  {
			  j++ ;
		  }
		  
		  return (j>=Ports_Sortie.size());
	  }
	  
	  public List<Out> out(){
		  return Ports_Sortie;
	  }
}