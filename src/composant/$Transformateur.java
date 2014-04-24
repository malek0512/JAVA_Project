package composant;

import java.util.ArrayList;
import java.util.List;

import port.In;
import port.Out;

/*
 au moins un port dans chaque liste
 */

public abstract class $Transformateur implements _Composant {
	
	  String Nom;
	  int idComposant;
	  private List<In> Ports_Entre;
	  private List<Out> Ports_Sortie;
	
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
		  this.idComposant = idComposant;
		  Ports_Entre = new ArrayList<In>();
		  Ports_Sortie = new ArrayList<Out>();
	  }
	
	  /**
	   * identifiant du composant
	   */
	  public int IdComposant(){
		  return idComposant;
	    }
	
	  /**
	   * revoie le nombre d'entree(s) du composant
	   */
	  public int nbEntrees(){
	    	return Ports_Entre.size();
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
		  
		  int i=0;
		  int j=0;
		  
		  while(i<Ports_Entre.size() && Ports_Entre.get(i).getValide()==true)
		  {
			  i++ ;
		  } 	
		  
		  while(j<=nbSorties() && Ports_Sortie.get(i).getValide()==true)
		  {
			  j++ ;
		  }
		  
		  return (i>=Ports_Entre.size() && j>=Ports_Sortie.size());
	  }
	  
	  public List<In> in(){
		  return Ports_Entre;
	  }
	  
	  public List<Out> out(){
		  return Ports_Sortie;
	  }
}
