package composant;

/**
 * 
 * @category Classe intermediaire, utilisée pour désigner un numero de port d'entree y, d'un numéro de composant x 
 * Elle sera notemment utile pour la sauvegarde d'une liste de connexion (Couple (numero_composant, numero_entree)).
 */
public class Couple
{
    public int x;
    public int y;

    public Couple(int x, int y)
    {
         this.x = x;
         this.y = y;
    }
    public String toString()
    {
    	return "(x:"+x+",y:"+y+")";
    }
}
