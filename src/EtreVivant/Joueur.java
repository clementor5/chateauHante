package EtreVivant;

import java.util.ArrayList;

import Main.Commande;
import Objet.Objet;

public class Joueur extends EtreVivant {

	private int					tailleInventaire	= 3;						// taille d'inventaire par defaut
	private ArrayList<Objet>	inventaire			= new ArrayList<Objet>();

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

	public void addToInventaire(Objet objetToAdd) {
		if (inventaire.size() == tailleInventaire) {
			Commande.print("Votre inventaire est plein ! L'objet n'a pas pu etre ajouté...");
		} else {
			inventaire.add(objetToAdd);
		}
	}

	public void removeToInventaire(Objet objetToRemove) {
		for (Objet objet : inventaire) {
			if (objetToRemove.equals(objet)) {
				inventaire.remove(objet);
			}
		}
	}

	public void afficherInventaire() {
		int nbCles = 0;

		Commande.print("Votre inventaire contient :");
		for (Objet objet : inventaire) {
			if (objet.getType().equals(Objet.getTypeCle())) { // si c'est une cle
				nbCles++;
			} else { // si c'est un objet
				Commande.print(objet.toString());
			}
		}
		Commande.print("Vous avez " + nbCles + " cles.");
	}

	public int getTailleInventaire() {
		return tailleInventaire;
	}

	public void setTailleInventaire(int tailleInventaire) {
		this.tailleInventaire = tailleInventaire;
	}
}
