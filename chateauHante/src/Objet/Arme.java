package Objet;

import java.util.ArrayList;
import java.util.Arrays;

import EtreVivant.Joueur;

public class Arme extends Objet {

	public static Joueur					joueur		= null;

	public final static ArrayList<String>	etatList	= new ArrayList<String>(
			Arrays.asList("Mauvaise Etat", "Bon Etat", "Parfait Etat"));
	public final static ArrayList<String>	nomList		= new ArrayList<String>(
			Arrays.asList("Hache", "Epée", "Lance", "Arbalette", "Arc"));

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
		return typeList.get(0);
	}

	public static String getBON_ETAT() {
		return typeList.get(1);
	}

	public static String getPARFAIT_ETAT() {
		return typeList.get(2);
	}
}