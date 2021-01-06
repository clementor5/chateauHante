package EtreVivant;

import java.util.ArrayList;

import Main.Commande;
import Objet.Objet;

public class Joueur extends EtreVivant {

	private int					tailleInventaire	= 3;						// taille d'inventaire par defaut

	private ArrayList<Objet>	inventaire			= new ArrayList<Objet>();
	private int					nbCles				= 0;

	/**
	 * @param nom
	 * @param hp
	 */
	public Joueur(String nom, int hp, int tailleInventaire) {
		super(nom, hp);
		this.tailleInventaire = tailleInventaire;
	}

	public ArrayList<Objet> getInventaire() {
		return inventaire;
	}

	public void setInventaire(ArrayList<Objet> inventaire) {
		this.inventaire = inventaire;
	}

	public boolean addToInventaire(Objet objetToAdd) {
		if (inventaire.size() == tailleInventaire) {
			Commande.print("Votre inventaire est plein ! L'objet n'a pas pu etre ajouté...");
			return false;
		} else {
			inventaire.add(objetToAdd);
			return true;
		}
	}

	public void removeToInventaire(Objet objetToRemove) {
		for (Objet objet : inventaire) {
			if (objetToRemove.equals(objet)) {
				inventaire.remove(objet);
				return;
			}
		}
	}

	public void afficherInventaire() {
		Commande.print("Votre inventaire contient :");
		for (Objet objet : inventaire) {
			Commande.print(objet.toString());
		}
		Commande.print("Vous avez " + nbCles + " cles.");
	}

	/**
	 * @return la taille de l'inventaire
	 */
	public int getTailleInventaire() {
		return tailleInventaire;
	}

	/**
	 * @param tailleInventaire
	 */
	public void setTailleInventaire(int tailleInventaire) {
		this.tailleInventaire = tailleInventaire;
	}

	/**
	 * @return nbCles
	 */
	public int getNbCles() {
		return nbCles;
	}

	/**
	 * @param nbCles le champ a modifier
	 */
	public void setNbCles(int nbCles) {
		this.nbCles = nbCles;
	}
}
