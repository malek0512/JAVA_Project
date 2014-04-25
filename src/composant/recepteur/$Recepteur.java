package composant.recepteur;

import java.util.List;

import composant._ComposantInOnly;

import port.In;
import port.Out;

/*
 au moins un port dans liste de entre
 aucun port dans liste sortie
 */
public abstract class $Recepteur implements _ComposantInOnly {

	private String Nom;
	private int Numero;
	private int nbEntreeMax;

	/**
	 * Constructeur d'un recepteur
	 * 
	 * @ensure les ports du composant ne seront pas connectés
	 * @param nom
	 *            nom du composant à creer
	 * @param idComposant
	 *            identifiant du composant à creer
	 * @param nbEnt
	 *            nombre d'entrees du composant
	 * @param nbEntreeMax 
	 * 
	 */
	public $Recepteur(String nom, int idComposant, int nbEntreeMax) {
		// Initialisation du tableau des ports d'entree et de sortie du
		// composite,
		// faute de ne pourvoir etre fait avec un contructeur dans l'interface
		// _composantOutOnly and In
		for (int i = 0; i < nbEntreeMax; i++) {
			arrayEntrees.add(new In());
		}

		this.nbEntreeMax = nbEntreeMax;
		this.Nom = nom;
		this.Numero = idComposant;
	}

	/**
	 * identifiant du composant
	 */
	public int getNumero() {
		return Numero;
	}

	/**
	 * revoie le nombre d'entree(s) du composant
	 */
	public int nbEntrees() {
		return arrayEntrees.size();
	}

	/**
	 * vérifie si chaque ports du composant élémentaire est connecté
	 */
	public boolean estExecutable() {
		int i = 0;
		while (i < arrayEntrees.size()
				&& arrayEntrees.get(i).getValide() == true) {
			i++;
		}

		return (i >= arrayEntrees.size());
	}

	public String getNom() {
		return Nom;
	}

	public List<In> entreeList() {
		return arrayEntrees;
	}

}