package Objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import Main.Game;

public class Objet implements Serializable {

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
		return "---------------------- \n" + ">>> ID : " + id + "\n>>> nom : " + nom;
	}

	/**
	 * Utilise l'objet et le detruit
	 */
	public void utiliser() {
		Game.joueur.removeToInventaire(this);
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
