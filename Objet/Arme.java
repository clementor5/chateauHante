package Objet;

import java.util.ArrayList;
import java.util.Arrays;

import Main.Commande;

public class Arme extends Objet {

	public final static ArrayList<String>	etatList	= new ArrayList<String>(Arrays.asList("Mauvaise Etat", "Bon Etat", "Parfait Etat"));
	public final static ArrayList<String>	nomList		= new ArrayList<String>(Arrays.asList("Hache", "Epée", "Lance", "Arbalette", "Arc", "Nerf",
			"Poele à frire", "Gants de boxe", "Clavier", "Batte de Baseball", "Gourdin", "Epee Covidée", "Fouet", "couteau", "passoire", "Cable usb",
			"Cactus", "Porte manteau", "katana", "fusil", "boule de pétanque", "Poing américain", "fourchette"));

	private String							etat;
	private int								degats;

	/**
	 * @param nom
	 * @param type
	 * @param etat
	 * @param degats
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

	/**
	 * @return l'etat de l'arme
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * @param etat
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * @return les degats de l'arme
	 */
	public int getDegats() {
		return degats;
	}

	/**
	 * @param degats
	 */
	public void setDegats(int degats) {
		this.degats = degats;
	}

	/**
	 * @return la string correspondant au mauvaise état
	 */
	public static String getMAUVAISE_ETAT() {
		return etatList.get(0);
	}

	/**
	 * @return la string correspondant au bon état
	 */
	public static String getBON_ETAT() {
		return etatList.get(1);
	}

	/**
	 * @return la string correspondant au parfait état
	 */
	public static String getPARFAIT_ETAT() {
		return etatList.get(2);
	}

	/**
	 * Utilise l'arme et met a jour son état La détruit si elle est en mauvaise état
	 */
	@Override
	public void utiliser() {
		if (etat.equals(etatList.get(0))) {
			Commande.print("L'arme était en mauvais état, elle est donc détruite lors de l'utilisation");
			super.utiliser();
		} else {
			for (int i = 1; i < etatList.size(); i++) {
				if (etat.equals(etatList.get(i))) {
					Commande.print("L'arme passe de l'etat " + etat + " a l'etat " + etatList.get(i - 1));
					etat = etatList.get(i - 1);
					return;
				}
			}
		}
	}
}