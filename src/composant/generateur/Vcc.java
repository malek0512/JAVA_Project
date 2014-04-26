package composant.generateur;

/**
 * 
 * Classe modélisant une Vcc qui est un generateur
 * renvoie toujours la valeur true
 */
public class Vcc extends $Generateur {
	
	/**
	 * initialise les attributs in et out
	 */
	  public Vcc(String nom, int idComposant){ 
		  super(nom,idComposant,1);
		  value = niveau.Haut;
		  spreadNiveau();
	  }

}