package Chateau;

import java.util.ArrayList;

import Main.Game;
import Main.Outils;
import Objet.Arme;
import Objet.Gemme;
import Objet.Objet;
import Objet.Potion;

public class Tresor {

	private ArrayList<Objet>	contenu;
	private int					nbCles;

	/**
	 * constructeur
	 * 
	 * @param contenu
	 */
	public Tresor(ArrayList<Objet> contenu, int nbCles) {
		super();
		this.contenu = contenu;
		this.nbCles = nbCles;
	}

	@Override
	public String toString() {
		String toString = "Le tresor contient : " + contenu.size() + " objets.";
		for (int i = 1; i < contenu.size(); i++) {
			Objet objet = contenu.get(i - 1);
			toString += "\n>>> Objet n°" + i + " : \n>>> " + objet.toString();
		}
		if (nbCles > 0) {
			toString += "\n>>> Il y a " + nbCles + " clé(s) au fond du coffre !";
		}
		return toString;
	}

	/**
	 * @return un contenu de coffre aleatoire
	 */
	public static ArrayList<Objet> creerContenuAleatoire() {
		ArrayList<Objet> _contenu = new ArrayList<Objet>();

		for (int i = 0; i < Outils.alea(1, 6); i++) { // le tresor contient de 1 a 6 objets
			String type = Objet.typeList.get(Outils.alea(0, Objet.typeList.size() - 1)); // type choisi aleatoirement (mais plus de chance pour les armes)
			if (type.equals(Objet.getTypeArme())) { // si c'est une arme
				String nom = Arme.nomList.get(Outils.alea(0, Arme.nomList.size() - 1)); // on choisi un nom d'arme aleatoire
				Arme arme = new Arme(nom, Objet.getTypeArme(), Arme.etatList.get(Outils.alea(0, Arme.etatList.size() - 1)),
						Outils.alea(1, (int) Math.round(0.4 * Game.HP))); // on cree une arme dans un etat aleatoire avec des degats aleatoires
				_contenu.add(arme); // on l'ajoute au tresor
			} else if (type.equals(Objet.getTypePotion())) {
				Potion potion = new Potion("potion", type); // on creer une potion qui rend un nombre de PV aleatoire
				_contenu.add(potion); // on l'ajoute au tresor
			} else if (type.equals(Objet.getTypeGemme())) {
				Gemme gemme = new Gemme("Gemme", type); // on creer une gemme qui donne un bonus d'attaque aleatoire
				_contenu.add(gemme); // on l'ajoute au tresor
			}
		}
		return _contenu;
	}

	/**
	 * @return le contenu du coffre
	 */
	public ArrayList<Objet> getContenu() {
		return contenu;
	}

	/**
	 * @param contenu
	 */
	public void setContenu(ArrayList<Objet> contenu) {
		this.contenu = contenu;
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
