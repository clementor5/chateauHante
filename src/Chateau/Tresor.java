package Chateau;

import java.util.ArrayList;

import Main.Game;
import Main.Outils;
import Objet.Arme;
import Objet.Objet;

public class Tresor {

	private ArrayList<Objet> contenu;

	public Tresor(ArrayList<Objet> contenu) {
		super();
		this.contenu = contenu;
	}

	@Override
	public String toString() {
		String toString = "Le coffre contient : " + contenu.size() + " objets.";
		for (int i = 1; i < contenu.size(); i++) {
			Objet objet = contenu.get(i - 1);
			toString += "\n>>> Objet n°" + i + " : \n>>>" + objet.toString();
		}
		return toString;
	}

	public ArrayList<Objet> getContenu() {
		return contenu;
	}

	public void setContenu(ArrayList<Objet> contenu) {
		this.contenu = contenu;
	}

	public static ArrayList<Objet> creerContenuAleatoire() {
		ArrayList<Objet> _contenu = new ArrayList<Objet>();

		for (int i = 0; i < Outils.alea(1, 6); i++) { // le tresor contient de 1 a 6 objets
			String type = Objet.typeList.get(Outils.alea(0, Objet.typeList.size() - 1)); // on choisi un type d'objet aleatoire
			if (type.equals(Objet.getTypeArme())) { // si c'est une arme
				String nom = Arme.nomList.get(Outils.alea(0, Arme.nomList.size() - 1)); // on choisi un nom d'arme aleatoire
				Arme arme = new Arme(nom, type, Arme.etatList.get(Outils.alea(0, Arme.etatList.size() - 1)),
						Outils.alea(1, (int) Math.round(0.4 * Game.HP))); // on cree une arme dans un etat aleatoire
				_contenu.add(arme); // on l'ajoute au tresor
			} else {
				Objet objet = new Objet(Objet.getTypeCle(), type); // #TODO gerer d'autres types d'objet plus tard
				_contenu.add(objet); // on l'ajoute au tresor
			}
		}
		return _contenu;
	}
}
