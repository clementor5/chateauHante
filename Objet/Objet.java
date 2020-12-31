package Objet;

import java.util.ArrayList;
import java.util.Arrays;

import Main.Game;

public class Objet {

	public final static ArrayList<String>	typeList	= new ArrayList<String>(Arrays.asList("Arme", "Potion", "Gemme", "Arme", "Arme"));

	public static int						compteur	= 0;

	private int								id;
	private String							nom;
	private String							type;

	public Objet(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
		compteur++;
		this.id = compteur;
	}

	@Override
	public String toString() {
		return "ID : " + id + "\n>>> nom : " + nom;
	}

	/**
	 * Utilise l'objet et le detruit
	 */
	public void utiliser() {
		ArrayList<Objet> inventaire = Game.joueur.getInventaire();
		for (int i = 0; i < inventaire.size(); i++) {
			if (this.getId() == inventaire.get(i).getId()) {
				inventaire.remove(i);
				return;
			}
		}
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String getTypeArme() {
		return typeList.get(0);
	}

	public static String getTypePotion() {
		return typeList.get(1);
	}

	public static String getTypeGemme() {
		return typeList.get(2);
	}
}
