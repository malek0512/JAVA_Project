package composant;

import java.util.List;

import port.In;
import port.Out;

/*
--Invariant--
a au moins un port
--Attribut--
String : Nom;
Int : Numero;
liste de port entrer 
liste de port sortie

--Methode--
void action (effectue sont action si executable()==true et envoie la valeur dans son port de sortie)
- generateur, envoyer info sur les ports sortie
- transfo, si port entrer avaiable, envoyer info sur port sortie
- recepeteur, si port entrer avaiable, faire un truc, genre allumer une dell.
void connect
- pour connecter un composant a un autre composant
- on apelle connect d'un seul composant qui recoit un nouveau port d'entree
void deconnecter
- pour deconcecter un composant a un autre composant
bool executable
- renvoie true si tout les ports ENTREE sont up
*/
/*
	--Invariant--
a au moins un port
	--Attribut--
String : Nom;
Int : Numero;
liste de port entrer 
liste de port sortie

	--Methode--
void action (effectue sont action si executable()==true et envoie la valeur dans son port de sortie)
	- generateur, envoyer info sur les ports sortie
	- transfo, si port entrer avaiable, envoyer info sur port sortie
	- recepeteur, si port entrer avaiable, faire un truc, genre allumer une dell.
void connect
	- pour connecter un composant a un autre composant
	- on apelle connect d'un seul composant qui recoit un nouveau port d'entree
void deconnecter
	- pour deconcecter un composant a un autre composant
bool executable
	- renvoie true si tout les ports ENTREE sont up
 */

/** 
 *  invariant :nbEntrees + nbSorties > 0
 */
public interface _Composant{
	
/**
 * revoie le nombre d'entree(s) du composant
 */
  public int nbEntrees();
  
  /**
   * revoie le nombre de sortie(s) du composant
   */  
  public int nbSorties();
  
  /**
   * tout composant à un id quand il est crée
   * @return identificateur du composant
   */
  public int IdComposant();
  
  /**
   * retourne l'ensemble des sorties du composant
   * @require !(this instanceof Recepteur)
   * @return un tableau de Port
   */
  public List<Out> out();

  
  /**
   * retourne l'ensemble des entrees du composant
   * @require !(this instanceof Generateur)
   * @return un tableau de Port
   */
  public List<In> in();
  
  /**
   * vérifie si chaque ports du composant est connecté
   */
  public boolean estExecutable(); 
  
  /**
   * 
   * @return etat
   */
  public String toString();
  
}
