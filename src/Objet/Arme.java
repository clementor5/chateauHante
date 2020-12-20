package Objet;

import java.util.ArrayList;
import java.util.Arrays;

import Main.Game;

public class Arme extends Objet {

	public final static ArrayList<String>	etatList	= new ArrayList<String>(Arrays.asList("Mauvaise Etat", "Bon Etat", "Parfait Etat"));
	public final static ArrayList<String>	nomList		= new ArrayList<String>(Arrays.asList("Hache", "Epée", "Lance", "Arbalette", "Arc"));

	private String							etat;
	private int								degats;

	/**
	 * @param etat
	 */
	public Arme(String nom, String type, String etat, int degats) {
		super(nom, type);
		this.etat = etat;
		this.degats = degats;
	}

	@Override
	public String toString() {
		return super.toString() + "\n>>> Etat : " + etat + "\n>>> Degats : " + degats;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public static String getMAUVAISE_ETAT() {
		return etatList.get(0);
	}

	public static String getBON_ETAT() {
		return etatList.get(1);
	}

	public static String getPARFAIT_ETAT() {
		return etatList.get(2);
	}

	public void utiliser() {
		if (etat.equals(etatList.get(0))) {
			ArrayList<Objet> inventaire = Game.joueur.getInventaire();
			for (int i = 0; i < inventaire.size(); i++) {
				if (this.getId() == inventaire.get(i).getId()) {
					inventaire.remove(i);
				}
			}
		} else {
			for (int i = 1; i < etatList.size(); i++) {
				if (etat.equals(etatList.get(i))) {
					this.setEtat(etatList.get(i - 1));
				}
			}
		}
	}
}