package composant;

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
void action (effectue son action si executable()==true et envoie la valeur dans son port de sortie)
	- generateur, envoyer info sur les ports sortie
	- transfo, si port entrer avaiable, envoyer info sur port sortie
	- recepeteur, si port entrer available, faire un truc, genre allumer une dell.



bool executable 
	- renvoie true si tout les ports ENTREE sont up
	==> deplacer dans _Composant_out
 */

/** 
 *  invariant :nbEntrees + nbSorties > 0
 */
public interface _Composant{

  /**
   * Tout composant possede un numero
   * @return Numero du composant
   */
  public int getNumero();
  
  /**
   * Tout composant possede un nom
   * @return Nom du composant
   */  
  public String getNom();
  
 
  /**
   * 
   * @return etat
   */
  public String toString();
  
}
