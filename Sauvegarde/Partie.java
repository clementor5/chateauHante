/**
 * Partie.java
 * date de creation : 8 janv. 2021
 */
package Sauvegarde;

import java.io.Serializable;

import Chateau.Chateau;
import Chateau.Sortie;
import EtreVivant.Joueur;

/**
 * Objet a sauvegarder pour pour sauvegarder une partie dans un etat à un instant precis
 */
public class Partie implements Serializable {

	public Chateau	chateau;
	public Joueur	joueur;
	public Sortie	sortieChoisie;
	public Sortie	sortiePrecedente;

	/**
	 * constructeur
	 * @param chateau
	 * @param joueur
	 * @param sortieChoisie
	 * @param sortiePrecedente
	 */
	public Partie(Chateau chateau, Joueur joueur, Sortie sortieChoisie, Sortie sortiePrecedente) {
		super();
		this.chateau = chateau;
		this.joueur = joueur;
		this.sortieChoisie = sortieChoisie;
		this.sortiePrecedente = sortiePrecedente;
	}

	/**
	 * @return chateau
	 */
	public Chateau getChateau() {
		return chateau;
	}

	/**
	 * @param chateau le champ a modifier
	 */
	public void setChateau(Chateau chateau) {
		this.chateau = chateau;
	}

	/**
	 * @return joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * @param joueur le champ a modifier
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * @return sortieChoisie
	 */
	public Sortie getSortieChoisie() {
		return sortieChoisie;
	}

	/**
	 * @param sortieChoisie le champ a modifier
	 */
	public void setSortieChoisie(Sortie sortieChoisie) {
		this.sortieChoisie = sortieChoisie;
	}

	/**
	 * @return sortiePrecedente
	 */
	public Sortie getSortiePrecedente() {
		return sortiePrecedente;
	}

	/**
	 * @param sortiePrecedente le champ a modifier
	 */
	public void setSortiePrecedente(Sortie sortiePrecedente) {
		this.sortiePrecedente = sortiePrecedente;
	}
}
