package composant;

import java.util.ArrayList;
import java.util.List;

import port.In;

/*
 au moins un port dans liste de entre
 aucun port dans liste sortie
 */
public abstract class $Recepteur implements _Composant {
	
	  String Nom;
	  int idComposant;
	  private List<In> Ports_Entre;
	
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
		  this.idComposant = idComposant;
		  Ports_Entre = new ArrayList<In>();
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
	   * vérifie si chaque ports du composant élémentaire est connecté
	   */
	  public boolean estExecutable(){
		  
		  int i=0;
		  
		  while(i<Ports_Entre.size() && Ports_Entre.get(i).getValide()==true)
		  {
			  i++ ;
		  } 	
		  
		  return (i>=Ports_Entre.size());
	  }
	    
	  public List<In> in(){
		  return Ports_Entre;
	  }
}